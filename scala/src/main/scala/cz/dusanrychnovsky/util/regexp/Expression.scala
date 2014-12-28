package cz.dusanrychnovsky.util.regexp

abstract class Expression {
  def evaluate(): List[List[String]]
}
