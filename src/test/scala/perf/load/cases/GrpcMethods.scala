package perf.load.cases

import io.gatling.core.Predef._
import com.github.phisgr.gatling.grpc.Predef._
import io.gatling.core.structure.ChainBuilder
import io.grpc.Status
import io.gatling.commons.validation.Success

import auth._
import auth.AuthGrpc

object GrpcMethods {

  // TODO: сделать рандомные фидеры

  val register: ChainBuilder =
    exec(
      grpc("Register")
        .rpc(AuthGrpc.METHOD_REGISTER)
        .payload(
          RegisterRequest(
            email = "test@example.com",
            password = "password123"
          )
        )
        .check(statusCode is Status.Code.OK)
        .check(
          extract[RegisterResponse, Long](r => Success(Some(r.userId)))
            .saveAs("userId")
        )
    )

  val login: ChainBuilder =
    exec(
      grpc("Login")
        .rpc(AuthGrpc.METHOD_LOGIN)
        .payload(
          LoginRequest(
            email = "test@example.com",
            password = "password123",
            appId = 1
          )
        )
        .check(statusCode is Status.Code.OK)
        .check(
          extract[LoginResponse, String](r => Success(Option(r.token)))
            .notNull
        )
    )

  val isAdmin: ChainBuilder =
    exec(
      grpc("IsAdmin")
        .rpc(AuthGrpc.METHOD_IS_ADMIN)
        .payload { session =>
          IsAdminRequest(userId = session("userId").as[Long])
        }
        .check(statusCode is Status.Code.OK)
        .check(
          extract[IsAdminResponse, Boolean](r => Success(Some(r.isAdmin)))
            .is(false)
        )
    )
}