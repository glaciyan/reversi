package de.htwg.se.reversi.model

enum Stone(view: Option[String]) {
  override def toString: String = view.getOrElse("🟩")

  case White extends Stone(Some("⚪"))
  case Black extends Stone(Some("⚫"))
  case Nothing extends Stone(None)
}
