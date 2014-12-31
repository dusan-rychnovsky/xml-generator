package cz.dusanrychnovsky.util.regexp;

import static java.util.Collections.nCopies;

import java.util.ArrayList;
import java.util.List;

public class EvaluateRegexp extends Visitor<Language> {

	@Override
	public Language visit(Identifier identifier) {
		return new Language(new Word(identifier.getSymbol()));
	}

	@Override
	public Language visit(Union union) {
		return Language.union(visit(union.getSubExprs()));
	}

	@Override
	public Language visit(Sequence sequence) {
		return Language.concat(visit(sequence.getSubExprs()));
	}

	@Override
	public Language visit(Iteration iteration) {
		
		Language subResult = iteration.getSubExpr().accept(this);

		List<Language> languages = new ArrayList<Language>();
		for (int c = iteration.getMin(); c <= iteration.getMax(); c++) {
			languages.add(Language.concat(nCopies(c, subResult)));
		}
		
		return Language.union(languages);
	}
}
