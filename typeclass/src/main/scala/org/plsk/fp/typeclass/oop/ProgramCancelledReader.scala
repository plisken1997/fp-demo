package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.domain.event.ProgramCancelled

class ProgramCancelledReader extends ProgramReaderStrategy[ProgramCancelled] {
  override def read(str: String): ProgramCancelled = ???
}
