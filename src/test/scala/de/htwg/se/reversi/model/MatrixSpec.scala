package de.htwg.se.reversi.model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec {
  "A Matrix" when {
    "created with size and fill" should {
      "have the correct size" in {
        val matrix = new Matrix[String](10, "H")
        matrix.size should be(10)
        matrix.row(0).size should be(10)
      }
      "have the correct element" in {
        val matrix = new Matrix[String](10, "ABC")
        matrix.cell(3, 4) should be("ABC")
      }
    }
    "created" should {
      val vecVec = Vector.tabulate(6, 6) { (row, col) => "Q" }
      val matrix = Matrix(vecVec)
      "be constructed properly" in {
        matrix.row(0) should be(vecVec(0))
      }
      "have the current size" in {
        matrix.size should be(6)
        matrix.row(0).size should be(6)
      }
      "have the correct element" in {
        matrix.cell(5, 5) should be("Q")
      }
      "put cells and be immutable" in {
        val returned = matrix.putCell(3, 2, "H")
        matrix.cell(3, 2) should be("Q")
        returned.cell(3, 2) should be("H")
      }
      "be fillable" in {
        val BMatrix = matrix.fill("B")
        matrix.cell(0, 0) should be("Q")
        BMatrix.cell(0, 0) should be("B")
      }
    }
  }
}
