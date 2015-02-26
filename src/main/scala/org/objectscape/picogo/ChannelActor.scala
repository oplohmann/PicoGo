package org.objectscape.picogo

/**
 * Created by Oliver Plohmann on 24.02.2015.
 */

import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger

import akka.actor.Actor

class ChannelActor[ItemType](val block: ItemType => Unit, val size: AtomicInteger) extends Actor {

  def receive = {
    case item: ItemType => run(item)
    case _    => println(actorName + ": huh?")
  }

  def run(item: ItemType): Unit = {
    block.apply(item)
    size.decrementAndGet()
  }

  def actorName = self.path.name

}