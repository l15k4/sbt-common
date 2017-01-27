import sbt._

lazy val `sbt-common` = (project in file("."))
  .settings(
    organization := "l15k4",
    name := "sbt-common",
    scalaVersion := "2.10.6",
    crossScalaVersions := Seq("2.10.6"),
    sbtPlugin := true
  )
  .settings(
    addSbtPlugin("com.eed3si9n"         % "sbt-buildinfo"         % "0.6.1"),
    addSbtPlugin("com.timushev.sbt"     % "sbt-updates"           % "0.2.0"),
    addSbtPlugin("com.jsuereth"         % "sbt-pgp"               % "1.0.0"),
    addSbtPlugin("com.eed3si9n"         % "sbt-assembly"          % "0.14.3"),
    addSbtPlugin("com.eed3si9n"         % "sbt-buildinfo"         % "0.6.1"),
    addSbtPlugin("com.frugalmechanic"   % "fm-sbt-s3-resolver"    % "0.9.0"),
    addSbtPlugin("se.marcuslonnberg"    % "sbt-docker"            % "1.4.0"),
    addSbtPlugin("net.virtual-void"     % "sbt-dependency-graph"  % "0.8.2"),
    addSbtPlugin("pl.project13.scala"   % "sbt-jmh"               % "0.2.18"),
    addCompilerPlugin("com.lihaoyi"     %% "acyclic"              % "0.1.7")
  )