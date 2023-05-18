package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.stone.StoneState
import de.htwg.se.reversi.model.{Field, GameState}

trait Controller {
  def put(row: Int, col: Int): Unit
  def undo(): GameState
  def field: Field
  def currentPlayer: StoneState
}
