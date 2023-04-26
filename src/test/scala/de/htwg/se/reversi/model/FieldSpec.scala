package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.Stone.{Nothing, White, Black}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec {
  val eol: String = sys.props("line.separator")
  var w: String = Stone.White.toString
  var b: String = Stone.Black.toString

  "A Field" when {
    "without padding" should {
      val emptyField = Field(new Matrix(8, Nothing), 0)
      "have a correct bar" in {
        emptyField.bar should be("+-+-+-+-+-+-+-+-+" + eol)
      }
      "have an empty row" in {
        emptyField.row(0) should be("| | | | | | | | |" + eol)
      }
    }
    "without padding and stones" should {
      "with some stones" should {
        val playingField = Field(new Matrix(8, Nothing), 0)
          .put(0, 0, White)
          .put(3, 4, Black)
          .put(3, 3, Black)
          .put(0, 0, Black)
          .put(0, 4, White)

        "have stones print" in {
          playingField.row(0) should be(s"|${b}| | | |${w}| | | |" + eol)
          playingField.row(3) should be(s"| | | |${b}|${b}| | | |" + eol)
        }
        "have a correct playing field" in {
          playingField.display should be(
            s"""+-+-+-+-+-+-+-+-+
              #|${b}| | | |${w}| | | |
              #+-+-+-+-+-+-+-+-+
              #| | | | | | | | |
              #+-+-+-+-+-+-+-+-+
              #| | | | | | | | |
              #+-+-+-+-+-+-+-+-+
              #| | | |${b}|${b}| | | |
              #+-+-+-+-+-+-+-+-+
              #| | | | | | | | |
              #+-+-+-+-+-+-+-+-+
              #| | | | | | | | |
              #+-+-+-+-+-+-+-+-+
              #| | | | | | | | |
              #+-+-+-+-+-+-+-+-+
              #| | | | | | | | |
              #+-+-+-+-+-+-+-+-+
              #""".stripMargin('#'))
        }
      }
    }
    "with padding" should {
      val field = Field(new Matrix(4, Nothing))
      "have a correct bar" in {
        field.bar should be("+---+---+---+---+" + eol)
      }
      "have an empty row" in {
        field.row(0) should be("|   |   |   |   |" + eol)
      }
    }
  }
}