package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * Represents a set node in a schema graph. A set node is associated with an
 * unordered collection (a set) of child nodes.
 * 
 * A set node is meant to represent a set of attributes of an element node.
 * 
 * @author Du�an Rychnovsk�
 *
 * @param <T>
 */
@Data
public class SetNode<T extends SchemaGraphNode> implements SchemaGraphNode {
	
	private final List<T> childNodes = new ArrayList<>();

    /**
     *
     * @param childNodes
     */
    public SetNode(List<T> childNodes) {
        childNodes.forEach(childNode -> this.childNodes.add(childNode));
    }

	/**
	 * 
	 * @param childNodes
	 */
	public SetNode(T... childNodes) {
		this(asList(childNodes));
	}

	/**
	 * 
	 * @return
	 */
	public List<T> getChildNodes() {
		return unmodifiableList(childNodes);
	}
	
    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
}
