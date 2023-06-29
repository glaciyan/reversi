package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, WhiteStone}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.Json

import scala.util.{Failure, Success}
import scala.xml.{Utility, XML}

class FieldSpec extends AnyWordSpec {
  val eol: String = sys.props("line.separator")

  "A Field" when {
    "without padding" should {
      val emptyField = Field(new Matrix(8, Stone(NoStone)))
      "have an empty row" in {
        emptyField.row(0).foreach(_.state should be(NoStone))
      }
      "correct theSize" in {
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
  "Serialization/Deserialization" should {
    val sField = Field(new Matrix(2, Stone(NoStone))).put(0, 0, Stone(WhiteStone)).put(1, 1, Stone(BlackStone))

    val xml = Utility.trim(<field>
      <row>
        <stone>white</stone> <stone>nothing</stone>
      </row> <row>
        <stone>nothing</stone> <stone>black</stone>
      </row>
    </field>)

    val json = "[[\"white\",\"nothing\"],[\"nothing\",\"black\"]]"

    "work" when {
      "serialize xml" in {
        Utility.trim(sField.toXML) should be(xml)
      }
      "serialize json" in {
        sField.json.toString should be(json)
      }
      "deserialize xml" in {
        Field.fromXML(xml).get should be (sField)
      }
      "deserialize json" in {
        Field.fromJSON(Json.parse(json)).get should be (sField)
      }
    }
    "not work" when {
      val slightlyBroken = Utility.trim(<ield>
        <ro>
          <stone>white</stone> <stone>nothing</stone>
        </ro> <row>
          <stone>nothing</stone> <stone>black</stone>
        </row>
      </ield>)

      val fullyBroken = Utility.trim(<ield>
        <ro>
          <stone>white</stone> <stone>nothing</stone>
        </ro> <row>
          <stone>aifgholhjikafhbgojkadfhjg</stone> <stone>black</stone>
        </row>
      </ield>)

      val json = "[[\"white\",\"adgafhadfgaf\"],[\"nothing\",\"black\"]]"

      "only give a working parts" in {
        Field.fromXML(slightlyBroken) shouldBe Success(Field(Matrix(Vector(Vector(Stone(NoStone), Stone(BlackStone))))))
      }
      "can't deserialize xml" in {
        Field.fromXML(fullyBroken) shouldBe a[Failure[_]]
      }
      "deserialize json" in {
        Field.fromJSON(Json.parse(json)) shouldBe a[Failure[_]]
      }
    }
  }
  "Another Field" when {
    "a default field" should {
      val field = Field()

      "get possible moves" in {
        val moves = field.getPossibleMoves(BlackStone, WhiteStone)
        moves shouldBe List()
      }
      "get the winner" in {
        val winner = field.getWinner(BlackStone, WhiteStone)
        winner shouldBe None
      }
    }
  }
}