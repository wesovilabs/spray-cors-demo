package com.wesovi.demo

import akka.actor.ActorRefFactory
import akka.actor.ActorSystem
import akka.actor.Props

object ActorLocator {

  implicit val system = ActorSystem("AccountSystem-1")
  def accountRemoteActor(implicit ctx: ActorRefFactory) = system.actorSelection("akka.tcp://AccountRemoteSystem@127.0.0.1:20001/account/AccountFacade")
  
}