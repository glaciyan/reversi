package de.htwg.se.reversi.views

import de.htwg.se.reversi.controller.GameState
import de.htwg.se.reversi.util.Observer

import scala.io.StdIn

class TUIView(state: GameState) extends GameUI, Observer {
  state.add(this)

  override def run(): Unit = inputLoop()

  private def inputLoop(): Unit = {
    while !state.finished do {
      println(state.field.display)
      waitForInput()
    }
  }

  private def waitForInput(): Unit = {
    StdIn.readLine()
  }

  override def update(): Unit = ???
}
