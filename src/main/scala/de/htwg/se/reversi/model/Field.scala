package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.stone.{NoStone, Stone, StoneState}

case class Field(m: Matrix[Stone] = new Matrix(8, Stone(NoStone))) {
  def size: Int = m.theSize

  val eol: String = sys.props("line.separator")

  def row(row: Int): Vector[Stone] = m.row(row)

  def put(row: Int, col: Int, stone: Stone): Field = copy(m.putCell(row, col, stone))

  def getStone(row: Int, col: Int): Option[Stone] = m.cell(row, col)

  /**
   *
   * @param goodGuy
   * @param enemy
   * @return Might return multiple moves on the same coordinate, use all of them on a turn!!!
   */
  def getPossibleMoves(goodGuy: StoneState, enemy: StoneState): List[Move] = {
    var moves = List[Move]()

    for (row <- 0 until size; col <- 0 until size; up <- -1 to 1; right <- -1 to 1; if (up, right) != (0, 0)) {
      val stone = getStone(row, col)
      if (stone.contains(Stone(NoStone))) {
        val empty = stone.get

        var (i, j) = (row, col)

        var enemies = List[Coordinate]()

        while (i < size && j < size && getStone(i + up, j + right).contains(Stone(enemy))) {
          i += up
          j += right

          enemies = enemies :+ Coordinate(i, j, enemy)
        }

        // we moved, enemies between
        if ((i != row || j != col) && getStone(i + up, j + right).contains(Stone(goodGuy))) {
          moves = moves :+ Move(Coordinate(row, col, goodGuy), enemies, Coordinate(i + up, j + right, goodGuy))
        }
      }
    }

    moves
  }
}
