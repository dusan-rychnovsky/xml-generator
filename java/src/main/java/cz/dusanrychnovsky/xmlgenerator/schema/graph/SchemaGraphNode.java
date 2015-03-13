package cz.dusanrychnovsky.xmlgenerator.schema.graph;

/**
 * Represents a node in a schema graph.
 * 
 * @author Du�an Rychnovsk�
 *
 */
public interface SchemaGraphNode {

	/**
	 * 
	 * @param visitor
	 * @return
	 */
	public abstract <T> T accept(Visitor<T> visitor);
}
