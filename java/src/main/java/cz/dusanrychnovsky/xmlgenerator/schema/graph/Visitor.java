package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class Visitor<T> {
	
	/**
	 * 
	 * @param graph
	 * @return
	 */
	public abstract T visit(SchemaGraph graph);
	
	/**
	 * 
	 * @param rootNode
	 * @return
	 */
	public abstract T visit(RootNode rootNode);
	
	/**
	 * 
	 * @param elNode
	 * @return
	 */
	public abstract T visit(ElementNode elNode);

	/**
	 * 
	 * @param attrNode
	 * @return
	 */
	public abstract T visit(AttributeNode attrNode);
	
	/**
	 * 
	 * @param seqNode
	 * @return
	 */
	public abstract T visit(SequenceNode<? extends SchemaGraphNode> seqNode);
	
	/**
	 * 
	 * @param attrSetNode
	 * @return
	 */
	public abstract T visit(SetNode<? extends SchemaGraphNode> attrSetNode);
	
	/**
	 * 
	 * @param nodes
	 * @return
	 */
	protected List<T> visit(List<SchemaGraphNode> nodes) {
		return
            nodes
                .stream()
                .map(node -> node.accept(this))
                .collect(toList());
	}
}
