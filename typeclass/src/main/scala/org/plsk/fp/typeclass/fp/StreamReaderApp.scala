package org.plsk.fp.typeclass.fp

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl._
import org.plsk.fp.typeclass.Fixtures._
import org.plsk.fp.typeclass.model.{DomainEvent, RawEvent}

object StreamReaderApp extends App {

  implicit val system = ActorSystem("StreamReaderApp")
  implicit val ec = system.dispatcher

  // read dataset
  val source: Source[String, NotUsed] = Source(sourceInput)

  // build RawEvent
  val toRawEvent: Flow[String, RawEvent, NotUsed] =
    Flow.fromFunction{
      msg =>
        EventParser.parseMessage(msg)
          .fold(handleError[RawEvent], identity)
    }

  // raw event to domainEvent
  val toDomainEvent: Flow[RawEvent, DomainEvent, NotUsed] =
    Flow.fromFunction{
      rawEvent =>
        EventParser.readToModel(rawEvent)
          .fold(handleError[DomainEvent], identity)
    }

  // process event
  val processEvent: Flow[DomainEvent, DomainEvent, NotUsed] =
    Flow.fromFunction(identity)

  // domainEvent to message
  val formatOutput: Flow[DomainEvent, String, NotUsed] =
    Flow.fromFunction {
      event => EventParser.toMessage(event)
    }

  // run !
  val done =
    source
      .via(toRawEvent)
      .via(toDomainEvent)
      .via(processEvent)
      .via(formatOutput)
      .runForeach(println)

  done.onComplete(_ => system.terminate())

  // utility functions

  def handleError[T](error: Throwable): T = throw error
}
