package org.objectscape.picogo

import java.util.concurrent.LinkedTransferQueue


/**
 * Created by Oliver Plohmann on 26.02.2015.
 */
object PicoGoTest extends App {

  val blockingQueue = new LinkedTransferQueue[Int]()

  val greetingSize = (n: Int) => {
    blockingQueue.add(n)
    ()
  }
  val greetingSizeChannel = PicoGo.newChannel(greetingSize)

  val greeting = (name: String) => {
    val greeting = "Hello " + name + "!"
    greetingSizeChannel.send(greeting.size)
  }

  val greetingChannel = PicoGo.newChannel(greeting)

  greetingChannel.send("Olli")
  greetingChannel.send("dude")
  greetingChannel.send("you")

  println(blockingQueue.take())

  while(!blockingQueue.isEmpty) {
    println(blockingQueue.poll())
  }

  println("done")

}
