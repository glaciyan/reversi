package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.Field
import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.util.Event.{AlreadyPlacedError, GameDone, Placed}
import de.htwg.se.reversi.util.Observable

class Controller(var field: Field, var currentPlayer: StoneState, var finished: Boolean = false) extends Observable {
  def put(row: Int, col: Int): Unit = {
    field.getStone(row, col).state match {
      case NoStone =>
        field = field.put(row, col, Stone(currentPlayer))
        currentPlayer = nextPlayer
//        if gameFinished then
//          finished = true
//          notifyObservers(GameDone)
        notifyObservers(Placed)
      case _: StoneState => notifyObservers(AlreadyPlacedError)
    }

  }

  private def nextPlayer = currentPlayer match {
    case WhiteStone => BlackStone
    case BlackStone => WhiteStone
    case _ => WhiteStone
  }
}