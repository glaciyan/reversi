package de.htwg.se.reversi.model

case class Field(m: Matrix[Stone] = new Matrix(8, Stone.Nothing)) {
  def size: Int = m.size

  val eol: String = sys.props("line.separator")

  def row(row: Int): String = m.row(row).mkString("")

  def put(row: Int, col: Int, stone: Stone): Field = copy(m.putCell(row, col, stone))

  def getStone(row: Int, col: Int): Stone = m.cell(row, col)
}
