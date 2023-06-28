package de.htwg.se.reversi.model

import play.api.libs.json.JsValue

import scala.util.Try

trait JSONDeserializable[T] {
  def fromJSON(json: JsValue): Try[T]
}
