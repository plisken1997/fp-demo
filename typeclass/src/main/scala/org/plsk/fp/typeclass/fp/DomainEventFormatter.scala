package org.plsk.fp.typeclass.fp

import org.plsk.fp.typeclass.model.{DomainEvent, ProgramCancelled, ProgramLiked, ProgramStarted}

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

  implicit object ProgramLikedJsonFormatter extends DomainEventJsonFormatter[ProgramLiked] {
    override def format(event: ProgramLiked): JSON =
      s"""
         |{
         |  "eventType": "program_liked,
         |  "payload": {
         |    "raw": "$event"
         |  }
         |}
         |""".stripMargin
  }

  implicit object ProgramCancelledJsonFormatter extends DomainEventJsonFormatter[ProgramCancelled] {
    override def format(event: ProgramCancelled): JSON =
      s"""
         |{
         |  "eventType": "program_cancelled,
         |  "payload": {
         |    "raw": "$event"
         |  }
         |}
         |""".stripMargin
  }
}