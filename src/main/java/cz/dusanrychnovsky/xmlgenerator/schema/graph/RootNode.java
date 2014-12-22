package cz.dusanrychnovsky.xmlgenerator.schema.graph;

public class RootNode extends SchemaGraphNode {

    private final SequenceNode childNode;

    /**
     *
     * @param childNode
     */
    public RootNode(SequenceNode childNode) {
        this.childNode = childNode;
    }

    /**
     * 
     * @return
     */
	public SequenceNode getChildNode() {
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
