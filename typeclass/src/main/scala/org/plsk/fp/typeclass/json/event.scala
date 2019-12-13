package org.plsk.fp.typeclass.json

import org.plsk.fp.typeclass.domain.event.{ProgramCancelled, ProgramLiked, ProgramStarted, RawEvent}

object event {

  import io.circe._, io.circe.generic.semiauto._

  implicit val programStartedDecoder: Decoder[ProgramStarted] = deriveDecoder
  implicit val programCancelledDecoder: Decoder[ProgramCancelled] = deriveDecoder
  implicit val programLikedDecoder: Decoder[ProgramLiked] = deriveDecoder

  implicit val rawEventDecoder: Decoder[RawEvent] = new Decoder[RawEvent] {
    final def apply(c: HCursor): Decoder.Result[RawEvent] =
      for {
        eventType <- c.downField("eventType").as[String]
        payload = c.downField("payload").focus.map(_.toString).getOrElse(throw new Exception(s"failed to read payload from [${c.downField("payload")}]"))
      } yield  RawEvent(eventType, payload)
  }
}
