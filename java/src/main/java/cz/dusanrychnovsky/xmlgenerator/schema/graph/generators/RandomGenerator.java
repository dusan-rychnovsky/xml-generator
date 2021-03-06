package cz.dusanrychnovsky.xmlgenerator.schema.graph.generators;

import java.util.List;
import java.util.Random;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import cz.dusanrychnovsky.xmlgenerator.schema.graph.AttributeNode;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.ElementNode;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.RootNode;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.SchemaGraph;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.SequenceNode;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.SetNode;

public class RandomGenerator extends DocumentGenerator {
	
	private final Random rnd = new Random();
	private final SchemaGraph schemaGraph;
	private final RandomValueGenerator valueGenerator = new RandomValueGenerator();
	
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

		String text = valueGenerator.get(elNode.getContentType());
		result.setText(text);

		SequenceNode<ElementNode> rndElSeqNode = rndItem(elNode.getElSeqNodes(), rnd);
        rndElSeqNode.getChildNodes().forEach(
            subElNode -> result.addContent(buildElement(subElNode))
        );
		
		SetNode<AttributeNode> rndAttrSetNode = rndItem(elNode.getAttrSetNodes(), rnd);
        rndAttrSetNode.getChildNodes().forEach(
            attrNode -> result.getAttributes().add(buildAttribute(attrNode))
        );
		
		return result;
	}
	
	/**
	 * 
	 * @param attrNode
	 * @return
	 */
	private Attribute buildAttribute(AttributeNode attrNode) {

		String value = valueGenerator.get(attrNode.getContentType());
		return new Attribute(attrNode.getAttrName(), value);
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
