package org.plsk.fp.typeclass.oop

object DI {

  def provideEventParser(): EventParser =  {

    val rawEventParser = new RawEventParser
    val programStartedParser = new ProgramStartedParser
    val programCancelledParser = new ProgramCancelledParser
    val programLikedParser = new ProgramLikedParser

    new EventParser(
      rawEventParser,
      programStartedParser,
      programCancelledParser,
      programLikedParser
    )
  }

}
