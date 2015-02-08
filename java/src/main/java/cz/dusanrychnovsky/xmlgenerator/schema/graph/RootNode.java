package cz.dusanrychnovsky.xmlgenerator.schema.graph;

/**
 * Represents the root node of a schema graph.
 * 
 * @author Dušan Rychnovský
 *
 */
public class RootNode extends SchemaGraphNode {

    private final SequenceNode<ElementNode> childNode;
    
    /**
     * 
     * @param rootElNode
     */
    public RootNode(ElementNode rootElNode) {
    	this.childNode = new SequenceNode<>(rootElNode);
    }

    /**
     * 
     * @return
     */
	public SequenceNode<ElementNode> getChildNode() {
		return childNode;
	}
	
    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
    
    @Override
    public String toString() {
        return "r()";
    }
}
