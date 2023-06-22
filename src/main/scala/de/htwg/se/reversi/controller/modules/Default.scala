package de.htwg.se.reversi.controller.modules

import de.htwg.se.reversi.controller.{Controller, IController, IFileSave, XMLFileSave}
import de.htwg.se.reversi.model.{Field, GameState, IField, IGameState, XMLDeserializable}
import de.htwg.se.reversi.model.stone.{BlackStone, Stone, WhiteStone}

object Default {
  given IGameState = GameState(BlackStone, Field()
    .put(4, 4, Stone(WhiteStone))
    .put(4, 3, Stone(BlackStone))
    .put(3, 4, Stone(BlackStone))
    .put(3, 3, Stone(WhiteStone)))

  given XMLDeserializable[IGameState] = GameState
  given IFileSave = XMLFileSave()

  given IController = Controller()
}
