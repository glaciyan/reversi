package de.htwg.se.reversi.model.stone

case class Stone(state: StoneState) {
  def renderText(): String = state.renderText()
  def renderColor(): scala.swing.Color = state.renderColor()
  def square: Boolean = state.square
  def name: String = state.name
}
