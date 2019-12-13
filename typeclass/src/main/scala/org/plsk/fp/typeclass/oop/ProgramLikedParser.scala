package org.plsk.fp.typeclass.oop

import io.circe.parser.decode
import org.plsk.fp.typeclass.domain.event.ProgramLiked
import org.plsk.fp.typeclass.domain.event.parser.EventParserStrategy
import org.plsk.fp.typeclass.json.event.programLikedDecoder

class ProgramLikedParser extends EventParserStrategy[ProgramLiked]{
  override def read(str: String): ProgramLiked =
    decode(str).getOrElse(throw new Exception(s"could not read ProgramLiked from payload [$str]"))
}
