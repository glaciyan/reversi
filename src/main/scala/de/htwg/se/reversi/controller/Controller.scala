package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.stone.StoneState
import de.htwg.se.reversi.model.{Field, GameState}
import de.htwg.se.reversi.util.Observable

trait Controller extends Observable {
  def put(row: Int, col: Int): Unit
  def undo(): GameState
  def field: Field
  def currentPlayer: StoneState
  def finished: Boolean
}
