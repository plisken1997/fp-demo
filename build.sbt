import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "org.plsk"
ThisBuild / organizationName := "fp"

lazy val typeclass = (project in file("typeclass"))
  .settings(
    name := "type class demo",
    libraryDependencies += scalaTest % Test,
  )

lazy val root = (project in file("."))
  .settings(
    name := "fp demo",
    libraryDependencies += scalaTest % Test
  ).aggregate(typeclass)
