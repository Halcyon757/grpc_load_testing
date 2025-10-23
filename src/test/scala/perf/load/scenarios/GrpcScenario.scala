package perf.load

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.load.cases.GrpcMethods

object GrpcScenario {
  val scn: ScenarioBuilder =
    scenario("GrpcScenario")
      .exec(GrpcMethods.register)
      .exec(GrpcMethods.login)
      .exec(GrpcMethods.isAdmin)
}
