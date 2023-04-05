package de.htwg.se.reversi.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec {
    "A reversi Field" when {

        // Ab hier fuer den schwarzen Stein X
        "filled with X" should {
            val field1 = new Field(1, Stone.X)
            val field2 = new Field(2, Stone.X)
            val field3 = new Field(3, Stone.X)
            val eol = sys.props("line.separator")
            "have a bar as String of form'+---+---+---+---+---+---+---+---+'" in {
                field3.bar() should be ("+---+---+---+---+---+---+---+---+" + eol)
            }
            "have a scalable bar" in {
                field1.bar(1,1) should be ("+-+" + eol)
                field2.bar(2,1) should be ("+--+" + eol)
                field2.bar(1,2) should be ("+-+-+" + eol)
            }
            "have cells as String of form '| X | X | X | X | X | X | X | X |'" in {
                field3.cells(0) should be("| X | X | X | X | X | X | X | X |" + eol)
            }
            "have scalable cells" in {
                field1.cells(0,1) should be("|X|" + eol)
                field2.cells(0,1) should be("|X|X|" + eol)
                field1.cells(0,3) should be("| X |" + eol)
            }
            "have a mesh in the form" + 
            "+-+" +
            "|X|" + 
            "+-+" in {
                field1.mesh(1) should be("+-+" + eol + "|X|" + eol + "+-+" + eol)
                field2.mesh(2) should be("+-+-+" + eol + "|X|X|" + eol + "+-+-+" + eol + "|X|X|" + eol + "+-+-+" + eol)
            }
        }

        // Ab hier test für den weissen Stein O
            "filled with O" should {
            val field1 = new Field(1, Stone.O)
            val field2 = new Field(2, Stone.O)
            val field3 = new Field(3, Stone.O)
            val eol = sys.props("line.sperator")
            "have a bar as String in form of '+---+---+---+---+---+---+---+---+'" in {
                field3.bar() should be("+---+---+---+---+---+---+---+---+" + eol)
            }
            "have a scalable bar" in {
                field1.bar(1,1) should be ("+-+" + eol)
                field2.bar(2,1) should be ("+--+" + eol)
                field2.bar(1,2) should be ("+-+-+" + eol)
            }
            "have cells as String of form '| O | O | O | O | O | O | O | O |'" in {
                field3.cells(0) should be("| O | O | O | O | O | O | O | O |" + eol)
            }
            "have scalable cells" in {
                field1.cells(0,1) should be("|O|" + eol)
                field2.cells(0,1) should be("|O|O|" + eol)
                field1.cells(0,3) should be("| O |" + eol)
            }
            "have a mesh in the form" + 
            "+-+" +
            "|O|" + 
            "+-+" in {
                field1.mesh(1) should be("+-+" + eol + "|O|" + eol + "+-+" + eol)
                field2.mesh(1) should be("+-+-+" + eol + "|O|O|" + eol + "+-+-+" + eol + "|O|O|" + eol + "+-+-+" + eol)
            }
        }
        "filled with Empty" should {
            var field = new Field(3, Stone.Empty)
            "have two X and two O after two moves" in {
                field.toString should be(("""
                #+---+---+---+---+---+---+---+---+
                #|   |   |   |   |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   |   |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   |   |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   | X | O |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   | O | X |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   |   |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   |   |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   |   |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #""").stripMargin('#'))
            }
            "have for example three X and three O after two moves" in {
                field.put(Stone.X, 6, 4).put(Stone.O, 4, 3).toString should be(("""
                #+---+---+---+---+---+---+---+---+
                #|   |   |   |   |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   |   |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   | O |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   | O | X | X |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   | O | X |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   |   |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   |   |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #|   |   |   |   |   |   |   |   |
                #+---+---+---+---+---+---+---+---+
                #""").stripMargin('#'))
            }
        }
    }
}