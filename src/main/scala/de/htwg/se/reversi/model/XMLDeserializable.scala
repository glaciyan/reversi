package de.htwg.se.reversi.model

import scala.util.Try
import scala.xml.Node

trait XMLDeserializable[T] {
  def fromXML(node: Node): Try[T]
}
