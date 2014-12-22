package cz.dusanrychnovsky.util.regexp;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerializeRegexpTest {

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
		
		assertEquals(
			"((cat|(dog,(pig){0,1},bird))){1,3}",
			regexp.accept(new SerializeRegexp())
		);
	}

}
