package org.plsk.fp.typeclass.fp

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl._
import org.plsk.fp.typeclass.Fixtures._
import org.plsk.fp.typeclass.model.{DomainEvent, ProgramCancelled, ProgramLiked, ProgramStarted, RawEvent}
import DomainEventFormatter.{DomainEventJsonFormatter, ProgramStartedJsonFormatter, ProgramCancelledJsonFormatter, ProgramLikedJsonFormatter}
import akka.stream.FlowShape

object StreamReaderApp extends App {

  implicit val system = ActorSystem("StreamReaderApp")
  implicit val ec = system.dispatcher


  // build RawEvent
  val toRawEvent: Flow[String, RawEvent, NotUsed] =
    Flow.fromFunction {
      msg =>
        EventParser.parseMessage(msg)
          .fold(handleError[RawEvent], identity)
    }

  // raw event to domainEvent
  val toDomainEvent: Flow[RawEvent, DomainEvent, NotUsed] =
    Flow.fromFunction {
      rawEvent =>
        EventParser.readToModel(rawEvent)
          .fold(handleError[DomainEvent], identity)
    }

  val filterProgramStartedFlow: Flow[DomainEvent, ProgramStarted, NotUsed] = Flow[DomainEvent].collect {
    case e: ProgramStarted =>
      println(s"take program started event [$e]")
      e
  }
  val filterProgramCancelledFlow: Flow[DomainEvent, ProgramCancelled, NotUsed] = Flow[DomainEvent].collect {
    case e: ProgramCancelled =>
      println(s"take program cancelled event [$e]")
      e
  }
  val filterProgramLikedFlow: Flow[DomainEvent, ProgramLiked, NotUsed] = Flow[DomainEvent].collect {
    case e: ProgramLiked =>
      println(s"take program liked event [$e]")
      e
  }
//  def filterProgram[Event](str: String): Flow[DomainEvent, Event, NotUsed] = Flow[DomainEvent].collect {
//    case e if e.isInstanceOf[Event] =>
//      println(s"[$str]take program event [$e]")
//      e.asInstanceOf[Event]
//  }

  // domainEvent to message
  def formatOutput[Event <: DomainEvent : DomainEventJsonFormatter]: Flow[Event, String, NotUsed] =
    Flow.fromFunction(EventParser.toMessage[Event])

  // process event
  val processEvent: Flow[DomainEvent, String, NotUsed] = Flow.fromGraph(GraphDSL.create() { implicit builder: GraphDSL.Builder[NotUsed] =>
    import GraphDSL.Implicits._

    val bcast = builder.add(Broadcast[DomainEvent](3))
    val merge = builder.add(Merge[String](3))

//    bcast ~> filterProgram[ProgramStarted]("started") ~> formatOutput[ProgramStarted] ~> merge
//    bcast ~> filterProgram[ProgramCancelled]("cancelled") ~> formatOutput[ProgramCancelled] ~>  merge
//    bcast ~> filterProgram[ProgramLiked]("liked") ~> formatOutput[ProgramLiked] ~>  merge
    bcast ~> filterProgramStartedFlow ~> formatOutput[ProgramStarted] ~> merge
    bcast ~> filterProgramCancelledFlow ~> formatOutput[ProgramCancelled] ~>  merge
    bcast ~> filterProgramLikedFlow ~> formatOutput[ProgramLiked] ~>  merge

    FlowShape(bcast.in, merge.out)
  })

  // read dataset
  val source: Source[String, NotUsed] = Source(sourceInput)

  // run !
  val done =
    source
      .via(toRawEvent)
      .via(toDomainEvent)
      .via(processEvent)
      .runForeach(println)

  done.onComplete(_ => system.terminate())

  // utility functions

  def handleError[T](error: Throwable): T = throw error
}
