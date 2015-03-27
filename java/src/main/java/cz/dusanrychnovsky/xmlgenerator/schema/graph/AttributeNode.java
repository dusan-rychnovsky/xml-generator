package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import lombok.Value;

/**
 * Represents an attribute node in a schema graph.
 * 
 * @author Du�an Rychnovsk�
 *
 */
@Value
public class AttributeNode implements SchemaGraphNode {

	private final String attrName;

	@Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
}
