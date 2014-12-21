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
}
