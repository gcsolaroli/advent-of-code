import Dependencies._

ThisBuild / organization := "it.imolab.adventOfCode"
ThisBuild / scalaVersion := "3.1.0"

ThisBuild / scalacOptions ++=
  Seq(
    "-deprecation",
    "-feature",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings",
    "-Yexplicit-nulls", // experimental (I've seen it cause issues with circe)
    "-Ykind-projector",
    "-Ysafe-init", // experimental (I've seen it cause issues with circe)
  ) ++ Seq("-rewrite", "-indent") ++ Seq("-source", "future")

// lazy val `root` =
//   project
//     .in(file("."))

lazy val `AdventOfCode` = project
    .in(file("."))
    .settings(name := "AdventOfCode")
    .aggregate(`Year_2020`, `Year_2021`)
    dependsOn(`Year_2020`, `Year_2021`)

lazy val `Year_2021` =
  project
    .in(file("2021"))
    .settings(name := "2021")
    .settings(commonSettings)
    .settings(dependencies)

lazy val `Year_2020` =
  project
    .in(file("2020"))
    .settings(name := "2020")
    .settings(commonSettings)
    .settings(dependencies)

lazy val commonSettings = commonScalacOptions ++ Seq(
  update / evictionWarningOptions := EvictionWarningOptions.empty
)

lazy val commonScalacOptions = Seq(
  Compile / console / scalacOptions --= Seq(
    "-Wunused:_",
    "-Xfatal-warnings",
  ),
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value,
)

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    // main dependencies
  ),
  libraryDependencies ++= Seq(
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-15`,
  ).map(_ % Test),
)

// cancelable in Global := true
