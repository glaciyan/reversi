// $COVERAGE-OFF$Disabling highlighting by default until a workaround for https://issues.scala-lang.org/browse/SI-8596 is found
package de.htwg.se.reversi

import de.htwg.se.reversi.controller.modules.Default.given
import de.htwg.se.reversi.controller.{Controller, IController}
import de.htwg.se.reversi.model.Field
import de.htwg.se.reversi.model.stone.{BlackStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.views.{GUIView, GameUI, TUIView}

import scala.io.StdIn.{readLine, readf, readf3}

@main def main(): Unit =
  val game: GameUI = TUIView()
  game.run()

// $COVERAGE-ON$
