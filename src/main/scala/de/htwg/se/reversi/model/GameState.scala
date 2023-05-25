package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.util.PutEvent
import de.htwg.se.reversi.util.PutEvent.{AlreadyPlacedError, Placed}

case class GameState(field: Field, currentPlayer: StoneState) {
  def put(row: Int, col: Int): GameState = this.copy(field.put(row, col, Stone(currentPlayer)), nextPlayer)

  private def nextPlayer = currentPlayer match {
    case WhiteStone => BlackStone
    case BlackStone => WhiteStone
    case _ => WhiteStone
  }
}