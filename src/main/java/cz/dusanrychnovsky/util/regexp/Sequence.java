package cz.dusanrychnovsky.util.regexp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sequence extends Expression {

    private final List<Expression> subExprs;

    /**
     *
     * @param subExprs
     */
    public Sequence(Expression... subExprs) {
        this.subExprs = Arrays.asList(subExprs);
    }
    
    /**
     * 
     * @return
     */
    public List<Expression> getSubExprs() {
    	return Collections.unmodifiableList(subExprs);
    }
    
    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
}
