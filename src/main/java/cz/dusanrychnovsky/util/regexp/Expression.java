package cz.dusanrychnovsky.util.regexp;

public abstract class Expression {

	/**
	 * 
	 * @param visitor
	 * @return
	 */
	public abstract <T> T accept(Visitor<T> visitor);
}
