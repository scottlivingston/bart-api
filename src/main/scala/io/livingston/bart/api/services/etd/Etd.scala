package io.livingston.bart.api.services.etd

import spray.json.DefaultJsonProtocol

case class Etd(destination: String, id: Int)

//object EtdProtocol extends DefaultJsonProtocol {
//  implicit val etdFormat = jsonFormat2(Etd)
//}
