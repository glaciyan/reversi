package de.htwg.se.reversi.model.stone

import de.htwg.se.reversi.util.{JSONParseException, XMLParseException}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.{JsString, Json}

import scala.xml.XML

class StoneSpec extends AnyWordSpec {
  "A stone with a state" should {
    "render the correct text" in {
      Stone(WhiteStone).renderText() should be("⚪")
      Stone(BlackStone).renderText() should be("⚫")
      Stone(NoStone).renderText() should be("🟩")
    }
  }
  "Stone" should {

    "serialize to XML" in {
      val stone = Stone(WhiteStone)
      val xml = stone.toXML
      scala.xml.Utility.trim(xml).toString() shouldBe "<stone>white</stone>"
    }

    "serialize to JSON" in {
      val stone = Stone(BlackStone)
      val json = stone.json
      json shouldBe JsString("black")
    }

    "deserialize from XML" in {
      val xmlString = "<stone>nothing</stone>"
      val xml = XML.loadString(xmlString)
      val result = Stone.fromXML(xml)
      result shouldBe a[scala.util.Success[_]]
      result.get shouldBe Stone(NoStone)
    }

    "deserialize black from XML" in {
      val xmlString = "<stone>black</stone>"
      val xml = XML.loadString(xmlString)
      val result = Stone.fromXML(xml)
      result shouldBe a[scala.util.Success[_]]
      result.get shouldBe Stone(BlackStone)
    }

    "deserialize from JSON" in {
      val jsonString = "\"white\""
      val json = Json.parse(jsonString)
      val result = Stone.fromJSON(json)
      result shouldBe a[scala.util.Success[_]]
      result.get shouldBe Stone(WhiteStone)
    }

    "fail to deserialize from invalid XML" in {
      val xmlString = "<stone>invalid</stone>"
      val xml = XML.loadString(xmlString)
      val result = Stone.fromXML(xml)
      result shouldBe a[scala.util.Failure[_]]
      result.failed.get shouldBe a[XMLParseException]
    }

    "fail to deserialize from invalid JSON" in {
      val jsonString = "123"
      val json = Json.parse(jsonString)
      val result = Stone.fromJSON(json)
      result shouldBe a[scala.util.Failure[_]]
      result.failed.get shouldBe a[JSONParseException]
    }

    "render text representation" in {
      val stone = Stone(NoStone)
      stone.renderText() shouldBe "\uD83D\uDFE9"
    }

    "render color representation" in {
      val stone = Stone(WhiteStone)
      stone.renderColor() shouldBe java.awt.Color.WHITE
    }

    "return square value" in {
      val stone = Stone(BlackStone)
      stone.square shouldBe false
    }

    "return name" in {
      val stone = Stone(BlackStone)
      stone.name shouldBe "Black"
    }

    "display" in {
      for (s <- List(WhiteStone, BlackStone, NoStone)) {
        val stn = Stone(s)
        stn.renderText() shouldBe a[String]
        stn.square shouldBe a[Boolean]
        stn.renderColor() shouldBe a[java.awt.Color]
        stn.name shouldBe a[String]
      }
    }
  }
}
