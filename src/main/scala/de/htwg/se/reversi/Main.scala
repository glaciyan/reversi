// $COVERAGE-OFF$Disabling highlighting by default until a workaround for https://issues.scala-lang.org/browse/SI-8596 is found
package de.htwg.se.reversi

import de.htwg.se.reversi.controller.modules.Default.given
import de.htwg.se.reversi.views.{GUIView, GameUI}

@main def main(): Unit =
  val game: GameUI = GUIView()
  game.run()

// $COVERAGE-ON$
