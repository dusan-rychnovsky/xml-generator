package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class AttributesSetNode extends SchemaGraphNode {
	
	private final List<AttributeNode> childNodes;
	
	/**
	 * 
	 * @param childNodes
	 */
	public AttributesSetNode(AttributeNode... childNodes) {
		this(Arrays.asList(childNodes));
	}
	
	/**
	 * 
	 * @param childNodes
	 */
	public AttributesSetNode(List<AttributeNode> childNodes) {
		this.childNodes = new ArrayList<AttributeNode>(childNodes);
	}

	/**
	 * 
	 * @return
	 */
	public List<AttributeNode> getChildNodes() {
		return childNodes;
	}
	
    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
    
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof AttributesSetNode)) {
			return false;
		}
		
		AttributesSetNode other = (AttributesSetNode) obj;
		return childNodes.equals(other.childNodes);
	}
	
	@Override
	public int hashCode() {
		return childNodes.hashCode();
	}
	
	@Override
	public String toString() {
		return "attrs(" + StringUtils.join(childNodes, ", ") + ")";
	}
}
