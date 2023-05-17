// $COVERAGE-OFF$Disabling highlighting by default until a workaround for https://issues.scala-lang.org/browse/SI-8596 is found
package de.htwg.se.reversi

import de.htwg.se.reversi.controller.Controller
import de.htwg.se.reversi.model.Field
import de.htwg.se.reversi.model.stone.{BlackStone, WhiteStone, Stone, StoneState}
import de.htwg.se.reversi.views.{GameUI, TUIView}

import scala.io.StdIn.{readLine, readf, readf3}

@main def main(): Unit =
  val field = Field().put(3, 3, Stone(BlackStone)).put(3, 4, Stone(WhiteStone)).put(4, 3, Stone(WhiteStone)).put(4, 4, Stone(BlackStone))
  val state = Controller(field, WhiteStone, false)
  val game: GameUI = TUIView(state) // Template Pattern
  game.run()

// $COVERAGE-ON$
