package com.wesovi.demo

import akka.actor.ActorSystem
import com.wesovi.demo.util.ConfigHolder


trait Core {
  implicit def system: ActorSystem
}

trait BootedCore extends Core {
  implicit lazy val system = ActorSystem("microservice-system")

  sys.addShutdownHook(system.shutdown())
}
trait CoreActors extends ConfigHolder {
  this: Core =>

}