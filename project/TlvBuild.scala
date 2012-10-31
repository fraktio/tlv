import sbt._
import sbt.Keys._

object TlvBuild extends Build {

  lazy val tlv = Project(
    id = "tlv",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "TLV",
      organization := "fi.ln",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.9.2"
      // add other settings here
    )
  )
}
