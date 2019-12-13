package org.plsk.fp.typeclass.oop

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl.{Sink, _}
import org.plsk.fp.typeclass.Fixtures._
import org.plsk.fp.typeclass.domain.event.{DomainEvent, RawEvent}

object StreamReaderApp extends App {

  implicit val system = ActorSystem("StreamReaderApp")
  implicit val ec = system.dispatcher

  val eventParser = DI.provideEventParser()

  // read dataset
  val source: Source[String, NotUsed] = Source(sourceInput)

  // build RawEvent
  val toRawEvent: Flow[String, RawEvent, NotUsed] =
    Flow.fromFunction{
      msg =>
        eventParser.parseMesage(msg)
          .fold(handleError[RawEvent], identity)
    }

  // raw event to domainEvent
  val toDomainEvent: Flow[RawEvent, DomainEvent, NotUsed] =
    Flow.fromFunction{
      rawEvent =>
        eventParser.readToModel(rawEvent)
          .fold(handleError[DomainEvent], identity)
    }

  // process event
  val processEvent: Flow[DomainEvent, DomainEvent, NotUsed] =
    Flow.fromFunction(identity)

  // domainEvent to message
  val formatOutput: Flow[DomainEvent, String, NotUsed] =
    Flow.fromFunction {
      event => eventParser.toMessage(event)
    }

  // run !
  val done =
    source
      .via(toRawEvent)
      .via(toDomainEvent)
      .via(processEvent)
      .via(formatOutput)
      .runWith(Sink.seq[String])

  done.onComplete(_ => system.terminate())

  def handleError[T](error: Throwable): T = throw error
}
