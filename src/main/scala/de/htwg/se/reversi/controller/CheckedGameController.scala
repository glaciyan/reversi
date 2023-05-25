package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.{Field, GameState}
import de.htwg.se.reversi.model.stone.{NoStone, StoneState}
import de.htwg.se.reversi.util.PutEvent.AlreadyPlacedError

class CheckedGameController(gameController: GameController) extends Controller {
  override def put(row: Int, col: Int): Unit = {
    // logic to check
    if (field.getStone(row, col).state != NoStone) {
      notifyObservers(AlreadyPlacedError)
      return
    }

    gameController.put(row, col)
  }

  override def undo(): GameState = gameController.undo()

  override def field: Field = gameController.field

  override def currentPlayer: StoneState = gameController.currentPlayer

  override def finished: Boolean = gameController.finished
}
