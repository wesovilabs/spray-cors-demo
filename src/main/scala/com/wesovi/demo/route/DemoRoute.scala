package com.wesovi.demo.route

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.util.Failure
import scala.util.Success
import com.wesovi.demo.ActorLocator
import com.wesovi.demo.api.ApiRoute
import akka.actor.ActorRef
import akka.actor.ActorRefFactory
import akka.pattern.ask
import akka.pattern.ask
import akka.util.Timeout
import spray.http.MediaTypes
import spray.http.StatusCodes
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing._
import spray.routing.Directive.pimpApply
import spray.util.LoggingContext


 
object DemoRoute {

  
}

class DemoRoute (implicit ec: ExecutionContext, actorRefFactory:ActorRefFactory,log: LoggingContext) extends ApiRoute{
  
  val route: Route =
  path("demo" / "status"){
    get{
      respondWithMediaType(MediaTypes.`application/json`) {
        complete(StatusCodes.Accepted, "{'status':'ok'}")
      }
    }
  }
} 