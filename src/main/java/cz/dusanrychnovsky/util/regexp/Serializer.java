package cz.dusanrychnovsky.util.regexp;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * 
 * @author Du�an Rychnovsk�
 *
 */
public class Serializer extends Visitor<String> {

	@Override
	public String visit(Identifier identifier) {
		return identifier.getToken();
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
		builder.append(iteration.getMinCard());
		builder.append(",");
		builder.append(iteration.getMaxCard());
		builder.append("}");
		
		return builder.toString();
	}
}
