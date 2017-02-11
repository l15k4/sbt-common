package gwi.sbt

import sbt._

trait Dependencies {

  lazy val akkaVersion                = "2.4.17"
  lazy val alpakkaVersion             = "0.5"
  lazy val akkaHttpVersion            = "10.0.3"
  lazy val jacksonVersion             = "2.8.6"

  lazy val awsS3                  = "com.amazonaws"                 %  "aws-java-sdk-s3"              % "1.11.68"
  lazy val loggingImplLog4j       = "org.slf4j"                     %  "slf4j-log4j12"                % "1.7.22"
  lazy val loggingImplLogback     = "ch.qos.logback"                %  "logback-classic"              % "1.1.9"
  lazy val pprint                 = "com.lihaoyi"                   %% "pprint"                       % "0.4.4"
  lazy val guava                  = "com.google.guava"              %  "guava"                        % "21.0"
  lazy val sparkSketch            = "org.apache.spark"              %% "spark-sketch"                 % "2.1.0"
  lazy val scalaHttp              = "org.scalaj"                    %% "scalaj-http"                  % "2.3.0"
  lazy val commonsNet             = "commons-net"                   %  "commons-net"                  % "3.5"
  lazy val commonsMath            = "org.apache.commons"            %  "commons-math3"                % "3.6"
  lazy val sprayJson              = "io.spray"                      %% "spray-json"                   % "1.3.3"
  lazy val redis                  = "com.github.etaty"              %% "rediscala"                    % "1.8.0"
  lazy val algebird               = "com.twitter"                   %% "algebird-core"                % "0.12.4"
  lazy val monix                  = "io.monix"                      %% "monix"                        % "2.2.1"
  lazy val jodaTime               = Seq(
                                    "joda-time"                     %  "joda-time"                    % "2.9.7",
                                    "org.joda"                      %  "joda-convert"                 % "1.8"
                                  )
  lazy val jackson                = Seq(
                                    "com.fasterxml.jackson.module"  %% "jackson-module-scala"         % jacksonVersion,
                                    "com.fasterxml.jackson.core"    %  "jackson-core"                 % jacksonVersion,
                                    "com.fasterxml.jackson.core"    %  "jackson-annotations"          % jacksonVersion
                                  )
  lazy val clist                  = Seq(
                                    "org.backuity.clist"            %% "clist-core"                   % "3.2.2",
                                    "org.backuity.clist"            %% "clist-macros"                 % "3.2.2"                     % "provided"
                                  )
  lazy val loggingApi             = Seq(
                                    "org.slf4j"                     %  "slf4j-api"                    % "1.7.22",
                                    "com.typesafe.scala-logging"    %% "scala-logging"                % "3.5.0"
                                  )
  lazy val uaDetector             = Seq(
                                    "javax.servlet"                 %  "javax.servlet-api"            % "3.1.0",
                                    "net.sf.uadetector"             %  "uadetector-resources"         % "2014.10"
                                  )
  lazy val akkaDeps               = Seq(
                                    "com.typesafe.akka"             %% "akka-stream"                  % akkaVersion,
                                    "com.typesafe.akka"             %% "akka-http"                    % akkaHttpVersion,
                                    "com.typesafe.akka"             %% "akka-http-spray-json"         % akkaHttpVersion,
                                    "com.typesafe.akka"             %% "akka-remote"                  % akkaVersion,
                                    "com.typesafe.akka"             %% "akka-cluster"                 % akkaVersion,
                                    "com.typesafe.akka"             %% "akka-cluster-tools"           % akkaVersion,
                                    "com.typesafe.akka"             %% "akka-persistence"             % akkaVersion,
                                    "com.hootsuite"                 %% "akka-persistence-redis"       % "0.6.0",
                                    "ch.megard"                     %% "akka-http-cors"               % "0.1.11",
                                    "com.github.TanUkkii007"        %% "akka-cluster-custom-downing"  % "0.0.7",
                                    "com.github.romix.akka"         %% "akka-kryo-serialization"      % "0.5.0",
                                    "com.lightbend.akka"            %% "akka-stream-alpakka-file"     % alpakkaVersion,
                                    "com.lightbend.akka"            %% "akka-stream-alpakka-s3"       % alpakkaVersion,
                                    "com.lightbend.akka"            %% "akka-stream-alpakka-dynamodb" % alpakkaVersion,
                                    "com.typesafe.akka"             %% "akka-http-testkit"            % akkaHttpVersion             % "test",
                                    "com.typesafe.akka"             %% "akka-testkit"                 % akkaVersion                 % "test"
                                  )
  lazy val testingDeps            = Seq(
                                    "com.lihaoyi"                   %  "ammonite"                     % "0.8.2"                     % "test" cross CrossVersion.full,
                                    "org.scalatest"                 %% "scalatest"                    % "3.0.1"                     % "test",
                                    "com.storm-enroute"             %% "scalameter"                   % "0.8.2"                     % "test"
                                  )
}
