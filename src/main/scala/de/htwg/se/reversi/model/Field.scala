package de.htwg.se.reversi.model

case class Field(matrix : Matrix[Stone]):
    def this(size: Int, filling: Stone) = this(new Matrix(size, filling))

    val size = matrix.size
    val.eol = sys.props("line.separator")

    def bar(cellWidth: Int = 8 , cellNum: Int = 8): String = (("+" + "-" * cellWidth) * cellNum) + "+" eol

    def cells(row: Int, cellWidth: Int = 8): String = 
        matr