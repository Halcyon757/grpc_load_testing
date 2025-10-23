package perf.load.cases

import io.gatling.core.Predef._
import com.github.phisgr.gatling.grpc.Predef._
import io.gatling.core.structure.ChainBuilder
import io.grpc.Status
import io.gatling.commons.validation.Success

import auth._
import auth.AuthGrpc

object GrpcMethods {

  val register: ChainBuilder =
    exec(
      grpc("Register")
        .rpc(AuthGrpc.METHOD_REGISTER)
        .payload { session =>
          RegisterRequest(
            email    = session("email").as[String],
            password = session("password").as[String]
          )
        }
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
        .payload { session =>
          LoginRequest(
            email    = session("email").as[String],
            password = session("password").as[String],
            appId    = session("appId").as[Int]
          )
        }
        .check(statusCode is Status.Code.OK)
        .check(
          extract[LoginResponse, String](r => Success(Option(r.token)))
            .notNull
        )
    )

  val isAdminFalse: ChainBuilder =
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