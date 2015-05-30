package com.wesovi

import akka.actor.ActorRef
import scala.collection.immutable.Map

package object demo {
  
  type Services = Map[String, ActorRef]
  
  object Services {
    def empty[A, B]: Map[A, B] = Map.empty
  }
  
} 