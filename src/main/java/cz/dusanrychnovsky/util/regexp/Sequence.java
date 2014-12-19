package cz.dusanrychnovsky.util.regexp;

import java.util.ArrayList;
import java.util.List;

public class Sequence {

    private final List<Expression> subExprs;

    /**
     *
     * @param subExprs
     */
    public Sequence(List<Expression> subExprs) {
        this.subExprs = new ArrayList<Expression>(subExprs);
    }
}
