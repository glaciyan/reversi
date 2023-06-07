package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.Matrix.makeFill

case class Matrix[T](rows: Vector[Vector[T]]) extends Iterable[T] {

  def this(size: Int, cell: T) = this(makeFill(size, cell))

  def theSize: Int = rows.size

  def row(row: Int): Vector[T] = rows(row)

  //  def cell(row: Int, col: Int): T = rows(row)(col)
  def cell(row: Int, col: Int): Option[T] = if row < theSize && col < theSize then Some(rows(row)(col)) else None

  def putCell(row: Int, col: Int, cell: T): Matrix[T] = {
    copy(rows.updated(row, rows(row).updated(col, cell)))
  }

  def fill(cell: T): Matrix[T] = {
    copy(makeFill(theSize, cell))
  }

  override def iterator: Iterator[T] = new Iterator[T] {
    val msize = Matrix.this.theSize
    var row = 0
    var col = 0

    override def hasNext: Boolean = row < msize && col < msize

    override def next(): T = {
      if (col >= msize) {
        row += 1
        col = 0
      }

      col += 1
      Matrix.this.rows(row)(col)
    }
  }
}

object Matrix {
  private def makeFill[T](size: Int, cell: T): Vector[Vector[T]] = Vector.fill(size, size)(cell)
}
