package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Represents an element node in a schema graph.
 * 
 * @author Du�an Rychnovsk�
 *
 */
@Value
public class ElementNode implements SchemaGraphNode {
	
	private final String elName;
	private final List<SetNode<AttributeNode>> attrSetNodes = new ArrayList<>();
	private final List<SequenceNode<ElementNode>> elSeqNodes = new ArrayList<>();
	
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
        attrSetNodes.forEach(setNode -> this.attrSetNodes.add(setNode));
        elSeqNodes.forEach(seqNode -> this.elSeqNodes.add(seqNode));
	}

	/**
	 * 
	 * @return
	 */
	public List<SetNode<AttributeNode>> getAttrSetNodes() {
		return unmodifiableList(attrSetNodes);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<SequenceNode<ElementNode>> getElSeqNodes() {
		return unmodifiableList(elSeqNodes);
	}
	
    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
}
