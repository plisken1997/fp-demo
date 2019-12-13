package org.plsk.fp.typeclass.fp

import org.plsk.fp.typeclass.model.{DomainEvent, ProgramStarted}

trait DomainEventFormatter[Event <: DomainEvent, Output] {
  def format(event: Event): Output
}

object DomainEventFormatter {

  type JSON = String

  type DomainEventJsonFormatter[Event <: DomainEvent] = DomainEventFormatter[Event, JSON]

  implicit object ProgramStartedJsonFormatter extends DomainEventJsonFormatter[ProgramStarted] {
    override def format(event: ProgramStarted): JSON =
      s"""
        |{
        |  "eventType": "program_started,
        |  "payload": {
        |    "raw": "$event"
        |  }
        |}
        |""".stripMargin
  }

}