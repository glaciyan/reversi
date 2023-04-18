package de.htwg.se.reversi.model

case class Field(m: Matrix[Stone] = new Matrix(8, Stone.Nothing), padding: Int = 1) {
  def size: Int = m.size

  val eol: String = sys.props("line.separator")

  def bar: String = (("+" + "-" * ((padding * 2) + 1)) * size) + "+" + eol

  def row(row: Int): String = m.row(row).mkString("|" + (" " * padding), (" " * padding) + "|" + (" " * padding), (" " * padding) + "|") + eol

  def put(row: Int, col: Int, stone: Stone): Field = copy(m.putCell(row, col, stone))

  def display: String = (0 until size).map(row).mkString(bar, bar, bar)
}
