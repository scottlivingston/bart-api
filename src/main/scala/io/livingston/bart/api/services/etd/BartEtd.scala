package io.livingston.bart.api.services.etd

import akka.actor.{Props, Actor}

object BartEtd {
  case class CallBart()

  def props(): Props = Props(new BartEtd)
}

class BartEtd extends Actor {
  import BartEtd._

  def receive: Receive = {
    case CallBart => ??? //make api call to bart using some api thing
  }
}
