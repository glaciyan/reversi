package de.htwg.se.reversi.model.stone

import java.awt.Color

object WhiteStone extends StoneState {
  override def renderText(): String = "⚪"

  override def renderColor(): Color = Color.WHITE

  override def square: Boolean = false

  override def name: String = "White"
}
