package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.stone.{BlackStone, Stone, StoneState, WhiteStone}

trait IGameState extends XMLSerializable, JSONSerializable {
  def currentPlayer: StoneState

  def field: IField

  def put(row: Int, col: Int): IGameState

  def put(row: Int, col: Int, stone: StoneState, next: StoneState): IGameState

  def nextPlayer: StoneState
}
