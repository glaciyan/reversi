package de.htwg.se.reversi.model

import scala.util.Try
import scala.xml.Node

trait XMLSerializable {
  def toXML: Node
}
