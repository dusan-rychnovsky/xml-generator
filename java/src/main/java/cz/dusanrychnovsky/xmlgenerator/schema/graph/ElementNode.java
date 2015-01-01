package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.List;

public class ElementNode extends SchemaGraphNode {
	
	private final String elName;
	private final List<AttributesSetNode> attrSetNodes;
	private final List<SequenceNode> childNodes;
	
	/**
	 * 
	 * @param elName
	 * @param attrSetNodes
	 * @param seqNodes
	 */
	public ElementNode(
		String elName, 
		List<AttributesSetNode> attrSetNodes, 
		List<SequenceNode> seqNodes) {
		
		this.elName = elName;
		this.attrSetNodes = new ArrayList<>(attrSetNodes);
		this.childNodes = new ArrayList<>(seqNodes);
	}

	/**
	 * 
	 * @return
	 */
	public String getElName() {
		return elName;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<AttributesSetNode> getAttributesSetNodes() {
		return attrSetNodes;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<SequenceNode> getChildNodes() {
		return childNodes;
	}
	
    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
    
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof ElementNode)) {
			return false;
		}
		
		ElementNode other = (ElementNode) obj;
		return 
			elName.equals(other.elName) &&
			childNodes.equals(other.childNodes);
	}
	
	@Override
	public int hashCode() {
		return elName.hashCode() + childNodes.hashCode();
	}
	
	@Override
	public String toString() {
		return "e(" + elName + ")";
	}
}
