package org.plsk.fp.typeclass

import org.scalatest.{Matchers, WordSpec}

class FormatterSpec extends WordSpec with Matchers {

  "formatter services" should {

    "print the expected format" when {

      "parquet format is expected" in {
        val parquetWriter = new ParquetFormatter
        val expectedParquet = s"parquet[$baseJson]"
        parquetWriter.format(baseJson) shouldBe Parquet(expectedParquet)
      }

      "json format is expected" in {
        val jsonWriter = new JsonFormatter
        val expectedJson = s"""{"data": $baseJson}"""
        jsonWriter.format(baseJson) shouldBe Json(expectedJson)
      }

      "html format is expected" in {
        val htmlTable = new HtmlFormatter
        val expectedHtml =
          s"""
             |<table>
             |  <tr>
             |    <td>$baseJson</td>
             |  </tr>
             |</table>
             |""".stripMargin
        htmlTable.format(baseJson) shouldBe Html(expectedHtml)
      }

      "log format is expected" in {
        val logWriter = new LogFormatter
        val expectedLog = s"""127.0.0.1 user-identifier localhost [10/Oct/2000:13:55:36 -0700] "GET /$baseJson HTTP/1.0 200 2326""""
        logWriter.format(baseJson) shouldBe Log(expectedLog)
      }

    }
  }

  val baseJson = """{"name": "fp-demo", "org": "org.plsk", "tags": ["fp", "scala"]}"""
}
