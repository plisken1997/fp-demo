package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.domain.event._

import scala.util.{Failure, Try}

class EventParser(
                   private val rawEventReader: RawEventParser,
                   private val programStartedReader: ProgramStartedParser,
                   private val programCancelledReader: ProgramCancelledParser,
                   private val programLikedReader: ProgramLikedParser
) {

  def parseMesage(msg: String): Try[RawEvent] = Try(rawEventReader.read(msg))

  def readToModel(event: RawEvent): Try[DomainEvent] = event.eventType match {
    case "program_started" =>
      createProgramStarted(event.payload)
    case "program_cancelled" =>
      createProgramCancelled(event.payload)
    case "program_liked" =>
      createProgramLiked(event.payload)
    case _ =>
      Failure(new Exception(s"unhandled type [${event.eventType}]"))
  }

  def toMessage(domainEvent: DomainEvent): String = ???

  private def createProgramStarted(programStartedPayload: String): Try[ProgramStarted] =
    Try(programStartedReader.read(programStartedPayload))

  private def createProgramCancelled(programCancelledPayload: String): Try[ProgramCancelled] =
    Try(programCancelledReader.read(programCancelledPayload))

  private def createProgramLiked(programLikedPayload: String): Try[ProgramLiked] =
    Try(programLikedReader.read(programLikedPayload))
}
