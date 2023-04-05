package de.htwg.se.reversi.model

case class Field(matrix : Matrix[Stone]):
    def this(size: Int, filling: Stone) = this(new Matrix(size, filling))

    def size = matrix.size
    val eol = sys.props("line.separator")

    def bar(cellWidth: Int = size , cellNum: Int = size): String = (("+" + "-" * cellWidth) * cellNum) + "+" eol

    def cells(row: Int, cellWidth: Int = size): String = 
        matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + eol

    //mkString( start: String, sep: String, end: String)
    def mesh(cellWidth: Int = size): String = 
        (0 until size).map(cells(_, cellWidth)).mkString(bar(cellWidth, size), bar(cellWidth, size), bar(cellWidth, size)) 
    override def toString = mesh()

    def put(stone: Stone, x: Int, y: Int) = copy(matrix.replaceCell(x,y,stone))

    def get(x: Int, y: Int): Stone = matrix.cell(x,y)