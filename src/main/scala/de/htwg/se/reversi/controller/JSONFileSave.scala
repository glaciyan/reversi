package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.{IGameState, JSONDeserializable}
import de.htwg.se.reversi.util.XMLParseException
import play.api.libs.json.{JsValue, Json}

import java.io.{File, PrintWriter}
import scala.io.Source
import scala.util.{Failure, Success, Try}

class JSONFileSave(using deserializer: JSONDeserializable[IGameState]) extends IFileSave {
  override def write(file: File, gameState: IGameState): Try[Unit] = {
    val writer = new PrintWriter(file)
    writer.write(Json.stringify(gameState.json))
    writer.close()
    Success({})
  }

  override def read(file: File): Try[IGameState] = {
    val source = Source.fromFile(file)
    val content: String = source.getLines.mkString
    source.close()
    val json: JsValue = Json.parse(content)
    deserializer.fromJSON(json)
  }
}
