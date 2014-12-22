package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import cz.dusanrychnovsky.util.regexp.*;
import static cz.dusanrychnovsky.util.ListUtils.*;
import static java.util.Arrays.asList;

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
		
		List<SequenceNode> initial = new ArrayList<SequenceNode>();
		initial.add(new SequenceNode());
		
		return fold(
			visit(sequence.getSubExprs()),
			initial,
			new Product()
		);
	}

	@Override
	public List<SequenceNode> visit(Iteration iteration) {
		
		List<SequenceNode> subResult = iteration.getSubExpr().accept(this);

		List<SequenceNode> initial = new ArrayList<SequenceNode>();
		initial.add(new SequenceNode());
		
		List<List<SequenceNode>> cards = new ArrayList<List<SequenceNode>>();
		for (int c = iteration.getMinCard(); c <= iteration.getMaxCard(); c++) {
			cards.add(
				fold(
					times(subResult, c),
					initial,
					new Product()
				)
			);
		}
		
		return concat(cards);
	}

	private static class Product implements Reduce<List<SequenceNode>> {

		public List<SequenceNode> eval(List<SequenceNode> first, List<SequenceNode> second) {
			
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
}
