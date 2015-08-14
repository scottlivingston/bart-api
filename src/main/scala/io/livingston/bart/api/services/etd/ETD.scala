package io.livingston.bart.api.services.etd

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import io.livingston.bart.api.services.etd.ETD.GetEtd

object ETD {
  case class GetEtd()
  case class Success()

  def boot(system: ActorSystem): ActorRef = system.actorOf(ETD.props, "etd")

  def props() = Props(new ETD)
}

class ETD extends Actor{
  def receive = {
    case GetEtd() => {
      sender() ! "WooHoo!"
    }
  }
}
