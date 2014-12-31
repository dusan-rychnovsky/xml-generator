package cz.dusanrychnovsky.util.regexp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class EvaluateRegexpTest {
	
	private static final String CAT = "cat";
	private static final String DOG = "dog";
	private static final String BIRD = "bird";
	private static final String PIG = "pig";
	
	private final Visitor<Language> evaluate = new EvaluateRegexp();

	@Test
	public void identifierEvaluatesToASingleElementList() {
		
		Expression identifier = new Identifier(CAT);
		
		Language language = identifier.accept(evaluate);
		List<Word> words = language.getWords();
		
		assertEquals(1, words.size());
		assertTrue(words.contains(new Word(CAT)));
	}
	
	@Test
	public void unionEvaluatesToTheUnionOfPartialLists() {
		
		Expression union = new Union(
			new Union(
				new Identifier(CAT), 
				new Identifier(DOG)
			),
			new Union(
				new Identifier(BIRD),
				new Identifier(CAT),
				new Identifier(PIG)
			)
		);
		
		Language language = union.accept(evaluate);
		List<Word> words = language.getWords();
		
		assertEquals(4, words.size());
		assertTrue(words.contains(new Word(CAT)));
		assertTrue(words.contains(new Word(DOG)));
		assertTrue(words.contains(new Word(BIRD)));
		assertTrue(words.contains(new Word(PIG)));
	}
	
	@Test
	public void sequenceEvaluatesToTheConcatenationOfPartialLists() {
		
		Expression sequence = new Sequence(
			new Identifier(CAT),
			new Union(
				new Identifier(DOG),
				new Identifier(BIRD)
			),
			new Identifier(PIG)
		);
		
		Language language = sequence.accept(evaluate);
		List<Word> words = language.getWords();
		
		assertEquals(2, words.size());
		assertTrue(words.contains(new Word(CAT, DOG, PIG)));
		assertTrue(words.contains(new Word(CAT, BIRD, PIG)));
	}
	
	@Test
	public void iterationEvaluatesToTheUnionOfConcatenationsForIndividualCardinalities() {
		
		Expression iteration = new Iteration(
			0, 2,
			new Union(
				new Identifier(CAT),
				new Identifier(DOG)
			)
		);
		
		Language language = iteration.accept(evaluate);
		List<Word> words = language.getWords();
		
		assertEquals(7, words.size());
	}
}
