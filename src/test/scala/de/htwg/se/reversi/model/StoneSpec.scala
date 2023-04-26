package de.htwg.se.reversi.model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class StoneSpec extends AnyWordSpec {
  "A Stone" when {
    "white" should {
      "be white dot" in {
        Stone.White.toString should be("⚪")
      }
    }
    "black" should {
      "be black dot" in {
        Stone.Black.toString should be("⚫")
      }
    }
    "nothing" should {
      "be green square" in {
        Stone.Nothing.toString should be("🟩")
      }
    }
  }
  "Number Stone" when {
    "created" should {
      "work" in {
        Stone.getNumber(0).get should be("0️⃣")
        Stone.getNumber(1).get should be("1️⃣")
        Stone.getNumber(2).get should be("2️⃣")
        Stone.getNumber(3).get should be("3️⃣")
        Stone.getNumber(4).get should be("4️⃣")
        Stone.getNumber(5).get should be("5️⃣")
        Stone.getNumber(6).get should be("6️⃣")
        Stone.getNumber(7).get should be("7️⃣")
        Stone.getNumber(8).get should be("8️⃣")
        Stone.getNumber(123) should be(None)
      }
    }
  }
}
