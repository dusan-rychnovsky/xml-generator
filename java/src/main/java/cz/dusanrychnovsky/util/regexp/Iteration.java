package cz.dusanrychnovsky.util.regexp;

import lombok.Value;

@Value
public class Iteration extends Expression {

    private final int min;
    private final int max;
    private final Expression subExpr;

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
