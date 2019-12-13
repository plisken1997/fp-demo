package org.plsk.fp.typeclass.v0

object RunnerOOPApp extends App with RunnerHelper {

  val parquetWriter = new ParquetFormatter
  val jsonWriter = new JsonFormatter
  val htmlWriter = new HtmlFormatter
  val logWriter = new LogFormatter

  val formatter = new FormatterContext(
    parquetWriter,
    jsonWriter,
    htmlWriter,
    logWriter
  )

  val baseJson = """{"name": "fp-demo", "org": "org.plsk", "tags": ["fp", "scala"]}"""

  printSection("Print parquet")(println(formatter.formatParquet(baseJson)))

  printSection("Print json")(println(formatter.formatJson(baseJson)))

  printSection("Print html")(println(formatter.formatHtml(baseJson)))

  printSection("Print log")(println(formatter.formatLog(baseJson)))

}
