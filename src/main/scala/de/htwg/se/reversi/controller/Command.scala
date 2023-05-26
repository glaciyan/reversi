package de.htwg.se.reversi.controller

trait Command() {
  def doCommand(): Unit
  def undoCommand(): Unit
}
