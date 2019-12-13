package org.plsk.fp.typeclass.oop

import io.circe.parser.decode
import org.plsk.fp.typeclass.domain.event.ProgramStarted
import org.plsk.fp.typeclass.domain.event.parser.EventParserStrategy
import org.plsk.fp.typeclass.json.event.programStartedDecoder

class ProgramStartedParser extends EventParserStrategy[ProgramStarted] {
  override def read(str: String): ProgramStarted =
    decode(str).getOrElse(throw new Exception(s"could not read ProgramStarted from payload [$str]"))
}
