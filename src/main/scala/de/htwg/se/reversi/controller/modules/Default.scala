package de.htwg.se.reversi.controller.modules

import de.htwg.se.reversi.controller.{Controller, IController}
import de.htwg.se.reversi.model.Field
import de.htwg.se.reversi.model.stone.{BlackStone, Stone, WhiteStone}

object Default {
  private val defaultField: Field =
    Field()
      .put(4, 4, Stone(WhiteStone))
      .put(4, 3, Stone(BlackStone))
      .put(3, 4, Stone(BlackStone))
      .put(3, 3, Stone(WhiteStone))

  given IController = Controller(defaultField, BlackStone)
}
