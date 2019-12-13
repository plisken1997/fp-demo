package org.plsk.fp.typeclass.oop

trait EventParserStrategy[Model] {
  def read(str: String): Model
}
