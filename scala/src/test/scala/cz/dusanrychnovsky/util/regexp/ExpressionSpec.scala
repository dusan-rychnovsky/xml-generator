package cz.dusanrychnovsky.util.regexp

import org.scalatest._

class ExpressionSpec extends FunSpec with ShouldMatchers with GivenWhenThen {

  // TODO: Use Word instead of List[String] maybe? Have to find out how to have
  // Word conform to List[String], though ...
  
  // TODO: Change ListUtils.union to an implicit List[List[T]] method.   
  
  describe("Expression Evaluation") {
    
    it("Allows to evaluate an identifier-like expression.") {
      
      given("an identifier with 'a' as the letter")
      val identifier = new Identifier("a")
      
      when("evaluating")
      val language = identifier.evaluate()
      
      then("the result should be a language that contains just a word 'a'")
      language should have length(1)
      language should contain(List("a"))
    }
    
    it("Allows to evaluate a union-like expression.") {
      
      given("a union with a list of sub-expressions")
      val union = new Union(
        new MockExpression(List("a"), List("a", "b"), List("c")),
        new MockExpression(List("c"), List("d"))
      )
      
      when("evaluating")
      var language = union.evaluate()
      
      then("the result should be a union of the original languages")
      language should have length(4)
      language should contain(List("a"))
      language should contain(List("a", "b"))
      language should contain(List("c"))
      language should contain(List("d"))
    }
    
    
    it("Allows to evaluate a concatenation-like expression.") (pending)
    
    it("Allows to evaluate an interation-like expression.") (pending)
  }
  
  private class MockExpression(language: List[String]*) extends Expression {
    override def evaluate() = language.toList
  } 
}
