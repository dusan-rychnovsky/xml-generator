package cz.dusanrychnovsky.util.regexp;

import static cz.dusanrychnovsky.util.ListUtils.*;
import static java.util.Arrays.asList;
import static java.util.Collections.nCopies;

import java.util.ArrayList;
import java.util.List;

public class EvaluateRegexp extends Visitor<List<List<String>>> {

	@Override
	public List<List<String>> visit(Identifier identifier) {
		return asList(asList(identifier.getToken()));
	}

	@Override
	public List<List<String>> visit(Union union) {
		return concat(visit(union.getSubExprs()));
	}

	@Override
	public List<List<String>> visit(Sequence sequence) {
		return visit(sequence.getSubExprs())
			.stream()
			.reduce(asList(new ArrayList<String>()), (a, v) -> getProduct(a, v));
	}

	@Override
	public List<List<String>> visit(Iteration iteration) {
		
		List<List<String>> subResult = iteration.getSubExpr().accept(this);

		List<List<List<String>>> cards = new ArrayList<List<List<String>>>();
		for (int c = iteration.getMinCard(); c <= iteration.getMaxCard(); c++) {
			cards.add(
				nCopies(c, subResult)
					.stream()
					.reduce(asList(new ArrayList<String>()), (a, v) -> getProduct(a, v))
			);
		}
		
		return concat(cards);
	}

	private List<List<String>> getProduct(List<List<String>> first, List<List<String>> second) {
			
		if (first.isEmpty()) {
			first.add(new ArrayList<String>());
		}
		
		if (second.isEmpty()) {
			second.add(new ArrayList<String>());
		}
		
		List<List<String>> result = new ArrayList<List<String>>();
		
		for (List<String> firstNode : first) {
			for (List<String> secondNode : second) {
				result.add(concat(firstNode, secondNode));
			}
		}
		
		return result;
		
	}
}
