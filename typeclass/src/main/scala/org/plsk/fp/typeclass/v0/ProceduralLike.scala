package org.plsk.fp.typeclass.v0

object ProceduralLike {

  def writeParquet(input: JSONLike): Parquet =
    Parquet(s"parquet[$input]")

  def writeJSON(input: JSONLike): Json =
    Json(s"""{"data": $input}""")

  def writeHtmlTable(input: JSONLike): Html =
    Html(
    s"""
     |<table>
     |  <tr>
     |    <td>$input</td>
     |  </tr>
     |</table>
     |""".stripMargin)

  def writeLog(input: JSONLike): Log =
    Log(s"""127.0.0.1 user-identifier localhost [10/Oct/2000:13:55:36 -0700] "GET /$input HTTP/1.0 200 2326"""")
}
