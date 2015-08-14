package io.livingston.bart.api.http.v1

import akka.actor.ActorRef
import io.livingston.bart.api.http.BaseRoute
import io.livingston.bart.api.http.v1.route.ETDRoutes
import spray.routing.Route

trait BartRoutes extends BaseRoute {
  def route(etd: ActorRef): Route = {
    ETDRoutes.routes(etd)
  }
}
