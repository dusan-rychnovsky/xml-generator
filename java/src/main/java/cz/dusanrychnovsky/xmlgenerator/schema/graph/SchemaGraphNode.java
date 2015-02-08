package cz.dusanrychnovsky.xmlgenerator.schema.graph;

/**
 * Represents a node in a schema graph.
 * 
 * @author Dušan Rychnovský
 *
 */
public abstract class SchemaGraphNode {

	/**
	 * 
	 * @param visitor
	 * @return
	 */
	public abstract <T> T accept(Visitor<T> visitor);
}
