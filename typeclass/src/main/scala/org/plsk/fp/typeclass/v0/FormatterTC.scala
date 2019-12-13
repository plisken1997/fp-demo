package org.plsk.fp.typeclass.v0

class FormatterTC {

  def format[Format: Formatter](json: JSONLike): Format =
    implicitly[Formatter[Format]].format(json)
}

object FormatterTC {

  implicit object ParquetFormatter extends Formatter[Parquet] {
    override def format(input: JSONLike): Parquet = Parquet(s"parquet[$input]")
  }

  implicit object JsonFormatter extends Formatter[Json] {
    override def format(input: JSONLike): Json = Json(s"""{"data": $input}""")
  }

  implicit object HtmlFormatter extends Formatter[Html] {
    override def format(input: JSONLike): Html =
      Html(
        s"""
           |<table>
           |  <tr>
           |    <td>$input</td>
           |  </tr>
           |</table>
           |""".stripMargin)
  }

  implicit object LogFormatter extends Formatter[Log] {
    override def format(input: JSONLike): Log =
      Log(s"""127.0.0.1 user-identifier localhost [10/Oct/2000:13:55:36 -0700] "GET /$input HTTP/1.0 200 2326"""")
  }

}
