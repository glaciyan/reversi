package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.util.PutEvent

case class GameState(currentPlayer: StoneState, field: IField) extends IGameState {
  override def put(row: Int, col: Int): IGameState = this.put(row, col, currentPlayer, nextPlayer)

  override def put(row: Int, col: Int, stone: StoneState, next: StoneState): IGameState =
    this.copy(next, field.put(row, col, Stone(stone)))

  override def nextPlayer: StoneState = currentPlayer match {
    case WhiteStone => BlackStone
    case BlackStone => WhiteStone
    case _ => WhiteStone
  }
}