package perf.load.data

import io.gatling.core.feeder.Feeder

object Feeders {
  val authFeeder: Feeder[Any] = Iterator.continually {
    Map(
      "email"    -> DataGen.randomEmail(),
      "password" -> DataGen.randomPassword(),
      "appId"    -> DataGen.randomAppId()
    )
  }
}
