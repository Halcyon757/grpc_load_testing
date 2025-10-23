import Dependencies._

enablePlugins(GatlingPlugin)
inConfig(Test)(sbtprotoc.ProtocPlugin.protobufConfigSettings)

//protobuf stuff
Test / PB.targets := Seq(
  scalapb.gen(flatPackage = true) -> (Test / sourceManaged).value
)


lazy val root = (project in file("."))
  .settings(
    inThisBuild(
      List(
        organization := "perf",
        scalaVersion := "2.13.14",
        version := "0.1.0",
      ),
    ),
    name := "load",
    libraryDependencies ++= gatling,
    libraryDependencies ++= gatlingPicatinny,
    libraryDependencies ++= janino,
    libraryDependencies ++= amqpPlugin,
    libraryDependencies ++= kafkaPlugin,
    resolvers += "confluent" at "https://packages.confluent.io/maven/",
    libraryDependencies ++= kafkaSerializer,
    libraryDependencies ++= avro4s,
    libraryDependencies ++= jdbcPlugin,
    libraryDependencies ++= postgresJdbc,
    libraryDependencies ++= grpcDeps,
    libraryDependencies += "com.google.code.gson" % "gson" % "2.13.1",
    libraryDependencies ++= Seq(
      "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
      "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
      "com.thesamet.scalapb" %% "scalapb-json4s" % "0.12.0",
      "io.github.scalapb-json" %% "scalapb-circe" % "0.16.0",
      "com.github.phisgr" % "gatling-ext" % "0.5.0",
      "org.scalatest" %% "scalatest" % "3.2.19" % "test",
      "com.github.phisgr" % "gatling-grpc" % "0.17.0" % "test"
    ),
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-Xfatal-warnings",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-language:implicitConversions",
      "-language:higherKinds",
      "-language:existentials",
      "-language:postfixOps"
    ),
  )
