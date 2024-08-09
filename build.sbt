ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.1"

lazy val root = (project in file("."))
  .settings(
    name := "ft_ality",

    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
      "com.lihaoyi" %% "os-lib" % "0.10.1",
      "io.circe" %% "circe-yaml" % "1.15.0",
       "io.circe" %% "circe-generic" % "0.14.7",
       "io.circe" %% "circe-parser" % "0.14.9"
    )
  )

