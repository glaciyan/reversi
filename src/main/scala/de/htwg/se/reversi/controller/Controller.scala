package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.Stone.Nothing
import de.htwg.se.reversi.model.{Field, Stone}
import de.htwg.se.reversi.util.Event.{AlreadyPlacedError, GameDone, Placed}
import de.htwg.se.reversi.util.Observable

// TODO: testen
class Controller(var field: Field, var currentPlayer: Stone, var finished: Boolean) extends Observable {
  var count = 0;

  def put(row: Int, col: Int) = {
    field.getStone(row, col) match {
      case Nothing =>
        field = field.put(row, col, currentPlayer)
        currentPlayer = nextPlayer
        count += 1
        if count > 4 then
          finished = true
          notifyObservers(GameDone)
        notifyObservers(Placed)
      case _: Stone => notifyObservers(AlreadyPlacedError)
    }

  }

  private def nextPlayer = currentPlayer match {
    case Stone.White => Stone.Black
    case Stone.Black => Stone.White
    case _ => Stone.White
  }
}