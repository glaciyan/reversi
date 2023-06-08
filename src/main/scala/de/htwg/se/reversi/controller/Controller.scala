package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.{Coordinate, Field, GameState, Move}
import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.util.PutEvent.{AlreadyPlacedError, GameDone, InvalidPut, Placed}
import de.htwg.se.reversi.util.Observable

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

class Controller(var gameState: GameState, var finished: Boolean = false) extends Observable {
  private val history: mutable.Stack[Command] = mutable.Stack()

  def this(field: Field, startingPlayer: StoneState) = this(GameState(field, startingPlayer), false)

  def put(row: Int, col: Int, possibleMoves: List[Move]): Unit = {
    if field.getStone(row, col) match
      case Some(value) => value.state != NoStone
      case None => false
    then {
      notifyObservers(AlreadyPlacedError)
      return
    }

    if (!possibleMoves.exists(m => m.on == Coordinate(row, col, gameState.currentPlayer))) {
      notifyObservers(InvalidPut)
    } else {
      val command = PutCommand(this, row, col, possibleMoves)
      command.doCommand()
      history.push(command)
      notifyObservers(Placed)
    }

  }

  def undo(): Try[GameState] = {
    if (history.isEmpty) return Failure(new NoSuchElementException())
    val oldState = gameState
    val command = history.pop()
    command.undoCommand()
    notifyObservers(Placed)
    Success(oldState)
  }

  def field: Field = gameState.field

  def currentPlayer: StoneState = gameState.currentPlayer
}
