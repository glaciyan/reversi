package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.{Coordinate, GameState, IField, IGameState, Move}
import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.util.{AlreadyPlacedError, GameDone, InvalidPut, Observable, Placed}

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

class Controller(using var gameState: IGameState) extends IController {
  var finished: Boolean = false
  private val history: mutable.Stack[Command] = mutable.Stack()
  private val future: mutable.Stack[Command] = mutable.Stack()

  override def put(row: Int, col: Int): Unit = {
    if (field.hasStonePlaced(row, col)) {
      notifyObservers(AlreadyPlacedError)
      return
    }

    val possibleMoves = field.getPossibleMoves(gameState.currentPlayer, gameState.nextPlayer)

    if (possibleMoves != Nil && !possibleMoves.exists(m => m.on == Coordinate(row, col, gameState.currentPlayer))) {
      notifyObservers(InvalidPut)
    } else {
      val command = PutCommand(this, row, col, possibleMoves)
      push(command)

      field.getWinner(gameState.currentPlayer, gameState.nextPlayer) match
        case Some(winner) => notifyObservers(GameDone(Stone(winner)))
        case None => notifyObservers(Placed)
    }
  }


  private def push(command: Command): Unit = {
    command.doCommand()
    history.push(command)
    future.clear()
  }

  override def undo(): Try[IGameState] = {
    if (history.isEmpty) return Failure(new NoSuchElementException())
    val oldState = gameState
    val command = history.pop()
    command.undoCommand()
    future.push(command)
    notifyObservers(Placed)
    Success(oldState)
  }

  override def redo(): Try[IGameState] = {
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

  override def field: IField = gameState.field

  override def currentPlayer: StoneState = gameState.currentPlayer
}
