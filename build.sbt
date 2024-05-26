ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.1"

lazy val root = (project in file("."))
  .settings(
    name := "ft_ality",
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
  )

