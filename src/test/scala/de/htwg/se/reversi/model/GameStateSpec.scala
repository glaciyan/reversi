package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, WhiteStone}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.Json

import scala.util.{Failure, Success}
import scala.xml.Utility

class GameStateSpec extends AnyWordSpec {
  "Serialization/Deserialization" should {
    val sField = Field(new Matrix(2, Stone(NoStone))).put(0, 0, Stone(WhiteStone)).put(1, 1, Stone(BlackStone))
    val state = GameState(WhiteStone, sField)

    val xml = Utility.trim(<gamestate>
      <currentPlayer>
        <stone>white</stone>
      </currentPlayer>
      <field>
        <row>
          <stone>white</stone> <stone>nothing</stone>
        </row> <row>
        <stone>nothing</stone> <stone>black</stone>
      </row>
      </field>
    </gamestate>)

    val json = "{\"player\":\"white\",\"field\":[[\"white\",\"nothing\"],[\"nothing\",\"black\"]]}"

    "work" when {
      "serialize xml" in {
        Utility.trim(state.toXML) should be(xml)
      }
      "serialize json" in {
        state.json.toString should be(json)
      }
      "deserialize xml" in {
        GameState.fromXML(xml).get should be(state)
      }
      "deserialize json" in {
        GameState.fromJSON(Json.parse(json)).get should be(state)
      }
    }
    "not work" when {
      val brokenPlayer = Utility.trim(<gamestate>
        <currentPlayer>
          <stone>whit</stone>
        </currentPlayer>
        <field>
          <row>
            <stone>white</stone> <stone>nothing</stone>
          </row> <row>
          <stone>nothing</stone> <stone>black</stone>
        </row>
        </field>
      </gamestate>)

      val json = "{\"player\":\"whit\",\"field\":[[\"white\",\"nothing\"],[\"nothing\",\"black\"]]}"

      "can't read player xml" in {
        GameState.fromXML(brokenPlayer) shouldBe a[Failure[_]]
      }

      "can't read player json" in {
        GameState.fromJSON(Json.parse(json)) shouldBe a[Failure[_]]
      }

      val brokenField = Utility.trim(<gamestate>
        <currentPlayer>
          <stone>white</stone>
        </currentPlayer>
        <field>
          <row>
            <stone>white</stone> <stone>nothing</stone>
          </row> <row>
          <stone>nothin</stone> <stone>black</stone>
        </row>
        </field>
      </gamestate>)

      val jsonField = "{\"player\":\"white\",\"field\":[[\"white\",\"nothin\"],[\"nothing\",\"black\"]]}"

      "can't read field xml" in {
        GameState.fromXML(brokenField) shouldBe a[Failure[_]]
      }

      "can't read field json" in {
        GameState.fromJSON(Json.parse(jsonField)) shouldBe a[Failure[_]]
      }

      val brokenStructure = Utility.trim(<gamestate>
      </gamestate>)

      val jsonStructure = "{}"

      "broken structure xml" in {
        GameState.fromXML(brokenStructure) shouldBe a[Failure[_]]
      }

      "broken structure json" in {
        GameState.fromJSON(Json.parse(jsonStructure)) shouldBe a[Failure[_]]
      }
    }
  }
}
