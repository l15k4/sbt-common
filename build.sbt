lazy val `sbt-common` = (project in file("."))
  .settings(
    organization := "net.globalwebindex",
    name := "sbt-common",
    scalaVersion := "2.10.6",
    crossScalaVersions := Seq("2.10.6"),
    sbtPlugin := true,
    publishTo := Some("S3 Snapshots" at "s3://public.maven.globalwebindex.net.s3-website-eu-west-1.amazonaws.com/snapshots"),
    publishMavenStyle := true,
    pomIncludeRepository := { _ => false}
  ).settings(
    addSbtPlugin("com.eed3si9n"                   % "sbt-buildinfo"         % "0.6.1"),
    addSbtPlugin("com.timushev.sbt"               % "sbt-updates"           % "0.2.0"),
    addSbtPlugin("com.jsuereth"                   % "sbt-pgp"               % "1.0.0"),
    addSbtPlugin("com.eed3si9n"                   % "sbt-assembly"          % "0.14.3"),
    addSbtPlugin("com.dwijnand"                   % "sbt-dynver"            % "1.1.1"),
    addSbtPlugin("com.eed3si9n"                   % "sbt-buildinfo"         % "0.6.1"),
    addSbtPlugin("com.frugalmechanic"             % "fm-sbt-s3-resolver"    % "0.9.0"),
    addSbtPlugin("se.marcuslonnberg"              % "sbt-docker"            % "1.4.1"),
    addSbtPlugin("net.virtual-void"               % "sbt-dependency-graph"  % "0.8.2"),
    addSbtPlugin("com.timgroup"                   % "sbt-utc"               % "0.0.14"),
    addSbtPlugin("pl.project13.scala"             % "sbt-jmh"               % "0.2.18"),
    addCompilerPlugin("com.softwaremill.clippy"   %% "plugin"               % "0.5.0" classifier "bundle"),
    addCompilerPlugin("com.lihaoyi"               %% "acyclic"              % "0.1.7")
  )