lazy val `sbt-common` = (project in file("."))
  .settings(
    organization := "net.globalwebindex",
    name := "sbt-common",
    version := "0.0.8",
    sbtPlugin := true,
    publishTo := Some("releases" at "https://oss.sonatype.org/" + "service/local/staging/deploy/maven2"),
    publishMavenStyle := true,
    pomIncludeRepository := { _ => false}
  ).settings(
    addSbtPlugin("io.get-coursier"                % "sbt-coursier"          % "1.0.0"),
    addSbtPlugin("com.jsuereth"                   % "sbt-pgp"               % "1.1.0-M1"),
    addSbtPlugin("net.virtual-void"               % "sbt-dependency-graph"  % "0.9.0"),
    addSbtPlugin("com.eed3si9n"                   % "sbt-buildinfo"         % "0.7.0"),
    addSbtPlugin("com.timushev.sbt"               % "sbt-updates"           % "0.3.3"),
    addSbtPlugin("com.eed3si9n"                   % "sbt-assembly"          % "0.14.6"),
    addSbtPlugin("com.frugalmechanic"             % "fm-sbt-s3-resolver"    % "0.14.0"),
    addSbtPlugin("se.marcuslonnberg"              % "sbt-docker"            % "1.5.0"),
    addSbtPlugin("pl.project13.scala"             % "sbt-jmh"               % "0.3.2"),
    addSbtPlugin("org.scala-js"                   % "sbt-scalajs"           % "0.6.21"),
    addCompilerPlugin("com.softwaremill.clippy"   %% "plugin"               % "0.5.3" classifier "bundle"),
    addCompilerPlugin("com.lihaoyi"               %% "acyclic"              % "0.1.7")
  )