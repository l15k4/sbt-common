## sbt-common

Provides standardized configuration and dependencies to use in similar scala based projects.

### production setup :

In **project/plugins.sbt** :
```
resolvers += "S3 Snapshots" at "s3://public.maven.globalwebindex.net.s3-website-eu-west-1.amazonaws.com/snapshots"
addSbtPlugin("net.globalwebindex" % "sbt-common" % "x.y.z")
```
In **project/project/plugins.sbt** (because the plugin is not published to an official maven repository yet) :
 ```
 addSbtPlugin("com.frugalmechanic" % "fm-sbt-s3-resolver" % "0.9.0")
 ```

### development setup :

You can use plugin directly in which case you need just this in **project/plugins.sbt** :
```
dependsOn(ProjectRef(uri("file://home/ubuntu/src/sbt-common"), "sbt-common"))
```
or
```
dependsOn(ProjectRef(uri("ssh://git@github.com/l15k4/sbt-common"), "sbt-common"))
```
