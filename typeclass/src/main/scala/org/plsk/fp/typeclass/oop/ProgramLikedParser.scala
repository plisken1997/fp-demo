package org.plsk.fp.typeclass.oop

import io.circe.parser.decode
import org.plsk.fp.typeclass.json.event.programLikedDecoder
import org.plsk.fp.typeclass.model.ProgramLiked

class ProgramLikedParser extends EventParserStrategy[ProgramLiked]{
  override def read(str: String): ProgramLiked =
    decode(str).getOrElse(throw new Exception(s"could not read ProgramLiked from payload [$str]"))
}
