package perf.load

import io.gatling.core.Predef._
import com.github.phisgr.gatling.grpc.Predef._
import io.grpc.ManagedChannelBuilder
import scala.concurrent.duration._

class load extends Simulation {

  val grpcProtocol =
    grpc(ManagedChannelBuilder.forAddress("localhost", 44044).usePlaintext())
      .shareChannel

  setUp(
    GrpcScenario.scn.inject(
      incrementUsersPerSec(5)        // +5 rps за ступень
        .times(5)                    // 5 ступеней
        .eachLevelLasting(1.minute)  // по 1 минуте
        .separatedByRampsLasting(30.seconds)
        .startingFrom(5)             // стартовая нагрузка
    )
  ).protocols(grpcProtocol)
    .maxDuration(10.minutes)
}
