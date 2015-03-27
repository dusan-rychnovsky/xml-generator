package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * Represents a sequence node in a schema graph. A sequence node is associated
 * with an ordered collection (a sequence) of child nodes.
 * 
 * A sequence node is meant to represent a sequence of sub-elements of an
 * element.
 * 
 * @author Du�an Rychnovsk�
 *
 * @param <T>
 */
@Value
public class SequenceNode<T extends SchemaGraphNode> implements SchemaGraphNode {
	
	private final List<T> childNodes = new ArrayList<>();

    /**
     *
     * @param childNodes
     */
    public SequenceNode(List<T> childNodes) {
        childNodes.forEach(childNode -> this.childNodes.add(childNode));
    }

	/**
	 * 
	 * @param childNodes
	 */
	public SequenceNode(T... childNodes) {
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
