package cz.dusanrychnovsky.util.regexp

class Identifier(letter: String) extends Expression {
  
  override def evaluate() = {
    List(new Word(letter))
  }
}
