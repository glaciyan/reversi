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
}
