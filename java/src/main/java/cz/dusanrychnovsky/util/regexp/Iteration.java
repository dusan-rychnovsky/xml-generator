package cz.dusanrychnovsky.util.regexp;

public class Iteration extends Expression {

    private final int min;
    private final int max;
    private final Expression subExpr;

    /**
     *
     * @param min
     * @param max
     * @param subExpr
     */
    public Iteration(int min, int max, Expression subExpr) {
        this.min = min;
        this.max = max;
        this.subExpr = subExpr;
    }
    
    /**
     * 
     * @return
     */
    public int getMin() {
    	return min;
    }
    
    /**
     * 
     * @return
     */
    public int getMax() {
    	return max;
    }
    
    /**
     * 
     * @return
     */
    public Expression getSubExpr() {
    	return subExpr;
    }
    
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
	
    @Override
    public boolean equals(Object obj) {
    	
    	if (!(obj instanceof Iteration)) {
    		return false;
    	}
    	
    	Iteration other = (Iteration) obj;
    	return 
    		subExpr.equals(other.subExpr) &&
    		(min == other.min) &&
    		(max == other.max);
    }
    
    @Override
    public int hashCode() {
    	return subExpr.hashCode() + min + max;
    }
}
