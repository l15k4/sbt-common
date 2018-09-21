package gwi.sbt

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

trait Dependencies {

  lazy val akkaVersion                  = "2.5.16"
  lazy val alpakkaVersion               = "0.18"
  lazy val akkaHttpVersion              = "10.1.5"
  lazy val jacksonVersion               = "2.9.2"

  lazy val scalaJsDom                   = "org.scala-js"                  %%%!  "scalajs-dom"                           % "0.9.6"
  lazy val scalaJsJavaTime              = "org.scala-js"                  %%%!  "scalajs-java-time"                     % "0.2.2"
  lazy val scalaTags                    = "com.lihaoyi"                   %%%!  "scalatags"                             % "0.6.7"
  lazy val prickle                      = "com.github.benhutchison"       %%%!  "prickle"                               % "1.1.14"
  lazy val scalaRx                      = "com.lihaoyi"                   %%%!  "scalarx"                               % "0.3.2"
  lazy val utest                        = "com.lihaoyi"                   %%%!  "utest"                                 % "0.6.5"                 % "test"

  lazy val awsS3                        = "com.amazonaws"                 %     "aws-java-sdk-s3"                       % "1.11.413"
  lazy val awsSNS                       = "com.amazonaws"                 %     "aws-java-sdk-sns"                      % "1.11.413"
  lazy val loggingImplLog4j             = "org.slf4j"                     %     "slf4j-log4j12"                         % "1.7.25"
  lazy val loggingImplLogback           = "ch.qos.logback"                %     "logback-classic"                       % "1.2.3"
  lazy val guava                        = "com.google.guava"              %     "guava"                                 % "23.0"
  lazy val scalaHttp                    = "org.scalaj"                    %%    "scalaj-http"                           % "2.4.1"
  lazy val sttp                         = "com.softwaremill.sttp"         %%    "core"                                  % "1.3.3"
  lazy val commonsNet                   = "commons-net"                   %     "commons-net"                           % "3.6"
  lazy val commonsMath                  = "org.apache.commons"            %     "commons-math3"                         % "3.6.1"
  lazy val sprayJson                    = "io.spray"                      %%    "spray-json"                            % "1.3.4"
  lazy val scalaRedis                   = "net.debasishg"                 %%    "redisclient"                           % "3.4"
  lazy val algebird                     = "com.twitter"                   %%    "algebird-core"                         % "0.13.3"
  lazy val monix                        = "io.monix"                      %%    "monix"                                 % "2.3.2"
  lazy val typesafeConfig               = "com.typesafe"                  %     "config"                                % "1.3.1"
  lazy val configAnnotation             = "com.wacai"                     %%    "config-annotation"                     % "0.3.7"
  lazy val kafkaClients                 = "org.apache.kafka"              %     "kafka-clients"                         % "0.10.2.1"
  lazy val scalaMeta                    = "org.scalameta"                 %%    "scalameta"                             % "2.0.1"
  lazy val cassandraDriver              = "com.datastax.cassandra"        %     "cassandra-driver-core"                 % "3.6.0"
  lazy val nettyHandler                 = "io.netty"                      %     "netty-handler"                         % "4.1.29.Final" classifier "linux-x86_64"
  lazy val cassandraDriverNettyEpoll    = "io.netty"                      %     "netty-transport-native-epoll"          % "4.1.29.Final" classifier "linux-x86_64"
  lazy val snappy                       = "org.xerial.snappy"             %     "snappy-java"                           % "1.1.7.2"
  lazy val asciiGraphs                  = "com.github.mdr"                %%    "ascii-graphs"                          % "0.0.7"
  lazy val pprint                       = "com.lihaoyi"                   %%    "pprint"                                % "0.5.3"
  lazy val fastUuid                     = "com.eatthepath"                %     "fast-uuid"                             % "0.1"

  lazy val gcBigQuery                   = "com.google.cloud"              %     "google-cloud-bigquery"                 % "1.43.0"

