package de.htwg.se.reversi.model.stone

import de.htwg.se.reversi.model.{JSONDeserializable, JSONSerializable, XMLDeserializable, XMLSerializable}
import de.htwg.se.reversi.util.{JSONParseException, XMLParseException}
import netscape.javascript.JSObject
import play.api.libs.json.{JsObject, JsValue, Json}

import scala.util.{Failure, Success, Try}
import scala.xml.Node

case class Stone(state: StoneState) extends XMLSerializable, JSONSerializable {
  def renderText(): String = state.renderText()

  def renderColor(): scala.swing.Color = state.renderColor()

  def square: Boolean = state.square

  def name: String = state.name

  private def stoneSerialize(): String = state match {
    case BlackStone => "black"
    case WhiteStone => "white"
    case NoStone => "nothing"
    case _ => "empty"
  }

  override def toXML: Node = <stone>
    {stoneSerialize()}
  </stone>

  override def json: JsValue = Json.toJson(stoneSerialize())
}

object Stone extends XMLDeserializable[Stone], JSONDeserializable[Stone] {

  private def stoneDeserialize(name: String): Try[Stone] = name match {
    case "white" => Success(Stone(WhiteStone))
    case "black" => Success(Stone(BlackStone))
    case "nothing" | "empty" => Success(Stone(NoStone))
    case v => Failure(new XMLParseException(s"Could not parse stone with value: $v"))
  }

  override def fromXML(node: Node): Try[Stone] = {
    stoneDeserialize(node.text.trim)
  }

  override def fromJSON(json: JsValue): Try[Stone] = {
    json.asOpt[String] match
      case Some(value) => stoneDeserialize(value)
      case None => Failure(new JSONParseException("Could not parse stone from json"))
  }
}