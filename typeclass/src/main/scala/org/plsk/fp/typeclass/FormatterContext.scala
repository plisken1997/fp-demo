package org.plsk.fp.typeclass

class FormatterContext(
                        private val parquetWriter: Formatter[Parquet],
                        private val jsonWriter: Formatter[Json],
                        private val htmlWriter: Formatter[Html],
                        private val logWriter: Formatter[Log]
 ) {

  def formatParquet(json: JSONLike): Parquet = parquetWriter.format(json)

  def formatJson(json: JSONLike): Json = jsonWriter.format(json)

  def formatHtml(json: JSONLike): Html = htmlWriter.format(json)

  def formatLog(json: JSONLike): Log = logWriter.format(json)
}
