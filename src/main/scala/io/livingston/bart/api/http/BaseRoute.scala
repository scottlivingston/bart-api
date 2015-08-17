package io.livingston.bart.api.http

import org.json4s.{DefaultFormats, Formats}
import spray.http.{HttpEntity, HttpResponse, StatusCodes}
import spray.httpx.Json4sJacksonSupport
import spray.routing.SimpleRoutingApp

trait BaseRoute extends Json4sJacksonSupport with SimpleRoutingApp {
  implicit def json4sJacksonFormats: Formats = DefaultFormats

  def success(entity: HttpEntity) = complete(HttpResponse(StatusCodes.OK, entity))
  def error  (entity: HttpEntity) = complete(HttpResponse(StatusCodes.InternalServerError, entity))
}
