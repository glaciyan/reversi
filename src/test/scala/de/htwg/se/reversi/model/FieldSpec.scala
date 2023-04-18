package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.Stone.{Nothing, White, Black}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec {
  val eol: String = sys.props("line.separator")

  "A Field" when {
    val emptyField = Field(new Matrix(8, Nothing))
    "with empty fields" should {
      "have a correct bar" in {
        emptyField.bar should be("+-+-+-+-+-+-+-+-+" + eol)
      }
      "have an empty row" in {
        emptyField.row(0) should be("| | | | | | | | |" + eol)
      }
    }
    "with some stones" should {
      val playingField = emptyField
        .put(0, 0, White)
        .put(3, 4, Black)
        .put(3, 3, Black)
        .put(0, 0, Black)
        .put(0, 4, White)

      "have stones print" in {
        playingField.row(0) should be ("|X| | | |O| | | |" + eol)
        playingField.row(3) should be ("| | | |X|X| | | |" + eol)
      }
      "have correct field" in {
        playingField.display should be("""+""")
      }
    }
  }
}