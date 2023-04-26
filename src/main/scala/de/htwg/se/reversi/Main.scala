package de.htwg.se.reversi

import de.htwg.se.reversi.controller.GameState
import de.htwg.se.reversi.model.{Field, Stone}
import de.htwg.se.reversi.views.{GameUI, TUIView}

import scala.io.StdIn.{readLine, readf, readf3}

@main def main(): Unit =
  val field = Field().put(3, 3, Stone.Black).put(3, 4, Stone.White).put(4, 3, Stone.White).put(4, 4, Stone.Black)
  val state = GameState(field, Stone.White, false)
  val game: GameUI = TUIView(state)
  game.run()
