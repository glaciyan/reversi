package de.htwg.se.reversi.model.stone

trait StoneState {
  def renderText(): String
  def renderColor(): swing.Color
  def square: Boolean
  def name: String
}
