import sbt._

object Dependencies {
  val gatlingVersion = "3.9.5"

  lazy val gatling: Seq[ModuleID] = Seq(
    "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion,
    "io.gatling"            % "gatling-test-framework"     % gatlingVersion % Test
  )

  lazy val gatlingPicatinny: Seq[ModuleID] = Seq("org.galaxio" %% "gatling-picatinny" % "0.16.0")
  lazy val janino: Seq[ModuleID]           = Seq("org.codehaus.janino" % "janino" % "3.1.12")
  lazy val amqpPlugin: Seq[ModuleID]       = Seq("org.galaxio" %% "gatling-amqp-plugin" % "0.12.0")
  lazy val kafkaPlugin: Seq[ModuleID]      = Seq("org.galaxio" %% "gatling-kafka-plugin" % "0.14.0")
  lazy val kafkaSerializer: Seq[ModuleID]  = Seq("io.confluent" % "kafka-avro-serializer" % "7.6.0")
  lazy val avro4s: Seq[ModuleID]           = Seq("com.sksamuel.avro4s" %% "avro4s-core" % "4.1.2")
  lazy val jdbcPlugin: Seq[ModuleID]       = Seq("org.galaxio" %% "gatling-jdbc-plugin" % "0.12.0")
  lazy val postgresJdbc: Seq[ModuleID]     = Seq("org.postgresql" % "postgresql" % "42.5.6")

  lazy val grpcDeps: Seq[ModuleID] = Seq(
    "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
    "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
    "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
  )
}

