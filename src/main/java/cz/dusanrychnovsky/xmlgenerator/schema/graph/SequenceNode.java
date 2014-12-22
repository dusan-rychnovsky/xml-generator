package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SequenceNode extends SchemaGraphNode {
	
	private final List<ElementNode> childNodes;
	
	/**
	 * 
	 * @param childNodes
	 */
	public SequenceNode(ElementNode... childNodes) {
		this(Arrays.asList(childNodes));
	}
	
	/**
	 * 
	 * @param childNodes
	 */
	public SequenceNode(List<ElementNode> childNodes) {
		this.childNodes = new ArrayList<ElementNode>(childNodes);
	}
	
	/**
	 * 
	 * @param secondNode
	 * @return
	 */
	public SequenceNode concatWith(SequenceNode other) {
		
		List<ElementNode> childNodes = new ArrayList<ElementNode>();
		childNodes.addAll(this.childNodes);
		childNodes.addAll(other.childNodes);
		
		return new SequenceNode(childNodes);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof SequenceNode)) {
			return false;
		}
		
		SequenceNode other = (SequenceNode) obj;
		return childNodes.equals(other.childNodes);
	}
	
	@Override
	public int hashCode() {
		return childNodes.hashCode();
	}
	
	@Override
	public String toString() {
		return "s(" + StringUtils.join(childNodes, ", ") + ")";
	}
}
