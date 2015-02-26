package org.objectscape.picogo

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{Props, ActorSystem}

/**
 * Created by Oliver Plohmann on 26.02.2015.
 */
object PicoGo {

  lazy val system = ActorSystem("PicoGo")
  val nameCounter = new AtomicInteger(0)

  def newChannel[ItemType](block: ItemType => Unit, name: String = "") : Channel[ItemType] = {
    val size = new AtomicInteger(0)
    val actorName = if(name.isEmpty) {
      nameCounter.incrementAndGet().toString
    } else {
      name
    }
    val actorRef = system.actorOf(Props(new ChannelActor[ItemType](block, size)), name = actorName)
    new Channel[ItemType](actorRef, size)
  }

}
