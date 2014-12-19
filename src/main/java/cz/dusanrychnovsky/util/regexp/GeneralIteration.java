package cz.dusanrychnovsky.util.regexp;

import cz.dusanrychnovsky.util.regexp.Expression;
import cz.dusanrychnovsky.util.regexp.Iteration;

public class GeneralIteration extends Iteration {

    /**
     *
     * @param maxCard
     * @param subExpr
     */
    public GeneralIteration(int maxCard, Expression subExpr) {
        super(0, maxCard, subExpr);
    }
}
