val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "reversi",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.15",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test",
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.1.0",

    coverageExcludedPackages := "<empty>;.*views.*;",
    coverageExcludedFiles := "Main"
  )
