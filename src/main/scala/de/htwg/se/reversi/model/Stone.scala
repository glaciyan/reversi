package de.htwg.se.reversi.model

enum Stone(view: Option[String]) {
  override def toString: String = view.getOrElse("🟩")

  case White extends Stone(Some("⚪"))
  case Black extends Stone(Some("⚫"))
  case Nothing extends Stone(None)
}

object Stone {
  def getNumber(n: Int): Option[String] = n match {
    case 0 => Some("0️⃣")
    case 1 => Some("1️⃣")
    case 2 => Some("2️⃣")
    case 3 => Some("3️⃣")
    case 4 => Some("4️⃣")
    case 5 => Some("5️⃣")
    case 6 => Some("6️⃣")
    case 7 => Some("7️⃣")
    case 8 => Some("8️⃣")
    case _ => None
  }
}