package cz.dusanrychnovsky.xmlgenerator.schema.graph;

/**
 * Represents an attribute node in a schema graph.
 * 
 * @author Dušan Rychnovský
 *
 */
public class AttributeNode extends SchemaGraphNode {

	private final String attrName;
	
	/**
	 * 
	 * @param attrName
	 */
    public AttributeNode(String attrName) {
		this.attrName = attrName;
	}
    
    /**
     * 
     * @return
     */
	public String getAttrName() {
		return attrName;
	}
	
	@Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }

	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof AttributeNode)) {
			return false;
		}
		
		AttributeNode other = (AttributeNode) obj;
		return attrName.equals(other.attrName);
	}
	
	@Override
	public int hashCode() {
		return attrName.hashCode();
	}
	
	@Override
	public String toString() {
		return "a(" + attrName + ")";
	}
}
