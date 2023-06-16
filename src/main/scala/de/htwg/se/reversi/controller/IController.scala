package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.stone.StoneState
import de.htwg.se.reversi.model.{Field, GameState, IField, IGameState, Move}
import de.htwg.se.reversi.util.Observable

import scala.util.Try

trait IController extends Observable {
  def put(row: Int, col: Int): Unit

  def undo(): Try[IGameState]

  def redo(): Try[IGameState]

  def canUndo: Boolean

  def canRedo: Boolean

  def getLastCommand: Command

  def getPossibleMoves: List[Move]

  def field: IField

  def currentPlayer: StoneState

  def finished: Boolean

  def gameState: IGameState
}
