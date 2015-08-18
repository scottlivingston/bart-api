package io.livingston.bart.api.services.etd

import akka.actor.{ActorRef, Actor, Props}
import akka.event.Logging
import spray.client.pipelining._
import spray.http.{HttpRequest, HttpResponse}

import scala.concurrent.Future
import scala.util.{Failure, Success}

import scala.concurrent.ExecutionContext.Implicits.global

object BartEtd {
  case class CallBart(reflector: ActorRef)

  def props(): Props = Props(new BartEtd)
}

class BartEtd extends Actor {
  val log = Logging(context.system, this)

  import BartEtd._

  def receive: Receive = {
    case CallBart(reflector) => {
      val pipeline: HttpRequest => Future[HttpResponse] = sendReceive

      val response: Future[HttpResponse] = pipeline(Get("http://spray.io/"))

      response onComplete {
        case Success(r: HttpResponse) =>
          log.info("call worked")
          sender() ! EtdService.ReturnBartResult(reflector, r)
        case Failure(_) =>
          log.info("call failed")
          sender() ! _
      }
    }
  }
}
