package org.objectscape.picogo

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.ActorRef


/**
 * Created by Oliver Plohmann on 24.02.2015.
 */
class Channel[ItemType](val actor: ActorRef, val _size: AtomicInteger) {

  def send(item: ItemType): Unit = {
    _size.incrementAndGet()
    actor ! item
  }

  def size: Int = _size.get()

}
