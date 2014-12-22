package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import static cz.dusanrychnovsky.util.ListUtils.*;
import cz.dusanrychnovsky.util.regexp.*;

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
		
		List<SequenceNode> result = regexp.accept(new EvaluateRegexp());
		assertEquals(39, result.size());
		
		assertTrue(result.contains(sequenceNode("cat")));
		assertTrue(result.contains(sequenceNode("dog", "bird")));
		assertTrue(result.contains(sequenceNode("dog", "pig", "bird")));
		
		assertTrue(result.contains(sequenceNode("cat", "cat")));
		assertTrue(result.contains(sequenceNode("cat", "dog", "bird")));
		assertTrue(result.contains(sequenceNode("cat", "dog", "pig", "bird")));
		assertTrue(result.contains(sequenceNode("dog", "bird", "cat")));
		assertTrue(result.contains(sequenceNode("dog", "pig", "bird", "cat")));
		assertTrue(result.contains(sequenceNode("dog", "bird", "dog", "bird")));
		assertTrue(result.contains(sequenceNode("dog", "pig", "bird", "dog", "bird")));
		assertTrue(result.contains(sequenceNode("dog", "pig", "bird", "dog", "bird")));
		assertTrue(result.contains(sequenceNode("dog", "pig", "bird", "dog", "pig", "bird")));
		
		// ...
	}
	
	/**
	 * 
	 * @param elementNames
	 * @return
	 */
	private SequenceNode sequenceNode(String... elementNames) {
		return new SequenceNode(
			map(
				asList(elementNames),
				new Map<String, ElementNode>() {
					public ElementNode eval(String item) {
						return new ElementNode(item);
					}
				}
			)
		);
	}
}
