import sbt._
import Keys._

object MacroBuild extends Build {
  lazy val main = Project("main", file(".")) dependsOn(macroSub) settings (
//      scalacOptions ++= Seq("-Ymacro-debug-lite", "-print")
    )
  lazy val macroSub = Project("macro", file("macro")) settings(
    libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-reflect" % _),
    libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _)
  )
}