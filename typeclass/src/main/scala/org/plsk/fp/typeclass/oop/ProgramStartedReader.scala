package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.domain.event.ProgramStarted

class ProgramStartedReader extends ProgramReaderStrategy[ProgramStarted] {
  override def read(str: String): ProgramStarted = ???
}
