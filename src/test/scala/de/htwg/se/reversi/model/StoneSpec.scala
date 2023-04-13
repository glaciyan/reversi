package de.htwg.se.reversi.model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class StoneSpec extends AnyWordSpec {
  "A Stone" when {
    "white" should {
      "be O" in {
        Stone.White.toString should be("O")
      }
    }
    "black" should {
      "be X" in {
        Stone.Black.toString should be("X")
      }
    }
    "nothing" should {
      "be whitespace" in {
        Stone.Nothing.toString should be(" ")
      }
    }
  }
}
