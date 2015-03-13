package cz.dusanrychnovsky.util.regexp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public abstract class Visitor<T> {

	/**
	 *
	 * @param identifier
	 * @return
	 */
	public abstract T visit(Identifier identifier);
	
	/**
	 * 
	 * @param union
	 * @return
	 */
	public abstract T visit(Union union);
	
	/**
	 * 
	 * @param sequence
	 * @return
	 */
	public abstract T visit(Sequence sequence);
	
	/**
	 * 
	 * @param iteration
	 * @return
	 */
	public abstract T visit(Iteration iteration);
	
	/**
	 * 
	 * @param exprs
	 * @return
	 */
	protected List<T> visit(List<Expression> exprs) {
        return
            exprs
                .stream()
                .map(expr -> expr.accept(this))
                .collect(toList());
	}
}
