package org.plsk.fp.typeclass

package object v0 {

  type JSONLike = String

  case class Parquet(str: JSONLike)
  case class Json(str: JSONLike)
  case class Html(str: JSONLike)
  case class Log(str: JSONLike)

}
