package cz.dusanrychnovsky.util.regexp;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class SerializeRegexp extends Visitor<String> {

	@Override
	public String visit(Identifier identifier) {
		return identifier.getSymbol();
	}

	@Override
	public String visit(Union union) {
		return "(" + join(visit(union.getSubExprs()), "|") + ")";
	}
	
	@Override
	public String visit(Sequence sequence) {
		return "(" + join(visit(sequence.getSubExprs()), ",") + ")";
	}
	
	@Override
	public String visit(Iteration iteration) {
		
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append(iteration.getSubExpr().accept(this));
		builder.append(")");
		builder.append("{");
		builder.append(iteration.getMin());
		builder.append(",");
		builder.append(iteration.getMax());
		builder.append("}");
		
		return builder.toString();
	}
}
