package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.{Coordinate, Field, GameState, Move}
import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.util.{AlreadyPlacedError, GameDone, InvalidPut, Observable, Placed}

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

class Controller(var gameState: GameState, var finished: Boolean = false) extends IController {
  private val history: mutable.Stack[Command] = mutable.Stack()
  private val future: mutable.Stack[Command] = mutable.Stack()

  def this(field: Field, startingPlayer: StoneState) = this(GameState(field, startingPlayer), false)

  override def put(row: Int, col: Int, possibleMoves: List[Move] = Nil): Unit = {
    if field.getStone(row, col) match
      case Some(value) => value.state != NoStone
      case None => false
    then {
      notifyObservers(AlreadyPlacedError)
      return
    }

    if (possibleMoves != Nil && !possibleMoves.exists(m => m.on == Coordinate(row, col, gameState.currentPlayer))) {
      notifyObservers(InvalidPut)
    } else {
      val command = PutCommand(this, row, col, possibleMoves)
      push(command)

      if (field.getPossibleMoves(gameState.currentPlayer, gameState.nextPlayer).isEmpty) {
        // count stones for winner
        var black: Int = 0
        var white: Int = 0
        for (i <- 0 until field.size; j <- 0 until field.size) field.getStone(i, j) match {
          case Some(value) => value.state match {
            case WhiteStone => white += 1
            case BlackStone => black += 1
            case _ =>
          }
          case None =>
        }

        val winner: StoneState = if black > white then BlackStone else if (black == white) NoStone else WhiteStone
        notifyObservers(GameDone(Stone(winner)))
      } else {
        notifyObservers(Placed)
      }
    }

  }

  private def push(command: Command): Unit = {
    command.doCommand()
    history.push(command)
    future.clear()
  }

  override def undo(): Try[GameState] = {
    if (history.isEmpty) return Failure(new NoSuchElementException())
    val oldState = gameState
    val command = history.pop()
    command.undoCommand()
    future.push(command)
    notifyObservers(Placed)
    Success(oldState)
  }

  override def redo(): Try[GameState] = {
    if (future.isEmpty) return Failure(new NoSuchElementException())
    val command = future.pop()
    val oldState = gameState
    command.doCommand()
    history.push(command)
    notifyObservers(Placed)
    Success(oldState)
  }

  override def canUndo: Boolean = history.nonEmpty
  override def canRedo: Boolean = future.nonEmpty

  override def getLastCommand: Command = history.top

  override def getPossibleMoves: List[Move] = field.getPossibleMoves(gameState.currentPlayer, gameState.nextPlayer)

  override def field: Field = gameState.field

  override def currentPlayer: StoneState = gameState.currentPlayer
}
