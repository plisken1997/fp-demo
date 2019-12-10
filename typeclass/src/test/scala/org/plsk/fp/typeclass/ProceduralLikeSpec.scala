package org.plsk.fp.typeclass

import org.scalatest.{Matchers, WordSpec}

class ProceduralLikeSpec extends WordSpec with Matchers {

  "procedural like services" should {

    "print the expected format" when {

      "parquet format is expected" in {
        val expectedParquet = s"parquet[$baseJson]"
        ProceduralLike.writeParquet(baseJson) shouldBe Parquet(expectedParquet)
      }

      "json format is expected" in {
        val expectedJson = s"""{"data": $baseJson}"""
        ProceduralLike.writeJSON(baseJson) shouldBe Json(expectedJson)
      }

      "html format is expected" in {
        val expectedHtml =
          s"""
             |<table>
             |  <tr>
             |    <td>$baseJson</td>
             |  </tr>
             |</table>
             |""".stripMargin
        ProceduralLike.writeHtmlTable(baseJson) shouldBe Html(expectedHtml)
      }

      "log format is expected" in {
        val expectedLog = s"""127.0.0.1 user-identifier localhost [10/Oct/2000:13:55:36 -0700] "GET /$baseJson HTTP/1.0 200 2326""""
        ProceduralLike.writeLog(baseJson) shouldBe Log(expectedLog)
      }

    }
  }

  val baseJson = """{"name": "fp-demo", "org": "org.plsk", "tags": ["fp", "scala"]}"""

}
