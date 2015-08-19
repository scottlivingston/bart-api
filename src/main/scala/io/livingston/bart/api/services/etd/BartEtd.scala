package io.livingston.bart.api.services.etd

import akka.actor.{ActorRef, Actor, Props}
import spray.client.pipelining._
import spray.http.HttpRequest

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object BartEtd {
  case class CallBart(reflector: ActorRef)

  def props(): Props = Props(new BartEtd)
}

class BartEtd extends Actor {

  import BartEtd._

  def receive: Receive = {
    case CallBart(reflector) => {
      val pipeline: HttpRequest => Future[String] = sendReceive ~> unmarshal[String]

      val response: Future[String] = pipeline(Get("http://api.bart.gov/api/etd.aspx?cmd=etd&orig=RICH&key=MW9S-E7SL-26DU-VV8V"))

      response onComplete {
        case Success(r: String) => {

          val departures = BartEtdUnmarshaller.unmarshall(r)
          reflector ! EtdService.ReturnBartResult(departures)
        }
        case Failure(_) =>
          reflector ! _
      }
    }
  }
}