  lazy val akkaActor                    = "com.typesafe.akka"             %%    "akka-actor"                            % akkaVersion
  lazy val akkaSlf4j                    = "com.typesafe.akka"             %%    "akka-slf4j"                            % akkaVersion
  lazy val akkaStream                   = "com.typesafe.akka"             %%    "akka-stream"                           % akkaVersion
  lazy val akkaStreamTestkit            = "com.typesafe.akka"             %%    "akka-stream-testkit"                   % akkaVersion
  lazy val akkaStreamKafka              = "com.typesafe.akka"             %%    "akka-stream-kafka"                     % "0.22"
  lazy val akkaStreamCheckpoint         = "com.github.svezfaz"            %%    "akka-stream-checkpoint-kamon"          % "0.0.3"
  lazy val akkaHttp                     = "com.typesafe.akka"             %%    "akka-http"                             % akkaHttpVersion
  lazy val akkaHttpCors                 = "ch.megard"                     %%    "akka-http-cors"                        % "0.2.2"
  lazy val akkaHttpSprayJson            = "com.typesafe.akka"             %%    "akka-http-spray-json"                  % akkaHttpVersion
  lazy val akkaHttpTestkit              = "com.typesafe.akka"             %%    "akka-http-testkit"                     % akkaHttpVersion         % "test"
  lazy val akkaRemote                   = "com.typesafe.akka"             %%    "akka-remote"                           % akkaVersion
  lazy val akkaCluster                  = "com.typesafe.akka"             %%    "akka-cluster"                          % akkaVersion
  lazy val akkaClusterTools             = "com.typesafe.akka"             %%    "akka-cluster-tools"                    % akkaVersion
  lazy val akkaClusterSharding          = "com.typesafe.akka"             %%    "akka-cluster-sharding"                 % akkaVersion
  lazy val akkaClusterCustomDowning     = "com.github.TanUkkii007"        %%    "akka-cluster-custom-downing"           % "0.0.9"
  lazy val akkaDistributedData          = "com.typesafe.akka"             %%    "akka-distributed-data-experimental"    % akkaVersion
  lazy val akkaPersistence              = "com.typesafe.akka"             %%    "akka-persistence"                      % akkaVersion
  lazy val akkaPersistenceRedis         = "com.safety-data"               %%    "akka-persistence-redis"                % "0.3.0"
  lazy val akkaPersistenceJdbc          = "com.github.dnvriend"           %%    "akka-persistence-jdbc"                 % "3.4.0"
  lazy val akkaPersistenceCassandra     = "com.typesafe.akka"             %%    "akka-persistence-cassandra"            % "0.90"
  lazy val akkaPersistenceCassandraTest = "com.typesafe.akka"             %%    "akka-persistence-cassandra-launcher"   % "0.90"                  % Test
  lazy val akkaPersistenceDynamoDB      = "com.typesafe.akka"             %%    "akka-persistence-dynamodb"             % "1.1.0"
  lazy val akkaPersistenceInMemory      = "com.github.dnvriend"           %%    "akka-persistence-inmemory"             % "2.5.1.1"
  lazy val akkaKryoSerialization        = "com.github.romix.akka"         %%    "akka-kryo-serialization"               % "0.5.2"
  lazy val akkaTestkit                  = "com.typesafe.akka"             %%    "akka-testkit"                          % akkaVersion             % "test"
  lazy val akkaMockScheduler            = "com.miguno.akka"               %%    "akka-mock-scheduler"                   % "0.5.1"                 % "test"
  lazy val akkaMultiNodeTestkit         = "com.typesafe.akka"             %%    "akka-multi-node-testkit"               % akkaVersion             % "test"

  lazy val alpakkaFtp                   = "com.lightbend.akka"            %%    "akka-stream-alpakka-ftp"               % alpakkaVersion
  lazy val alpakkaFile                  = "com.lightbend.akka"            %%    "akka-stream-alpakka-file"              % alpakkaVersion
  lazy val alpakkaS3                    = "com.lightbend.akka"            %%    "akka-stream-alpakka-s3"                % alpakkaVersion
  lazy val alpakkaDynamodb              = "com.lightbend.akka"            %%    "akka-stream-alpakka-dynamodb"          % alpakkaVersion
  lazy val alpakkaCassandra             = "com.lightbend.akka"            %%    "akka-stream-alpakka-cassandra"         % alpakkaVersion

  lazy val ammonite                     = "com.lihaoyi"                   %     "ammonite"                              % "1.0.2"                 % "test" cross CrossVersion.full
  lazy val scalatest                    = "org.scalatest"                 %%    "scalatest"                             % "3.0.5"                 % "test"
  lazy val scalatestKafka               = "net.manub"                     %%    "scalatest-embedded-kafka"              % "2.0.0"                 % "test"
  lazy val scalameter                   = "com.storm-enroute"             %%    "scalameter"                            % "0.8.2"                 % "test"
  lazy val s3mock                       = "io.findify"                    %%    "s3mock"                                % "0.2.5"                 % "test"

  lazy val jodaTime                     = Seq(
                                          "joda-time"                     %     "joda-time"                             % "2.9.9",
                                          "org.joda"                      %     "joda-convert"                          % "1.9.2"
                                        )
  lazy val jackson                      = Seq(
                                          "com.fasterxml.jackson.module"  %%    "jackson-module-scala"                  % jacksonVersion,
                                          "com.fasterxml.jackson.core"    %     "jackson-core"                          % jacksonVersion,
                                          "com.fasterxml.jackson.core"    %     "jackson-annotations"                   % jacksonVersion
                                        )
  lazy val clist                        = Seq(
                                          "org.backuity.clist"            %%    "clist-core"                            % "3.5.0",
                                          "org.backuity.clist"            %%    "clist-macros"                          % "3.5.0"                 % "provided"
                                        )
  lazy val loggingApi                   = Seq(
                                          "org.slf4j"                     %     "slf4j-api"                             % "1.7.25",
                                          "com.typesafe.scala-logging"    %%    "scala-logging"                         % "3.9.0"
                                        )
}
