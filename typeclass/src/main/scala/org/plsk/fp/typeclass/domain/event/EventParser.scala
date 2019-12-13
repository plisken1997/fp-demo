package org.plsk.fp.typeclass.domain.event

import org.plsk.fp.typeclass.oop.{ProgramCancelledReader, ProgramLikedReader, ProgramStartedReader, RawEventReader}

import scala.util.{Failure, Try}

class EventParser(
  private val rawEventReader: RawEventReader,
  private val programStartedReader: ProgramStartedReader,
  private val programCancelledReader: ProgramCancelledReader,
  private val programLikedReader: ProgramLikedReader
) {

  def parseMesage(msg: String): Try[RawEvent] = Try(rawEventReader.read(msg))

  def readToModel(event: RawEvent): Try[DomainEvent] = event.`type` match {
    case "program_started" =>
      createProgramStarted(event.payload)
    case "program_cancelled" =>
      createProgramCancelled(event.payload)
    case "program_liked" =>
      createProgramLiked(event.payload)
    case _ =>
      Failure(new Exception(s"unhandled type [${event.`type`}]"))
  }

  def toMessage(domainEvent: DomainEvent): String = ???

  private def createProgramStarted(programStartedPayload: String): Try[ProgramStarted] =
    Try(programStartedReader.read(programStartedPayload))

  private def createProgramCancelled(programCancelledPayload: String): Try[ProgramCancelled] =
    Try(programCancelledReader.read(programCancelledPayload))

  private def createProgramLiked(programLikedPayload: String): Try[ProgramLiked] =
    Try(programLikedReader.read(programLikedPayload))
}
