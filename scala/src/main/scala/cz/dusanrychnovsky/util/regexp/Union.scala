package cz.dusanrychnovsky.util.regexp

import cz.dusanrychnovsky.util.ListUtils._

class Union(subExprs: Expression*) extends Expression {
  
  override def evaluate() = {
    union(subExprs.toList.map(e => e.evaluate))
  }
}
