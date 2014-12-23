package cz.dusanrychnovsky.util.regexp;

public class PositiveIteration extends Iteration {

    /**
     *
     * @param maxCard
     * @param subExpr
     */
    public PositiveIteration(int maxCard, Expression subExpr) {
        super(1, maxCard, subExpr);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
}
