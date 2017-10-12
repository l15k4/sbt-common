package gwi.sbt

import sbt.Keys.{parallelExecution, _}
import sbt.{Def, File, _}
import sbtassembly.AssemblyPlugin.autoImport._
import sbtdocker.DockerPlugin
import sbtdocker.DockerPlugin.autoImport._
import sbtdocker.mutable.Dockerfile
import sbtdocker.DockerKeys.{dockerBuildAndPush, dockerPush}

trait Packager extends Dependencies {

  def publishSettings(organization: String, projectName: String, resolverOpt: sbt.Resolver) = Seq(
    publishTo := Some(resolverOpt),
    publishMavenStyle := true,
    publishArtifact := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    pomExtra :=
      <url>https://github.com/{organization}/{projectName}</url>
       <licenses>
         <license>
           <name>The MIT License (MIT)</name>
           <url>http://opensource.org/licenses/MIT</url>
           <distribution>repo</distribution>
         </license>
       </licenses>
       <scm>
         <url>git@github.com:{organization}/{projectName}.git</url>
         <connection>scm:git:git@github.com:{organization}/{projectName}.git</connection>
       </scm>
       <developers>
         <developer>
           <id>l15k4</id>
           <name>Jakub Liska</name>
           <email>liska.jakub@gmail.com</email>
         </developer>
       </developers>
  )

  private def deployFileMappings(sourceDirectory: String, targetDirectory: String): List[(String, String)] = {
    def recursively(dir: File): Array[File] = {
      val list = sbt.IO.listFiles(dir).sortBy(_.getName) // app-deps.jar first, then app.jar, because deps don't change usually
      list.filter(_.isFile) ++ list.filter(_.isDirectory).flatMap(recursively)
    }
    recursively(new File(sourceDirectory)).toList
      .map(_.absolutePath)
      .map( path => path.substring(sourceDirectory.length) )
      .map(partialPath => (sourceDirectory + partialPath, targetDirectory + partialPath))
  }

  private def chainDeps[T](taskKey: TaskKey[T], confs: Seq[Configuration]): Def.Setting[Task[T]] = confs.toList match {
    case xs if xs.isEmpty || xs.size == 1 || xs.size > 4 =>
      sys.error("It is possible to chain only 2-4 tasks !!! ")
    case first :: second :: Nil =>
      taskKey := ((taskKey in first) dependsOn(taskKey in second)).value
    case first :: second :: third :: Nil =>
      taskKey := ((taskKey in first) dependsOn(taskKey in second) dependsOn(taskKey in third)).value
    case first :: second :: third :: fourth :: Nil =>
      taskKey := ((taskKey in first) dependsOn(taskKey in second) dependsOn(taskKey in third) dependsOn(taskKey in fourth)).value
  }

  case class DeployDef(conf: Configuration, baseImageName: String, repoName: String, appName: String, mainClassFqn: String, unmanagedJarFiles: Seq[File] = Seq.empty)
  def deployMultiple(deployDefs: DeployDef*): Seq[Def.Setting[_]] =
    deployDefs.flatMap { case DeployDef(conf, baseImageName, repoName, appName, mainClassFqn, unmanagedJarFiles) =>
      inConfig(conf)(DockerPlugin.projectSettings ++ deploy(baseImageName, repoName, appName, mainClassFqn, unmanagedJarFiles))
    } ++ Seq(
      chainDeps(docker, deployDefs.map(_.conf)),
      chainDeps(dockerPush, deployDefs.map(_.conf)),
      chainDeps(dockerBuildAndPush, deployDefs.map(_.conf))
    )

  def deploy(baseImageName: String, repoName: String, appName: String, mainClassFqn: String, unmanagedJarFiles: Seq[File] = Seq.empty): Seq[Def.Setting[_]] = {
    val workingDir = SettingKey[File]("working-dir", "Working directory path for running applications")
    Seq(
      assembleArtifact := true,
      assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false, includeDependency = false),
      assemblyJarName in assembly := s"$appName.jar",
      assemblyJarName in assemblyPackageDependency := s"$appName-deps.jar",
      workingDir := baseDirectory.value / "deploy",
      cleanFiles += baseDirectory.value / "deploy" / "bin",
      baseDirectory in run := workingDir.value,
      baseDirectory in runMain := workingDir.value,
      unmanagedJars in Compile ++= unmanagedJarFiles,
      test in assembly := {},
      test in assemblyPackageDependency := {},
      mainClass in assembly := Some(mainClassFqn),
      aggregate in assembly := false,
      aggregate in assemblyPackageDependency := false,
      assemblyOutputPath in assembly := workingDir.value / "bin" / (assemblyJarName in assembly).value,
      assemblyOutputPath in assemblyPackageDependency := workingDir.value / "bin" / (assemblyJarName in assemblyPackageDependency).value,
      assembly := (assembly dependsOn clean).value,
      concurrentRestrictions in docker += Tags.limit(Tags.All, 1),
      parallelExecution in docker := false,
      concurrentRestrictions in dockerBuildAndPush += Tags.limit(Tags.All, 1),
      parallelExecution in dockerBuildAndPush := false,
      docker := (docker dependsOn(assembly, assemblyPackageDependency)).value,
      dockerfile in docker :=
        new Dockerfile {
          from(baseImageName)
          run("/bin/mkdir", s"/opt/$appName")
          deployFileMappings(workingDir.value.absolutePath, s"/opt/$appName").map { case (sourcePath, targetPath) => copy(new File(sourcePath), new File(targetPath)) }
          workDir(s"/opt/$appName")
          entryPoint("java", "-cp", s"bin/*", mainClassFqn)
        },
      imageNames in docker := Seq(
        ImageName(s"$repoName/$appName:${version.value}"),
        ImageName(s"$repoName/$appName:latest")
      )
    )
  }

}