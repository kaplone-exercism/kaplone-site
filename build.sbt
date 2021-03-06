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

// https://mvnrepository.com/artifact/javax.websocket/javax.websocket-api
libraryDependencies += "javax.websocket" % "javax.websocket-api" % "1.1" % "provided"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
