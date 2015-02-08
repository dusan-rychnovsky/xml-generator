package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents a sequence node in a schema graph. A sequence node is associated
 * with an ordered collection (a sequence) of child nodes.
 * 
 * A sequence node is meant to represent a sequence of sub-elements of an
 * element.
 * 
 * @author Dušan Rychnovský
 *
 * @param <T>
 */
public class SequenceNode<T extends SchemaGraphNode> extends SchemaGraphNode {
	
	private final List<T> childNodes;
	
	/**
	 * 
	 * @param childNodes
	 */
	public SequenceNode(T... childNodes) {
		this(Arrays.asList(childNodes));
	}
	
	/**
	 * 
	 * @param childNodes
	 */
	public SequenceNode(List<T> childNodes) {
		this.childNodes = new ArrayList<T>(childNodes);
	}

	/**
	 * 
	 * @return
	 */
	public List<T> getChildNodes() {
		return childNodes;
	}
	
    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
    
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof SequenceNode<?>)) {
			return false;
		}
		
		SequenceNode<T> other = (SequenceNode<T>) obj;
		return childNodes.equals(other.childNodes);
	}
	
	@Override
	public int hashCode() {
		return childNodes.hashCode();
	}
	
	@Override
	public String toString() {
		return "els(" + StringUtils.join(childNodes, ", ") + ")";
	}
}
