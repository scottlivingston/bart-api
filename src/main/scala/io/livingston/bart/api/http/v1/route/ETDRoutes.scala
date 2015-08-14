package io.livingston.bart.api.http.v1.route

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import io.livingston.bart.api.http.BaseRoute
import io.livingston.bart.api.services.etd.ETD
import spray.routing.Route
import scala.concurrent.duration._
import scala.util.{Success, Failure}

import scala.concurrent.ExecutionContext.Implicits.global

object ETDRoutes extends BaseRoute {
  def routes(etd: ActorRef): Route = path("etd") {
    get {
      onComplete[Any] {
        implicit val timeout = Timeout(10.seconds)

        etd ? ETD.GetEtd()
      } {
        case Success(res) => res match {
          case s: String => success(s)
        }
        case Failure(ex) => {
          error("Shit broke")
        }
      }
    }
  }
}
