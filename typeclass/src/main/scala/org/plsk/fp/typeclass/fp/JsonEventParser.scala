package org.plsk.fp.typeclass.fp

import org.plsk.fp.typeclass.fp.JsonParser.BaseJsonParser

import scala.util.{Failure, Success, Try}

trait JsonEventParser {

  def parseEvent[EventType: BaseJsonParser](payload: String): Try[EventType] =
    implicitly[BaseJsonParser[EventType]].parse(payload) match {
      case Right(event) => Success(event)
      case Left(error) => Failure(error)
    }
}
