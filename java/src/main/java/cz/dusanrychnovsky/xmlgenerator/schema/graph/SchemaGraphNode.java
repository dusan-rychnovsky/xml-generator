package cz.dusanrychnovsky.xmlgenerator.schema.graph;

public abstract class SchemaGraphNode {

	/**
	 * 
	 * @param visitor
	 * @return
	 */
	public abstract <T> T accept(Visitor<T> visitor);
}
