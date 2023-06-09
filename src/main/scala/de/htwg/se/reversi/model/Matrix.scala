package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.Matrix.makeFill
import java.util.NoSuchElementException

case class Matrix[T](rows: Vector[Vector[T]]) {

  def this(size: Int, cell: T) = this(makeFill(size, cell))

  def theSize: Int = rows.size

  def row(row: Int): Vector[T] = rows(row)

  //  def cell(row: Int, col: Int): T = rows(row)(col)
  def cell(row: Int, col: Int): Option[T] = if row < theSize && col < theSize && row >= 0 && col >= 0 then Some(rows(row)(col)) else None

  def putCell(row: Int, col: Int, cell: T): Matrix[T] = {
    copy(rows.updated(row, rows(row).updated(col, cell)))
  }

  def fill(cell: T): Matrix[T] = {
    copy(makeFill(theSize, cell))
  }
}

object Matrix {
  private def makeFill[T](size: Int, cell: T): Vector[Vector[T]] = Vector.fill(size, size)(cell)
}
