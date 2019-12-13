package org.plsk.fp.typeclass.fp

import io.circe.parser.decode
import org.plsk.fp.typeclass.model.{ProgramCancelled, ProgramLiked, ProgramStarted, RawEvent}

trait JsonParser[Model, Error] {
  def parse(str: String): Either[Error, Model]
}

object JsonParser {

  case class ParseError(payload: String, error: String) extends Exception(error)

  type BaseJsonParser[Model] = JsonParser[Model, ParseError]

  implicit lazy val RawEventJsonParser = new BaseJsonParser[RawEvent] {
    import org.plsk.fp.typeclass.json.event.rawEventDecoder

    override def parse(str: String): Either[ParseError, RawEvent] =
      decode(str) match {
        case Right(event) => Right(event)
        case Left(error: Throwable) => Left(ParseError(str, error.getMessage))
      }
  }

  implicit lazy val ProgramStartedJsonParser = new BaseJsonParser[ProgramStarted] {
    import org.plsk.fp.typeclass.json.event.programStartedDecoder

    override def parse(str: String): Either[ParseError, ProgramStarted] =
      decode(str) match {
        case Right(event) => Right(event)
        case Left(error: Throwable) => Left(ParseError(str, error.getMessage))
      }
  }

  implicit lazy val ProgramCancelledJsonParser = new BaseJsonParser[ProgramCancelled] {
    import org.plsk.fp.typeclass.json.event.programCancelledDecoder

    override def parse(str: String): Either[ParseError, ProgramCancelled] =
      decode(str) match {
        case Right(event) => Right(event)
        case Left(error: Throwable) => Left(ParseError(str, error.getMessage))
      }
  }

  implicit lazy val ProgramLikedJsonParser = new BaseJsonParser[ProgramLiked] {
    import org.plsk.fp.typeclass.json.event.programLikedDecoder

    override def parse(str: String): Either[ParseError, ProgramLiked] =
      decode(str) match {
        case Right(event) => Right(event)
        case Left(error: Throwable) => Left(ParseError(str, error.getMessage))
      }
  }

}
