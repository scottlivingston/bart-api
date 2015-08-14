package io.livingston.bart.api.http.v1

import akka.actor.{Actor, ActorRef}
import spray.routing.HttpService

class RestApi(etd: ActorRef) extends Actor with HttpService with BartRoutes  {
  def receive: Receive = runRoute(route(etd))
}
