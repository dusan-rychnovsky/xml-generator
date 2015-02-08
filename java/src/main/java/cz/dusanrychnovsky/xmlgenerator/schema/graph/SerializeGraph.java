package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import static org.apache.commons.lang3.StringUtils.join;

import java.util.ArrayList;
import java.util.List;

public class SerializeGraph extends Visitor<String> {

	@Override
	public String visit(SchemaGraph graph) {
		return graph.getRootNode().accept(this);
	}

	@Override
	public String visit(RootNode rootNode) {
		return rootNode.getChildNode().accept(this);
	}

	@Override
	public String visit(ElementNode elNode) {
		
		// apparently, Java does not allow to convert a List<V> to List<U>
		// even when V extends U
		List<SchemaGraphNode> childNodes = new ArrayList<>();
		childNodes.addAll(elNode.getElSeqNodes());
		
		StringBuilder builder = new StringBuilder();
		builder.append("e(");
		builder.append(elNode.getElName());
		builder.append(" - ");
		builder.append(join(visit(childNodes), ", "));
		builder.append(")");
		
		return builder.toString();
	}

	@Override
	public String visit(AttributeNode attrNode) {
		return "a(" + attrNode.getAttrName() + ")";
	}

	@Override
	public String visit(SequenceNode<? extends SchemaGraphNode> seqNode) {

		// apparently, Java does not allow to convert a List<V> to List<U>
		// even when V extends U
		List<SchemaGraphNode> childNodes = new ArrayList<>();
		childNodes.addAll(seqNode.getChildNodes());
		
		return "els(" + join(visit(childNodes), ", ") + ")";
	}

	@Override
	public String visit(SetNode<? extends SchemaGraphNode> attrSetNode) {

		// apparently, Java does not allow to convert a List<V> to List<U>
		// even when V extends U
		List<SchemaGraphNode> childNodes = new ArrayList<>();
		childNodes.addAll(attrSetNode.getChildNodes());
		
		return "attrs(" + join(visit(childNodes), ", ") + ")";
	}
}
