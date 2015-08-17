package io.livingston.bart.api.http.v1.route

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import io.livingston.bart.api.http.BaseRoute
import io.livingston.bart.api.services.etd.{Etd, EtdService}
import spray.routing.Route

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object ETDRoutes extends BaseRoute {
  def routes(etd: ActorRef): Route = path("etd") {
    get {
      onComplete[Any] {
        implicit val timeout = Timeout(10.seconds)

        etd ? EtdService.GetEtd()
      } {
        case Success(res) => res match {
          case s: Etd => complete(s)
        }
        case Failure(ex) => {
          error("Shit broke")
        }
      }
    }
  }
}
