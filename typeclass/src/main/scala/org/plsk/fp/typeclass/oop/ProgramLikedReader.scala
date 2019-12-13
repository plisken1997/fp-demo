package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.domain.event.{ProgramCancelled, ProgramLiked}

class ProgramLikedReader extends ProgramReaderStrategy[ProgramLiked]{
  override def read(str: String): ProgramLiked = ???
}
