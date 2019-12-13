package org.plsk.fp.typeclass.oop

trait ProgramReaderStrategy[Model] {
  def read(str: String): Model
}
