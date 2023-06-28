package de.htwg.se.reversi.model.stone
import java.awt.Color
import scala.swing.Color

object BlackStone extends StoneState {
  override def renderText(): String = "⚫"

  override def renderColor(): Color = Color.BLACK

  override def square: Boolean = false

  override def name: String = "Black"
}
