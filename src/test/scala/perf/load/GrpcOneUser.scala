package perf.load

import io.gatling.core.Predef._
import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.grpc.protocol.GrpcProtocol
import io.grpc.ManagedChannelBuilder
import scala.concurrent.duration._

class GrpcOneUser extends Simulation {
  private val grpcProtocol: GrpcProtocol =
    grpc(ManagedChannelBuilder.forAddress("localhost", 44044).usePlaintext())
      .shareChannel

  setUp(
    GrpcScenario.scn.inject(atOnceUsers(1))
  ).protocols(grpcProtocol)
    .maxDuration(2.minutes)
}