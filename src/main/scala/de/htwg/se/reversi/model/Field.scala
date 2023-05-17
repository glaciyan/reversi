package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.stone.{NoStone, Stone}

case class Field(m: Matrix[Stone] = new Matrix(8, Stone(NoStone))) {
  def size: Int = m.size

  val eol: String = sys.props("line.separator")

  def row(row: Int): Vector[Stone] = m.row(row)

  def put(row: Int, col: Int, stone: Stone): Field = copy(m.putCell(row, col, stone))

  def getStone(row: Int, col: Int): Stone = m.cell(row, col)
}
