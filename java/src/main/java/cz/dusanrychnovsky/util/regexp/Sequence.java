package cz.dusanrychnovsky.util.regexp;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

@Value
public class Sequence extends Expression {

    private final List<Expression> subExprs = new ArrayList<>();

    public Sequence(List<Expression> subExprs) {
        subExprs.forEach(subExpr -> this.subExprs.add(subExpr));
    }

    public Sequence(Expression... subExprs) {
        this(asList(subExprs));
    }

    public List<Expression> getSubExprs() {
        return unmodifiableList(subExprs);
    }
    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
}
