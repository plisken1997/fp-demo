package org.plsk.fp.typeclass.fp

import JsonParser._
import org.plsk.fp.typeclass.fp.DomainEventFormatter.DomainEventJsonFormatter
import org.plsk.fp.typeclass.model.{DomainEvent, ProgramCancelled, ProgramLiked, ProgramStarted, RawEvent}

import scala.util.{Failure, Try}

object EventParser extends JsonEventParser {

  def parseMessage(msg: String): Try[RawEvent] = parseEvent[RawEvent](msg)

  def readToModel(event: RawEvent): Try[DomainEvent] =
    event.eventType match {
      case "program_started" => parseEvent[ProgramStarted](event.payload)
      case "program_cancelled" => parseEvent[ProgramCancelled](event.payload)
      case "program_liked" => parseEvent[ProgramLiked](event.payload)
      case _ =>
        Failure(new Exception(s"unhandled type [${event.eventType}]"))
    }

  def toMessage[Event <: DomainEvent : DomainEventJsonFormatter](domainEvent: Event): String =
    implicitly[DomainEventJsonFormatter[Event]].format(domainEvent)
}
