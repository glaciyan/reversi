package de.htwg.se.reversi.model.stone

import java.awt.Color

object NoStone extends StoneState {
  override def renderText(): String = "🟩"
  override def renderColor(): Color = Color.GREEN

  override def square: Boolean = true

  override def name: String = "Nothing"
}
