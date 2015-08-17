import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import io.livingston.bart.api.http.v1.RestApi
import io.livingston.bart.api.services.etd.EtdService
import spray.can.Http

import scala.concurrent.duration.FiniteDuration

object BartRestBoot extends App {
  implicit val system = ActorSystem("rest")
  implicit val timeout = Timeout.durationToTimeout(FiniteDuration(1, "second"))

  val etd = EtdService.boot(system)

  val service = system.actorOf(Props(new RestApi(etd)))

  IO(Http) ? Http.Bind(service, "127.0.0.1", 8080)
}
