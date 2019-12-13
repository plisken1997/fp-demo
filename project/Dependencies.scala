import sbt._

object Dependencies {

  import DependenciesVersion._

  lazy val scalaTest = Seq("org.scalatest" %% "scalatest" % scalaTestVersion)
  lazy val akkaStream = Seq("com.typesafe.akka" %% "akka-stream" % akkaStreamVersion)

  lazy val circe = Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  )
    .map(_ % circeVersion)
}

object DependenciesVersion {

  val scalaTestVersion = "3.0.8"
  val akkaStreamVersion = "2.6.1"
  val circeVersion = "0.12.0"
}
