package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.json.event.programCancelledDecoder
import io.circe.parser._
import org.plsk.fp.typeclass.model.ProgramCancelled

class ProgramCancelledParser extends EventParserStrategy[ProgramCancelled] {
  override def read(str: String): ProgramCancelled =
     decode(str).getOrElse(throw new Exception(s"could not read ProgramCancelled from payload [$str]"))
}
