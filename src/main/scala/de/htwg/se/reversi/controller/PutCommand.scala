package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.GameState
import de.htwg.se.reversi.model.stone.{Stone, StoneState}

import scala.util.{Failure, Success, Try}

case class PutCommand(controller: Controller, row: Int, col: Int) extends Command:
  var replaced: Option[Stone] = None
  var whoPlaced: Option[StoneState] = None

  override def doCommand(): Unit = {
    replaced = controller.gameState.field.getStone(row, col)
    whoPlaced = Some(controller.gameState.currentPlayer)
    controller.gameState = controller.gameState.put(row, col)
  }

  override def undoCommand(): Try[Unit] = {
    (replaced, whoPlaced) match {
      case (Some(replaced), Some(who)) => Success(controller.gameState = controller.gameState.put(row, col, replaced.state, who))
      case _ => Failure(new IllegalStateException("doCommand has to be called first"))
    }
  }
