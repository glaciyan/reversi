package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.{Field, GameState}
import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.util.PutEvent.{AlreadyPlacedError, GameDone, Placed}
import de.htwg.se.reversi.util.Observable

import scala.collection.mutable

class GameController(private var gameState: GameState, var finished: Boolean = false) extends Controller {
  private val history: mutable.Stack[GameState] = mutable.Stack()

  def this(field: Field, startingPlayer: StoneState) = this(GameState(field, startingPlayer), false)

  def put(row: Int, col: Int): Unit = {
    history.push(gameState)
    gameState = gameState.put(row, col)
    notifyObservers(Placed)
  }

  def undo(): GameState = {
    val oldState = gameState
    gameState = history.pop()
    oldState
  }

  def field: Field = gameState.field

  def currentPlayer: StoneState = gameState.currentPlayer
}
