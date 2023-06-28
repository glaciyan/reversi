package de.htwg.se.reversi.model

import play.api.libs.json.JsValue

trait JSONSerializable {
  def json: JsValue
}
