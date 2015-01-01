package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.List;

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
	public abstract T visit(SequenceNode seqNode);
	
	/**
	 * 
	 * @param attrSetNode
	 * @return
	 */
	public abstract T visit(AttributesSetNode attrSetNode);
	
	/**
	 * 
	 * @param nodes
	 * @return
	 */
	protected List<T> visit(List<SchemaGraphNode> nodes) {
		
		List<T> result = new ArrayList<T>();
		
		for (SchemaGraphNode node : nodes) {
			result.add(node.accept(this));
		}
		
		return result;
	}
}
