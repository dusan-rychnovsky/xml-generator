package cz.dusanrychnovsky.xmlgenerator.schema.graph;

public class SchemaGraph {

    private final RootNode rootNode;

    /**
     *
     * @param rootNode
     */
    public SchemaGraph(RootNode rootNode) {
        this.rootNode = rootNode;
    }
    
    /**
     * 
     * @return
     */
	public RootNode getRootNode() {
		return rootNode;
	}
	
	/**
	 * 
	 * @param visitor
	 * @return
	 */
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
	
    @Override
	public String toString() {
		return rootNode.toString();
	}
}
