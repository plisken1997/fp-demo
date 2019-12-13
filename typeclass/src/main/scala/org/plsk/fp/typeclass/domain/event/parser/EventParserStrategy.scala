package org.plsk.fp.typeclass.domain.event.parser

trait EventParserStrategy[Model] {
  def read(str: String): Model
}
