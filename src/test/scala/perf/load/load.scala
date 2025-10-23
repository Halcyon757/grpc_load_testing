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
      incrementConcurrentUsers(200)
        .times(5)
        .eachLevelLasting(1.minute)
        .separatedByRampsLasting(30.seconds)
        .startingFrom(200)
    )
  ).protocols(grpcProtocol)
    .maxDuration(10.minutes)
}
