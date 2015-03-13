package cz.dusanrychnovsky.util.regexp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

@Data
public class Union extends Expression {

    private final List<Expression> subExprs = new ArrayList<>();

    public Union(List<Expression> subExprs) {
        subExprs.forEach(subExpr -> this.subExprs.add(subExpr));
    }

    public Union(Expression... subExprs) {
        this(asList(subExprs));
    }

    public List<Expression> getSubExprs() {
        return unmodifiableList(subExprs);
    }

    private List<Expression> getSubExprs(List<Expression> subExprs) {
        return unmodifiableList(subExprs);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
}
