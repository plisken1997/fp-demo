package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.model._

import scala.util.{Failure, Try}

class EventParser(
  private val rawEventReader: EventParserStrategy[RawEvent],
  private val programStartedReader: EventParserStrategy[ProgramStarted],
  private val programCancelledReader: EventParserStrategy[ProgramCancelled],
  private val programLikedReader: EventParserStrategy[ProgramLiked]
) {

  def parseMessage(msg: String): Try[RawEvent] = Try(rawEventReader.read(msg))

  def readToModel(event: RawEvent): Try[DomainEvent] =
    event.eventType match {
      case "program_started" => createProgramStarted(event.payload)
      case "program_cancelled" => createProgramCancelled(event.payload)
      case "program_liked" => createProgramLiked(event.payload)
      case _ =>
        Failure(new Exception(s"unhandled type [${event.eventType}]"))
    }

  def toMessage(domainEvent: DomainEvent): String =
    domainEvent match {
      case e: ProgramStarted =>
        s"""
       |{
       |  "eventType": "program_started,
       |  "payload": {
       |    "raw": "$e"
       |  }
       |}
       |""".stripMargin
      case _ => s"""{"error": [{"type": "not implemented"}],"object":"$domainEvent"}"""
    }

  private def createProgramStarted(programStartedPayload: String): Try[ProgramStarted] =
    Try(programStartedReader.read(programStartedPayload))

  private def createProgramCancelled(programCancelledPayload: String): Try[ProgramCancelled] =
    Try(programCancelledReader.read(programCancelledPayload))

  private def createProgramLiked(programLikedPayload: String): Try[ProgramLiked] =
    Try(programLikedReader.read(programLikedPayload))
}
