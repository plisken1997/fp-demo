package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.domain.event.RawEvent

class RawEventReader extends ProgramReaderStrategy[RawEvent] {
  override def read(str: String): RawEvent = ???
}
