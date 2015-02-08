package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an element node in a schema graph.
 * 
 * @author Dušan Rychnovský
 *
 */
public class ElementNode extends SchemaGraphNode {
	
	private final String elName;
	private final List<SetNode<AttributeNode>> attrSetNodes;
	private final List<SequenceNode<ElementNode>> elSeqNodes;
	
	/**
	 * 
	 * @param elName
	 * @param attrSetNodes
	 * @param elSeqNodes
	 */
	public ElementNode(
		String elName, 
		List<SetNode<AttributeNode>> attrSetNodes, 
		List<SequenceNode<ElementNode>> elSeqNodes) {
		
		this.elName = elName;
		this.attrSetNodes = new ArrayList<>(attrSetNodes);
		this.elSeqNodes = new ArrayList<>(elSeqNodes);
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
	public List<SetNode<AttributeNode>> getAttrSetNodes() {
		return attrSetNodes;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<SequenceNode<ElementNode>> getElSeqNodes() {
		return elSeqNodes;
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
			elSeqNodes.equals(other.elSeqNodes);
	}
	
	@Override
	public int hashCode() {
		return elName.hashCode() + elSeqNodes.hashCode();
	}
	
	@Override
	public String toString() {
		return "e(" + elName + ")";
	}
}
