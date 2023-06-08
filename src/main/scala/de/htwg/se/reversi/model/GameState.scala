package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.util.PutEvent

case class GameState(field: Field, currentPlayer: StoneState) {
  def put(row: Int, col: Int): GameState = this.put(row, col, currentPlayer, nextPlayer)

  def put(row: Int, col: Int, stone: StoneState, next: StoneState): GameState =
    this.copy(field.put(row, col, Stone(stone)), next)

  def nextPlayer: StoneState = currentPlayer match {
    case WhiteStone => BlackStone
    case BlackStone => WhiteStone
    case _ => WhiteStone
  }
}