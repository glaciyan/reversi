import org.scalatest.funsuite.AnyFunSuite
import de.htwg.se.reversi.model.TuiSpielfeld

class TuiSpielfeldTest extends AnyFunSuite {
  test("Test Spielfeld-Initialisierung") {
    val size = 3
    val spielfeld = new TuiSpielfeld(size)
    spielfeld.init()
    assert(spielfeld.display() == "Spielfeld:\n. . . \n. . . \n. . . \n")
  }

  test("Test Spielfeld-Aktualisierung") {
    val size = 3
    val spielfeld = new TuiSpielfeld(size)
    spielfeld.init()
    spielfeld.update(0, 0, 'X')
    spielfeld.update(1, 1, 'O')
    spielfeld.update(2, 2, 'X')
    assert(spielfeld.display() == "Spielfeld:\nX . . \n. O . \n. . X \n")
  }
}
