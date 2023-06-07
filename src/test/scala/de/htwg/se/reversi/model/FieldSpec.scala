package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, WhiteStone}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec {
  val eol: String = sys.props("line.separator")

  "A Field" when {
    "without padding" should {
      val emptyField = Field(new Matrix(8, Stone(NoStone)))
      "have an empty row" in {
        emptyField.row(0).foreach(_.state should be(NoStone))
      }
      "correct size" in {
        emptyField.size should be(8)
      }
    }
    "without padding and stones" should {
      "with some stones" should {
        val playingField = Field(new Matrix(8, Stone(NoStone)))
          .put(0, 0, Stone(WhiteStone))
          .put(3, 4, Stone(BlackStone))
          .put(3, 3, Stone(BlackStone))
          .put(0, 0, Stone(BlackStone))
          .put(0, 4, Stone(WhiteStone))

        "have an option" in {
          playingField.getStone(0, 4) should be(Some(Stone(WhiteStone)))
        }
        "be able to get a single stone" in {
          playingField.getStone(0, 4).get.state should be(WhiteStone)
          playingField.getStone(3, 4).get.state should be(BlackStone)
          playingField.getStone(0, 0).get.state should be(BlackStone)
          playingField.getStone(6, 6).get.state should be(NoStone)
        }
      }
    }
  }
}