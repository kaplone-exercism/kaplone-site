name := """kaplone_site"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  "org.postgresql" % "postgresql" % "9.4.1212.jre7",
  cache,
  javaWs
)

//libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1212-jdbc42"
//libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
