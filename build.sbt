name := "schema-test"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-io" % "1.3.2",
  "com.github.fge" % "json-schema-validator" % "2.2.5",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",

  "ch.qos.logback" % "logback-classic" % "1.2.3"
)
