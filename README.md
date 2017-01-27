**sbt-common** provides standardized configuration and dependencies to use in similar scala based projects.

```
//in file project/plugins.sbt

addSbtPlugin("net.globalwebindex" % "sbt-common" % "x.y.z")
// dependsOn(ProjectRef(uri("ssh://git@github.com/l15k4/sbt-common"), "sbt-common"))
// dependsOn(ProjectRef(uri("file://...."), "sbt-common"))

```
