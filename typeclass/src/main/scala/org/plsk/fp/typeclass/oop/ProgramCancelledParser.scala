package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.domain.event.ProgramCancelled
import org.plsk.fp.typeclass.domain.event.parser.EventParserStrategy
import org.plsk.fp.typeclass.json.event.programCancelledDecoder
import io.circe.parser._

class ProgramCancelledParser extends EventParserStrategy[ProgramCancelled] {
  override def read(str: String): ProgramCancelled =
     decode(str).getOrElse(throw new Exception(s"could not read ProgramCancelled from payload [$str]"))
}
