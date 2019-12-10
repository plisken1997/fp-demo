package org.plsk.fp.typeclass

class FormatterTC {

  def format[Format: Formatter](json: JSONLike): Format =
    implicitly[Formatter[Format]].format(json)

}
