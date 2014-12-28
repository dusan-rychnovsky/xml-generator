package cz.dusanrychnovsky.util.regexp

class Word(val letters: String*) {
  
  override def equals(other: Any): Boolean = {
    other match {
      case w: Word => w.letters == this.letters
      case _ => false
    }
  }
  
  override def hashCode: Int = {
    letters.hashCode
  }
}