package cz.dusanrychnovsky.util.regexp;

import java.util.ArrayList;
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
        this(Arrays.asList(subExprs));
    }
    
    /**
     * 
     * @param subExprs
     */
    public Sequence(List<Expression> subExprs) {
    	this.subExprs = new ArrayList<>(subExprs);
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

    @Override
    public boolean equals(Object obj) {
    	
    	if (!(obj instanceof Sequence)) {
    		return false;
    	}
    	
    	Sequence other = (Sequence) obj;
    	
    	if (subExprs.size() != other.subExprs.size()) {
    		return false;
    	}
    	
    	for (int i = 0; i < subExprs.size(); i++) {
    		if (!subExprs.get(i).equals(other.subExprs.get(i))) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    @Override
    public int hashCode() {
    	return subExprs.hashCode();
    }
}