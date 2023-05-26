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
        matrix.cell(3, 4) should be(Some("ABC"))
      }
    }
    "created" should {
      val vecVec = Vector.tabulate(6, 6) { (_, _) => "Q" }
      val matrix = Matrix(vecVec)
      "be constructed properly" in {
        matrix.row(0) should be(vecVec(0))
      }
      "have the current size" in {
        matrix.size should be(6)
        matrix.row(0).size should be(6)
      }
      "have the correct element" in {
        matrix.cell(5, 5) should be(Some("Q"))
      }
      "put cells and be immutable" in {
        val returned = matrix.putCell(3, 2, "H")
        matrix.cell(3, 2) should be(Some("Q"))
        returned.cell(3, 2) should be(Some("H"))
      }
      "be have fill" in {
        val BMatrix = matrix.fill("B")
        matrix.cell(0, 0) should be(Some("Q"))
        BMatrix.cell(0, 0) should be(Some("B"))
      }
      "have putCell work" in {
        val putMatrix = matrix.putCell(0,0, "A")
        putMatrix.cell(0,0) should be (Some("A"))
      }
    }
  }
}
