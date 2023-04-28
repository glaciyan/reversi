package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.Stone.{Nothing, White, Black}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec {
  val eol: String = sys.props("line.separator")
  var W: String = Stone.White.toString
  var B: String = Stone.Black.toString
  var e: String = Stone.Nothing.toString

  "A Field" when {
    "without padding" should {
      val emptyField = Field(new Matrix(8, Nothing))
      "have an empty row" in {
        emptyField.row(0) should be(s"$e$e$e$e$e$e$e$e")
      }
    }
    "without padding and stones" should {
      "with some stones" should {
        val playingField = Field(new Matrix(8, Nothing))
          .put(0, 0, White)
          .put(3, 4, Black)
          .put(3, 3, Black)
          .put(0, 0, Black)
          .put(0, 4, White)

        "have stones print" in {
          playingField.row(0) should be(s"$B$e$e$e$W$e$e$e")
          playingField.row(3) should be(s"$e$e$e$B$B$e$e$e")
        }

        "be able to ge ta single stone" in {
          playingField.getStone(0, 4) should be(White)
        }
      }
    }
  }
}