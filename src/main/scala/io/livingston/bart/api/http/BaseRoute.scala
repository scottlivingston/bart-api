package io.livingston.bart.api.http

import org.json4s.{DefaultFormats, Formats}
import spray.http.{StatusCodes, HttpResponse, HttpEntity}
import spray.httpx.SprayJsonSupport
import spray.routing.SimpleRoutingApp

trait BaseRoute extends SprayJsonSupport with SimpleRoutingApp {
  implicit def json4sFormats: Formats = DefaultFormats

  def success(entity: HttpEntity) = complete(HttpResponse(StatusCodes.OK, entity))
  def error  (entity: HttpEntity) = complete(HttpResponse(StatusCodes.InternalServerError, entity))
}
