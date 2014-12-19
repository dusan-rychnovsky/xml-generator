package cz.dusanrychnovsky.util.regexp;

import java.util.ArrayList;
import java.util.List;

public class Union {

    private final List<Expression> subExprs;

    /**
     *
     * @param subExprs
     */
    public Union(List<Expression> subExprs) {
        this.subExprs = new ArrayList<Expression>(subExprs);
    }
}
