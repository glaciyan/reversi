package de.htwg.se.reversi.model.stone

case class Stone(val state: StoneState) {
  def renderText(): String = state.renderText()
}
