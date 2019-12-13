package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.domain.event.EventParser

object DI {

  def provideEventParser(): EventParser =  {

    val rawEventReader = new RawEventReader
    val programStartedReader = new ProgramStartedReader
    val programCancelledReader = new ProgramCancelledReader
    val programLikedReader = new ProgramLikedReader

    new EventParser(
      rawEventReader,
      programStartedReader,
      programCancelledReader,
      programLikedReader
    )
  }

}
