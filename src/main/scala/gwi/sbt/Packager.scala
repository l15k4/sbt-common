package gwi.sbt

import sbt.Keys._
import sbt.{File, _}
import sbtassembly.AssemblyPlugin.autoImport._
import sbtdocker.DockerPlugin.autoImport._
import sbtdocker.mutable.Dockerfile

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

  val workingDir = SettingKey[File]("working-dir", "Working directory path for running applications")

  def assemblySettings(appName: String, mainClassFqn: Option[String]) = {
    Seq(
      /* FAT JAR */
      libraryDependencies ++= clist,
      assembleArtifact := true,
      assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false, includeDependency = false),
      assemblyJarName in assembly := s"$appName.jar",
      assemblyJarName in assemblyPackageDependency := s"$appName-deps.jar",
      workingDir := baseDirectory.value / "deploy",
      cleanFiles += baseDirectory.value / "deploy" / "bin",
      baseDirectory in run := workingDir.value,
      baseDirectory in runMain := workingDir.value,
      test in assembly := {},
      test in assemblyPackageDependency := {},
      mainClass in assembly := mainClassFqn, // Note that sbt-assembly cannot assemble jar with multiple main classes use SBT instead
      aggregate in assembly := false,
      aggregate in assemblyPackageDependency := false,
      assemblyOutputPath in assembly := workingDir.value / "bin" / (assemblyJarName in assembly).value,
      assemblyOutputPath in assemblyPackageDependency := workingDir.value / "bin" / (assemblyJarName in assemblyPackageDependency).value
    )
  }

  def deployFileMappings(sourceDirectory: String, targetDirectory: String): List[(String, String)] = {
    def recursively(dir: File): Array[File] = {
      val list = sbt.IO.listFiles(dir).sortBy(_.getName) // app-deps.jar first, then app.jar, because deps don't change usually
      list.filter(_.isFile) ++ list.filter(_.isDirectory).flatMap(recursively)
    }
    recursively(new File(sourceDirectory)).toList
      .map(_.absolutePath)
      .map( path => path.substring(sourceDirectory.length) )
      .map(partialPath => (sourceDirectory + partialPath, targetDirectory + partialPath))
  }

  def deploySettings(baseImageName: String, repoName: String, appName: String, mainClassFqn: String, extraClasspath: Option[String] = None) = {
    Seq(
      docker := (docker dependsOn(assembly, assemblyPackageDependency)).value,
      dockerfile in docker :=
        new Dockerfile {
          from(baseImageName)
          run("/bin/mkdir", s"/opt/$appName")
          deployFileMappings(workingDir.value.absolutePath, s"/opt/$appName").map { case (sourcePath, targetPath) => copy(new File(sourcePath), new File(targetPath)) }
          workDir(s"/opt/$appName")
          entryPoint("java", "-cp", s"bin/*" + extraClasspath.map(":" + _).getOrElse(""), mainClassFqn)
        },
      imageNames in docker := Seq(
        ImageName(s"$repoName/$appName:${version.value}"),
        ImageName(s"$repoName/$appName:latest")
      )
    )
  }

  def copyJarTo(baseImageName: String, repoName: String, appName: String, baseAppName: String) =
    Seq(
      docker := (docker dependsOn(assembly, assemblyPackageDependency)).value,
      dockerfile in docker :=
        new Dockerfile {
          from(baseImageName)
          deployFileMappings(workingDir.value.absolutePath, s"/opt/$baseAppName").map { case (sourcePath, targetPath) => copy(new File(sourcePath), new File(targetPath)) }
        },
      imageNames in docker := Seq(
        ImageName(s"$repoName/$appName:${version.value}"),
        ImageName(s"$repoName/$appName:latest")
      )
    )

}