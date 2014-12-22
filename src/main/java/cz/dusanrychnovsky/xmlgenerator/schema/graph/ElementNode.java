package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

public class ElementNode extends SchemaGraphNode {
	
	private final String elName;
	private final List<SequenceNode> seqNodes;

	/**
	 * 
	 * @param elName
	 * @param seqNodes
	 */
	public ElementNode(String elName, SequenceNode... seqNodes) {
		this(elName, asList(seqNodes));
	}
	
	/**
	 * 
	 * @param elName
	 * @param seqNodes
	 */
	public ElementNode(String elName, List<SequenceNode> seqNodes) {
		this.elName = elName;
		this.seqNodes = new ArrayList<>(seqNodes);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof ElementNode)) {
			return false;
		}
		
		ElementNode other = (ElementNode) obj;
		return 
			elName.equals(other.elName) &&
			seqNodes.equals(other.seqNodes);
	}
	
	@Override
	public int hashCode() {
		return elName.hashCode() + seqNodes.hashCode();
	}
	
	@Override
	public String toString() {
		return "e(" + elName + ")";
	}
}
