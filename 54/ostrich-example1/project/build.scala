import sbt._
import Keys._

object build extends Build {
  lazy val example1 = Project (
    "example1",
    file("."),
    settings = Defaults.defaultSettings ++ Seq(
      scalaVersion := "2.8.1",
      libraryDependencies ++= Seq(
        "com.twitter" % "ostrich" % "4.9.3"
      ),
      resolvers ++= Seq(
        "twitter" at "http://maven.twttr.com/"
      ),
      fork in run := true
    )
  )
}

// vim: set ts=2 sw=2 et:
