package de.htwg.se.reversi.model

import de.htwg.se.reversi.model.stone.{Stone, StoneState}

trait IField {
  def size: Int

  val eol: String

  def row(row: Int): Vector[Stone]

  def put(row: Int, col: Int, stone: Stone): IField

  def getStone(row: Int, col: Int): Option[Stone]

  def getPossibleMoves(goodGuy: StoneState, enemy: StoneState): List[Move]

  def hasStonePlaced(row: Int, col: Int): Boolean

  def getWinner(goodGuy: StoneState, enemy: StoneState): Option[StoneState]
}
