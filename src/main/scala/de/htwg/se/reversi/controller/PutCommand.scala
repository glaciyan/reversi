package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.{Coordinate, GameState, Move}
import de.htwg.se.reversi.model.stone.{Stone, StoneState}

import scala.util.{Failure, Success, Try}

case class PutCommand(controller: Controller, row: Int, col: Int, moves: List[Move]) extends Command:
  private var replaced: Option[Stone] = None
  private var whoPlaced: Option[StoneState] = None
  private var enemies: List[Coordinate] = List[Coordinate]()

  override def doCommand(): Unit = {
    replaced = controller.gameState.field.getStone(row, col)
    whoPlaced = Some(controller.gameState.currentPlayer)
    var state = controller.gameState

    for (move <- moves.filter(_.on == Coordinate(row, col, controller.gameState.currentPlayer))) do enemies = enemies ::: move.enemies
    for (enemy <- enemies) state = state.put(enemy.row, enemy.col, controller.gameState.currentPlayer, controller.gameState.currentPlayer)

    controller.gameState = state.put(row, col)
  }

  override def undoCommand(): Try[Unit] = {
    (replaced, whoPlaced) match {
      case (Some(replaced), Some(who)) => {
        var state = controller.gameState
        for (enemy <- enemies) state = state.put(enemy.row, enemy.col, enemy.stoneState, replaced.state)
        Success(controller.gameState = state.put(row, col, replaced.state, who))
      }
      case _ => Failure(new IllegalStateException("doCommand has to be called first"))
    }
  }
