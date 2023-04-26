package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.{Field, Stone}
import de.htwg.se.reversi.util.Observable

class GameState(var field: Field, var currentPlayer: Stone, var finished: Boolean) extends Observable {
  def put(row: Int, col: Int) = {
    field = field.put(row, col, currentPlayer)
    currentPlayer = nextPlayer
  }

  private def nextPlayer = currentPlayer match {
    case Stone.White => Stone.Black
    case Stone.Black => Stone.White
    case _ => Stone.White
  }
}