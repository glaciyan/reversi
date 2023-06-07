package de.htwg.se.reversi.controller

import scala.util.Try

trait Command() {
  def doCommand(): Unit
  def undoCommand(): Try[Unit]
}
