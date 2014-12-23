package cz.dusanrychnovsky.util.regexp;

import java.util.ArrayList;
import java.util.List;

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
		
		List<T> result = new ArrayList<T>();
		
		for (Expression expr : exprs) {
			result.add(expr.accept(this));
		}
		
		return result;
	}
}
