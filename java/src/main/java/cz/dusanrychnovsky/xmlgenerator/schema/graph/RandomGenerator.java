package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.List;
import java.util.Random;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

public class RandomGenerator extends DocumentGenerator {
	
	private final Random rnd = new Random();
	private final SchemaGraph schemaGraph;
	
	/**
	 * 
	 * @param schemaGraph
	 */
	public RandomGenerator(SchemaGraph schemaGraph) {
		this.schemaGraph = schemaGraph;
	}
	
	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Document next() {
		
		ElementNode rootElNode = getRootElNode(schemaGraph);
		Element rootEl = buildElement(rootElNode);
				
		return new Document(rootEl);
	}
	
	/**
	 * 
	 * @param elNode
	 * @return
	 */
	private Element buildElement(ElementNode elNode) {
		
		Element result = new Element(elNode.getElName());
		
		SequenceNode rndSeqNode = rndItem(elNode.getChildNodes(), rnd);
		for (ElementNode subElNode : rndSeqNode.getChildNodes()) {
			result.addContent(buildElement(subElNode));
		}
		
		AttributesSetNode rndAttrSetNode = rndItem(elNode.getAttributesSetNodes(), rnd);
		for (AttributeNode attrNode : rndAttrSetNode.getChildNodes()) {
			result.getAttributes().add(buildAttribute(attrNode));
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param attrNode
	 * @return
	 */
	private Attribute buildAttribute(AttributeNode attrNode) {
		return new Attribute(attrNode.getAttrName(), "");
	}
	
	/**
	 * 
	 * @param list
	 * @param rnd
	 * @return
	 */
	private <T> T rndItem(List<T> list, Random rnd) {
		return list.get(rnd.nextInt(list.size()));
	}

	/**
	 * 
	 * @param graph
	 * @return
	 */
	private ElementNode getRootElNode(SchemaGraph graph) {
		
		RootNode rootNode = graph.getRootNode();
		
		List<ElementNode> nodes = rootNode.getChildNode().getChildNodes();
		if (nodes.size() != 1) {
			throw new IllegalStateException(
				"Invalid schema graph - exactly one root element node is expected."
			);
		}
		
		return nodes.get(0);
	}
}
