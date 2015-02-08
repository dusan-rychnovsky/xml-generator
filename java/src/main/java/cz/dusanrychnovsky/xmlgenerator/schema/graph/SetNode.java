package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents a set node in a schema graph. A set node is associated with an
 * unordered collection (a set) of child nodes.
 * 
 * A set node is meant to represent a set of attributes of an element node.
 * 
 * @author Dušan Rychnovský
 *
 * @param <T>
 */
public class SetNode<T extends SchemaGraphNode> extends SchemaGraphNode {
	
	private final List<T> childNodes;
	
	/**
	 * 
	 * @param childNodes
	 */
	public SetNode(T... childNodes) {
		this(Arrays.asList(childNodes));
	}
	
	/**
	 * 
	 * @param childNodes
	 */
	public SetNode(List<T> childNodes) {
		this.childNodes = new ArrayList<>(childNodes);
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
		
		if (!(obj instanceof SetNode)) {
			return false;
		}
		
		SetNode<T> other = (SetNode<T>) obj;
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
