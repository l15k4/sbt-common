package gwi.sbt

import java.util.TimeZone

import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin
import sbtassembly.AssemblyPlugin.autoImport._

object CommonPlugin extends AutoPlugin {

  object autoImport extends Packager {
    lazy val s3Resolver = "S3 Snapshots" at "s3://public.maven.globalwebindex.net.s3-website-eu-west-1.amazonaws.com/snapshots"
  }

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = JvmPlugin

  override lazy val projectSettings = Seq(
    scalaVersion := "2.12.1",
    offline := true,
    scalacOptions ++= Seq(
      "-unchecked", "-feature",
      "-Xlint", "-Xfuture",
      "-Ywarn-adapted-args", "-Ywarn-inaccessible",
      "-Ywarn-nullary-override", "-Ywarn-nullary-unit", "-Yno-adapted-args"
    ),
    autoCompilerPlugins := true,
    publishArtifact := false, // if project wants to publish, it should override it with Packager.publishSettings
    assembleArtifact := false, // if project wants to publish, it should override it with Packager.assemblySettings
    resolvers ++= Seq(
      autoImport.s3Resolver,
      Resolver.sonatypeRepo("snapshots"),
      Resolver.typesafeRepo("releases"),
      Resolver.mavenLocal,
      Resolver.jcenterRepo,
      Resolver.bintrayRepo("tanukkii007", "maven")
    ),
    /* sensible default test settings */
    testOptions in Test ++= Seq(Tests.Argument("-oDFI"), Tests.Setup(() => TimeZone.setDefault(TimeZone.getTimeZone("UTC")))),
    fork in Test := false,
    parallelExecution in Test := false,
    parallelExecution in IntegrationTest := false,
    testForkedParallel in IntegrationTest := false,
    testForkedParallel in Test := false,
    concurrentRestrictions in Test += Tags.limit(Tags.Test, 1),
    initialCommands in(Test, console) := """ammonite.repl.Main().run()"""
  )

  override def globalSettings: Seq[_root_.sbt.Def.Setting[_]] = Seq(
    cancelable := true
  )
}
