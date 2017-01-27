package gwi.sbt

import java.util.TimeZone

import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin
import sbtassembly.AssemblyPlugin.autoImport._

object CommonPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = JvmPlugin

  override lazy val projectSettings = Seq(
    scalaVersion := "2.11.8",
    offline := true,
    scalacOptions ++= Seq(
      "-unchecked", "-feature",
      "-Xlint", "-Xfuture",
      "-Yinline-warnings", "-Ywarn-adapted-args", "-Ywarn-inaccessible",
      "-Ywarn-nullary-override", "-Ywarn-nullary-unit", "-Yno-adapted-args"
    ),
    autoCompilerPlugins := true,
    publishArtifact := false, // if project wants to publish, it should override it with Packager.publishSettings
    assembleArtifact := false, // if project wants to publish, it should override it with Packager.assemblySettings
    resolvers ++= Seq(
      Resolver.sonatypeRepo("snapshots"),
      Resolver.typesafeRepo("releases"),
      Resolver.jcenterRepo,
      Resolver.mavenLocal
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

  object autoImport extends Packager

  override def globalSettings: Seq[_root_.sbt.Def.Setting[_]] = Seq(
    cancelable := true
  )
}
