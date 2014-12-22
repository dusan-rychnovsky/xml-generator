package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import cz.dusanrychnovsky.util.regexp.*;
import static cz.dusanrychnovsky.util.ListUtils.*;
import static java.util.Arrays.asList;
import static java.util.Collections.nCopies;

import java.util.ArrayList;
import java.util.List;

public class EvaluateRegexp extends Visitor<List<SequenceNode>> {

	@Override
	public List<SequenceNode> visit(Identifier identifier) {
		return asList(
			new SequenceNode(new ElementNode(identifier.getToken()))
		);
	}

	@Override
	public List<SequenceNode> visit(Union union) {
		return concat(visit(union.getSubExprs()));
	}

	@Override
	public List<SequenceNode> visit(Sequence sequence) {
		return visit(sequence.getSubExprs())
			.stream()
			.reduce(asList(new SequenceNode()), (a, v) -> getProduct(a, v));
	}

	@Override
	public List<SequenceNode> visit(Iteration iteration) {
		
		List<SequenceNode> subResult = iteration.getSubExpr().accept(this);

		List<List<SequenceNode>> cards = new ArrayList<List<SequenceNode>>();
		for (int c = iteration.getMinCard(); c <= iteration.getMaxCard(); c++) {
			cards.add(
				nCopies(c, subResult)
					.stream()
					.reduce(asList(new SequenceNode()), (a, v) -> getProduct(a, v))
			);
		}
		
		return concat(cards);
	}

	private List<SequenceNode> getProduct(List<SequenceNode> first, List<SequenceNode> second) {
			
		if (first.isEmpty()) {
			first.add(new SequenceNode());
		}
		
		if (second.isEmpty()) {
			second.add(new SequenceNode());
		}
		
		List<SequenceNode> result = new ArrayList<SequenceNode>();
		
		for (SequenceNode firstNode : first) {
			for (SequenceNode secondNode : second) {
				result.add(firstNode.concatWith(secondNode));
			}
		}
		
		return result;
		
	}
}
