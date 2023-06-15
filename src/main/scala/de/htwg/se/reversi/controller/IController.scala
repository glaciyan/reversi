package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.stone.StoneState
import de.htwg.se.reversi.model.{Field, GameState, Move}
import de.htwg.se.reversi.util.Observable

import scala.util.Try

trait IController extends Observable {
  def put(row: Int, col: Int, possibleMoves: List[Move] = Nil): Unit

  def undo(): Try[GameState]

  def redo(): Try[GameState]

  def canUndo: Boolean

  def canRedo: Boolean

  def getLastCommand: Command

  def getPossibleMoves: List[Move]

  def field: Field

  def currentPlayer: StoneState

  def finished: Boolean

  def gameState: GameState
}
