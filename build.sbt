lazy val `sbt-common` = (project in file("."))
  .settings(
    organization := "net.globalwebindex",
    name := "sbt-common",
    version := "0.0.3",
    sbtPlugin := true,
    publishTo := Some("S3 Snapshots" at "s3://public.maven.globalwebindex.net.s3-website-eu-west-1.amazonaws.com/snapshots"),
    publishMavenStyle := true,
    pomIncludeRepository := { _ => false}
  ).settings(
    addSbtPlugin("com.eed3si9n"                   % "sbt-buildinfo"         % "0.7.0"),
    addSbtPlugin("com.timushev.sbt"               % "sbt-updates"           % "0.3.1"),
    addSbtPlugin("com.jsuereth"                   % "sbt-pgp"               % "1.0.0"),
    addSbtPlugin("com.eed3si9n"                   % "sbt-assembly"          % "0.14.5"),
    addSbtPlugin("com.frugalmechanic"             % "fm-sbt-s3-resolver"    % "0.13.0"),
    addSbtPlugin("se.marcuslonnberg"              % "sbt-docker"            % "1.4.1"),
    addSbtPlugin("net.virtual-void"               % "sbt-dependency-graph"  % "0.8.2"),
    addSbtPlugin("com.timgroup"                   % "sbt-utc"               % "0.0.14"),
    addSbtPlugin("pl.project13.scala"             % "sbt-jmh"               % "0.2.27"),
    addSbtPlugin("org.scala-js"                   % "sbt-scalajs"           % "0.6.20"),
    addCompilerPlugin("com.softwaremill.clippy"   %% "plugin"               % "0.5.3" classifier "bundle"),
    addCompilerPlugin("com.lihaoyi"               %% "acyclic"              % "0.1.7")
  )