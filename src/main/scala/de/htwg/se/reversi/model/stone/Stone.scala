package de.htwg.se.reversi.model.stone

import de.htwg.se.reversi.model.{XMLDeserializable, XMLSerializable}
import de.htwg.se.reversi.util.XMLParseException

import scala.util.{Failure, Success, Try}
import scala.xml.Node

case class Stone(state: StoneState) extends XMLSerializable {
  def renderText(): String = state.renderText()

  def renderColor(): scala.swing.Color = state.renderColor()

  def square: Boolean = state.square

  def name: String = state.name

  override def toXML: Node = <stone>{state match {
      case BlackStone => "black"
      case WhiteStone => "white"
      case NoStone => "nothing"
      case _ => "empty"
    }}</stone>
}

object Stone extends XMLDeserializable[Stone] {
  def fromXML(node: Node): Try[Stone] = {
    node.text.trim match {
      case "white" => Success(Stone(WhiteStone))
      case "black" => Success(Stone(BlackStone))
      case "nothing" | "empty" => Success(Stone(NoStone))
      case v => Failure(new XMLParseException(s"Could not parse stone with value: $v"))
    }
  }
}