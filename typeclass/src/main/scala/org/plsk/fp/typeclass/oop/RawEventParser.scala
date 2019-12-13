package org.plsk.fp.typeclass.oop

import io.circe.parser.decode
import org.plsk.fp.typeclass.json.event.rawEventDecoder
import org.plsk.fp.typeclass.model.RawEvent

class RawEventParser extends EventParserStrategy[RawEvent] {
  override def read(str: String): RawEvent =
    decode(str)
      .fold(
        error =>
          throw new Exception(s"could not read raw event from payload [$str]: $error"),
        identity)
}
