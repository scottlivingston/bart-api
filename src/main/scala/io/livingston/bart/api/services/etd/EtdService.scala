package io.livingston.bart.api.services.etd

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import io.livingston.bart.api.services.etd.EtdService.GetEtd

object EtdService {
  case class GetEtd()
  case class Success()

  def boot(system: ActorSystem): ActorRef = system.actorOf(EtdService.props, "etd")

  def props() = Props(new EtdService)
}

class EtdService extends Actor {
  def receive = {
    case GetEtd() => {
      sender() ! Etd("test", 1)
    }
  }
}
