## sbt-common

Provides standardized configuration and dependencies to use in similar scala based projects.

This plugin is published to sonatype repository.
Or you can use plugin directly in which case you need just either of these in **project/plugins.sbt** :
```
dependsOn(ProjectRef(uri("file://path/to/sbt-common"), "sbt-common"))
dependsOn(ProjectRef(uri("https://github.com/l15k4/sbt-common.git"), "sbt-common"))
```
To get a specific version, do : `../sbt-common.git#vx.y.z`