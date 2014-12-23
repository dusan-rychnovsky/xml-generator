package cz.dusanrychnovsky.util.regexp;

public class SimpleIteration extends Iteration {

    /**
     *
     * @param subExpr
     */
    public SimpleIteration(Expression subExpr) {
        super(0, 1, subExpr);
    }
    

    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
}
