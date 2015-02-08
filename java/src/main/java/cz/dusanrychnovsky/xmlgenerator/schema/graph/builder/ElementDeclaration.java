package cz.dusanrychnovsky.xmlgenerator.schema.graph.builder;

import cz.dusanrychnovsky.util.regexp.Expression;

public class ElementDeclaration {

	private final String elName;
	private final Expression expr;
	
	/**
	 * 
	 * @param elName
	 * @param expr
	 */
	public ElementDeclaration(String elName, Expression expr) {
		this.elName = elName;
		this.expr = expr;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getElName() {
		return elName;
	}
	
	/**
	 * 
	 * @return
	 */
	public Expression getExpr() {
		return expr;
	}
}
