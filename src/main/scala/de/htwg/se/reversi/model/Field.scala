package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.util.XMLParseException

import scala.util.{Failure, Success, Try}
import scala.xml.Node

case class Field(m: Matrix[Stone] = new Matrix(8, Stone(NoStone))) extends IField {
  override def size: Int = m.theSize

  override val eol: String = sys.props("line.separator")

  override def row(row: Int): Vector[Stone] = m.row(row)

  override def put(row: Int, col: Int, stone: Stone): Field = copy(m.putCell(row, col, stone))

  override def getStone(row: Int, col: Int): Option[Stone] = m.cell(row, col)

  override def getPossibleMoves(goodGuy: StoneState, enemy: StoneState): List[Move] = {
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

  override def hasStonePlaced(row: Int, col: Int): Boolean = getStone(row, col) match {
    case Some(value) => value.state != NoStone
    case None => false
  }

  override def getWinner(goodGuy: StoneState, enemy: StoneState): Option[StoneState] = {
    if getPossibleMoves(goodGuy, enemy).nonEmpty then return None

    // count stones for winner
    var black: Int = 0
    var white: Int = 0
    for (i <- 0 until size; j <- 0 until size) getStone(i, j) match {
      case Some(value) => value.state match {
        case WhiteStone => white += 1
        case BlackStone => black += 1
        case _ =>
      }
      case None =>
    }

    if black > white then Some(BlackStone) else if black == white then None else Some(WhiteStone)
  }

  override def toXML: Node = <field>
    {m.rows.map(v => <row>
      {v.map(s => s.toXML)}
    </row>)}
  </field>
}

import scala.util.control.Breaks.{break, tryBreakable}

object Field extends XMLDeserializable[IField] {
  def fromXML(node: Node): Try[IField] = {
    tryBreakable {
      val matrix =
        (node \ "row").map(
          row => (row \ "stone").map(
            s => Stone.fromXML(s) match
              case Failure(exception) => break()
              case Success(value) => value
          ).toVector
        ).toVector

      Success(Field(Matrix(matrix)))
    } catchBreak {
      Failure(new XMLParseException("can't read field"))
    }
  }
}