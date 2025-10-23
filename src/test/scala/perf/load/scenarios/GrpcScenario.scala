package perf.load

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.load.cases.GrpcMethods
import perf.load.data.Feeders

object GrpcScenario {
  val scn: ScenarioBuilder =
    scenario("GrpcScenario")
      .feed(Feeders.authFeeder)
      .exec(GrpcMethods.register)
      .exec(GrpcMethods.login)
      .exec(GrpcMethods.isAdminFalse)
}
