package org.plsk.fp.typeclass

import FormatterTC._

object RunnerTCApp extends App with RunnerHelper {

  val formatter = new FormatterTC

  val baseJson = """{"name": "fp-demo", "org": "org.plsk", "tags": ["fp", "scala"]}"""

  printSection("Print parquet")(println(formatter.format[Parquet](baseJson)))

  printSection("Print json")(println(formatter.format[Json](baseJson)))

  printSection("Print html")(println(formatter.format[Html](baseJson)))

  printSection("Print log")(println(formatter.format[Log](baseJson)))
}
