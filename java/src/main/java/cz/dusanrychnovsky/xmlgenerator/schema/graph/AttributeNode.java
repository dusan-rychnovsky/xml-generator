package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import lombok.Data;

/**
 * Represents an attribute node in a schema graph.
 * 
 * @author Du�an Rychnovsk�
 *
 */
@Data
public class AttributeNode implements SchemaGraphNode {

	private final String attrName;

	@Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
}
