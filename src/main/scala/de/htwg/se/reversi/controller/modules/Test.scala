package de.htwg.se.reversi.controller.modules

import de.htwg.se.reversi.controller.{Controller, IController}
import de.htwg.se.reversi.model.stone.{BlackStone, Stone, WhiteStone}
import de.htwg.se.reversi.model.{Field, GameState, IGameState}

object Test {
  val sampleField: Field = Field().put(3, 3, Stone(BlackStone)).put(3, 4, Stone(WhiteStone)).put(4, 3, Stone(WhiteStone)).put(4, 4, Stone(BlackStone))
  given IGameState = GameState(WhiteStone, sampleField)

  given IController = Controller()
}
