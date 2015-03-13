package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import lombok.Data;

/**
 * Represents the root node of a schema graph.
 * 
 * @author Du�an Rychnovsk�
 *
 */
@Data
public class RootNode implements SchemaGraphNode {

    private final SequenceNode<ElementNode> childNode;
    
    /**
     * 
     * @param rootElNode
     */
    public RootNode(ElementNode rootElNode) {
    	this.childNode = new SequenceNode<>(rootElNode);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
}
