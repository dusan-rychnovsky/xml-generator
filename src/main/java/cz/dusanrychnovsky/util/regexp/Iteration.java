package cz.dusanrychnovsky.util.regexp;

public abstract class Iteration extends Expression {

    private final int minCard;
    private final int maxCard;
    private final Expression subExpr;

    /**
     *
     * @param minCard
     * @param maxCard
     * @param subExpr
     */
    public Iteration(int minCard, int maxCard, Expression subExpr) {
        this.minCard = minCard;
        this.maxCard = maxCard;
        this.subExpr = subExpr;
    }
    
    /**
     * 
     * @return
     */
    public int getMinCard() {
    	return minCard;
    }
    
    /**
     * 
     * @return
     */
    public int getMaxCard() {
    	return maxCard;
    }
    
    /**
     * 
     * @return
     */
    public Expression getSubExpr() {
    	return subExpr;
    }
    

    @Override
    public boolean equals(Object obj) {
    	
    	if (!(obj instanceof Iteration)) {
    		return false;
    	}
    	
    	Iteration other = (Iteration) obj;
    	return 
    		subExpr.equals(other.subExpr) &&
    		(minCard == other.minCard) &&
    		(maxCard == other.maxCard);
    }
    
    @Override
    public int hashCode() {
    	return subExpr.hashCode() + minCard + maxCard;
    }
}
