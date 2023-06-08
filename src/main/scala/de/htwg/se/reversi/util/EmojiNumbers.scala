package de.htwg.se.reversi.util

object EmojiNumbers {
  private val map = Map(
    1 -> "1️⃣",
    2 -> "2️⃣",
    3 -> "3️⃣",
    4 -> "4️",
    5 -> "5️⃣",
    6 -> "6️⃣",
    7 -> "7️⃣",
    8 -> "8️⃣",
    9 -> "9️⃣",
    10 -> "🔟"
  )

  def convert(n: Int): String = {
    val e = map.get(n)
    e match
      case Some(value) => value
      case None => n.toString
  }
}
