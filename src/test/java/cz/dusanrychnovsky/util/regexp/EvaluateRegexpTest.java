package cz.dusanrychnovsky.util.regexp;

import static java.util.Arrays.asList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

public class EvaluateRegexpTest {

	@Test
	public void canSerializeARegexp() {
		
		Expression regexp = new PositiveIteration(
			3,
			new Union(
				new Identifier("cat"),
				new Sequence(
					new Identifier("dog"),
					new SimpleIteration(
						new Identifier("pig")
					),
					new Identifier("bird")
				)
			)
		);
		
		List<List<String>> result = regexp.accept(new EvaluateRegexp());
		assertEquals(39, result.size());
		
		assertTrue(result.contains(asList("cat")));
		assertTrue(result.contains(asList("dog", "bird")));
		assertTrue(result.contains(asList("dog", "pig", "bird")));
		
		assertTrue(result.contains(asList("cat", "cat")));
		assertTrue(result.contains(asList("cat", "dog", "bird")));
		assertTrue(result.contains(asList("cat", "dog", "pig", "bird")));
		assertTrue(result.contains(asList("dog", "bird", "cat")));
		assertTrue(result.contains(asList("dog", "pig", "bird", "cat")));
		assertTrue(result.contains(asList("dog", "bird", "dog", "bird")));
		assertTrue(result.contains(asList("dog", "pig", "bird", "dog", "bird")));
		assertTrue(result.contains(asList("dog", "pig", "bird", "dog", "bird")));
		assertTrue(result.contains(asList("dog", "pig", "bird", "dog", "pig", "bird")));
		
		// ...
	}
}
