package de.htwg.se.reversi.controller.modules

import de.htwg.se.reversi.controller.{Controller, IController, IFileSave, JSONFileSave, XMLFileSave}
import de.htwg.se.reversi.model.{Field, GameState, IField, IGameState, JSONDeserializable, XMLDeserializable}
import de.htwg.se.reversi.model.stone.{BlackStone, Stone, WhiteStone}

object Default {
  given IGameState = GameState(BlackStone, Field()
    .put(4, 4, Stone(WhiteStone))
    .put(4, 3, Stone(BlackStone))
    .put(3, 4, Stone(BlackStone))
    .put(3, 3, Stone(WhiteStone)))

  given XMLDeserializable[IGameState] = GameState
  given JSONDeserializable[IGameState] = GameState

  given IFileSave = JSONFileSave()

  given IController = Controller()
}
