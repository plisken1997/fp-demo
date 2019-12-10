package org.plsk.fp

package object typeclass {

  type JSONLike = String

  case class Parquet(str: JSONLike)
  case class Json(str: JSONLike)
  case class Html(str: JSONLike)
  case class Log(str: JSONLike)

}
