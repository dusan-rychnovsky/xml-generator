package cz.dusanrychnovsky.util.regexp

import org.scalatest._

class ExpressionSpec extends FunSpec with ShouldMatchers with GivenWhenThen {

  describe("Expression Evaluation") {
    
    it("Allows to evaluate an identifier-like expression.") {
      
      given("an identifier with 'a' as the letter")
      val identifier = new Identifier("a")
      
      when("evaluating")
      val language = identifier.evaluate()
      
      then("the result should be a language that contains just a word 'a'")
      language should have length(1)
      language should contain(new Word("a"))
    }
    
    it("Allows to evaluate a union-like expression.") (pending)
    
    it("Allows to evaluate a concatenation-like expression.") (pending)
    
    it("Allows to evaluate an interation-like expression.") (pending)
  }
}
