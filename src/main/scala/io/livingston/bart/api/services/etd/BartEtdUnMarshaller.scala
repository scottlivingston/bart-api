package io.livingston.bart.api.services.etd

import java.util.ArrayList

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.DomDriver

case class TrainData(minutes: String, 
                     platform: Int,
                     direction: String,
                     length: Int,
                     color: String,
                     hexcolor: String,
                     bikeflag: Boolean)

case class Estimates(destination: String,
                     abbreviation: String,
                     estimates: ArrayList[TrainData])

case class Station(name: String, abbr: String,
                   etds: ArrayList[Estimates])

case class Departures(uri: String,
                date: String,
                time: String,
                station: Station,
                message: String)

object BartEtdUnmarshaller {
  def unmarshall(xml: String): Departures = {
    val xstream = new XStream(new DomDriver)

    xstream.alias("root", classOf[Departures])
    xstream.alias("etd", classOf[Estimates])
    xstream.alias("estimate", classOf[TrainData])
    xstream.aliasField("minutes", classOf[TrainData], "minutes")
    xstream.addImplicitCollection(classOf[Station], "etds")
    xstream.addImplicitCollection(classOf[Estimates], "estimates")

    xstream.fromXML(xml).asInstanceOf[Departures]
  }
}
