ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

lazy val root = (project in file("."))
    .settings(
        name := "ScalaAkka",
        libraryDependencies ++= Seq(
            "com.typesafe.akka" %% "akka-actor-typed" % "2.6.20",
            "com.typesafe.akka" %% "akka-stream" % "2.6.20",
            "com.typesafe.akka" %% "akka-slf4j" % "2.6.20"
        )
    )
