package com.wesovi.demo.api

import spray.routing._
import akka.actor.{ActorRef, ActorLogging, Props}
import akka.io.IO
import scala.concurrent.ExecutionContext.Implicits.global
import spray.can.Http
import spray.json.DefaultJsonProtocol
import spray.util.LoggingContext
import spray.httpx.SprayJsonSupport
import spray.http.HttpHeaders.{`Access-Control-Allow-Origin`, `Access-Control-Allow-Credentials`, `Access-Control-Allow-Headers`, `Access-Control-Allow-Methods`}
import spray.http.HttpMethods._
import spray.http.{StatusCodes, HttpOrigin, SomeOrigins}
import akka.actor.actorRef2Scala
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply
import com.wesovi.demo.route.DemoRoute
import com.wesovi.demo.util.ConfigHolder
import com.wesovi.demo.Core
import com.wesovi.demo.CoreActors


trait Api extends Directives with RouteConcatenation  with ConfigHolder{
  this: CoreActors with Core => 

  val routes =
    pathPrefix("api") { 
      new DemoRoute().route
    } 
    val rootService = system.actorOf(ApiService.props(config.getString("hostname"), config.getInt("port"), routes))
}

object ApiService {
  
  def props(hostname: String, port: Int, routes: Route) = Props(classOf[ApiService], hostname, port, routes)
}

class ApiService(hostname: String, port: Int, route: Route) extends HttpServiceActor with ActorLogging {
  IO(Http)(context.system) ! Http.Bind(self, hostname, port)

  def receive: Receive = runRoute(route)
}

object ApiRoute {
  case class Message(message: String)

  object ApiRouteProtocol extends DefaultJsonProtocol {
    implicit val messageFormat = jsonFormat1(Message)
  }

  object ApiMessages {
    val UnknownException = "Unknown exception"
    val UnsupportedService = "Sorry, provided service is not supported."
  }
}

abstract class ApiRoute(implicit log: LoggingContext) extends Directives with SprayJsonSupport {

  import com.wesovi.demo.api.ApiRoute.{ApiMessages, Message}
  import com.wesovi.demo.api.ApiRoute.ApiRouteProtocol._

  
}