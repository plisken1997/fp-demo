package org.plsk.fp.typeclass

trait Formatter[Format] {
  def format(input: JSONLike): Format
}

final class ParquetFormatter extends Formatter[Parquet] {
  override def format(input: JSONLike): Parquet = Parquet(s"parquet[$input]")
}

final class JsonFormatter extends Formatter[Json] {
  override def format(input: JSONLike): Json = Json(s"""{"data": $input}""")
}

final class HtmlFormatter extends Formatter[Html] {
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

final class LogFormatter extends Formatter[Log] {
  override def format(input: JSONLike): Log =
    Log(s"""127.0.0.1 user-identifier localhost [10/Oct/2000:13:55:36 -0700] "GET /$input HTTP/1.0 200 2326"""")
}
