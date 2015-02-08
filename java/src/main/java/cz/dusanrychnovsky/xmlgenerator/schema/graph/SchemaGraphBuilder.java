package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections15.MultiMap;
import org.apache.commons.collections15.multimap.MultiHashMap;

import static cz.dusanrychnovsky.util.ListUtils.map;
import static cz.dusanrychnovsky.util.ListUtils.fold;
import cz.dusanrychnovsky.util.regexp.EvaluateRegexp;
import cz.dusanrychnovsky.util.regexp.Language;
import cz.dusanrychnovsky.util.regexp.Word;

public class SchemaGraphBuilder {

	private final EvaluateRegexp evaluate = new EvaluateRegexp();
	
	private final Map<String, ElementDeclaration> elDecls = new HashMap<>();
	private final MultiMap<String, AttributeDeclaration> attrDecls = new MultiHashMap<>();
	private String rootElName;
	
	/**
	 * 
	 * @param elDecl
	 */
	public void addDeclaration(ElementDeclaration elDecl) {
		addDeclaration(elDecl, false);
	}
	
	/**
	 * 
	 * @param elDecl
	 */
    public void addDeclaration(ElementDeclaration elDecl, boolean isRootEl) {
    	
    	String elName = elDecl.getElName();
    	
    	if (elDecls.containsKey(elName)) {
    		throw new IllegalArgumentException(
    			"Element [" + elName + "] already declared."
    		);
    	}
    	
    	elDecls.put(elName, elDecl);
    	
    	if (isRootEl) {
    		setRootElement(elName);
    	}
    }
    
    /**
     * 
     * @param rootElName
     */
    public void setRootElement(String rootElName) {
    	this.rootElName = rootElName;
    }
    
    /**
     * 
     * @param attrDecl
     */
    public void addDeclaration(AttributeDeclaration attrDecl) {
    	attrDecls.put(attrDecl.getElName(), attrDecl);
    }
    
    /**
     * 
     * @param attrDecls
     */
    public void addDeclarations(List<AttributeDeclaration> attrDecls) {
    	for (AttributeDeclaration attrDecl : attrDecls) {
    		addDeclaration(attrDecl);
    	}
    }
    
    /**
     * 
     * @param elName
     * @return
     */
    private List<AttributeDeclaration> getAttrDeclarations(String elName) {
    	
    	if (!attrDecls.containsKey(elName)) {
    		return new ArrayList<>();
    	}
    	
    	List<AttributeDeclaration> result = new ArrayList<>();
    	result.addAll(attrDecls.get(elName));
    	
    	return result;
    }
    
    /**
     * 
     * @return
     */
    public SchemaGraph getResult() {
    	
        if (rootElName == null) {
        	throw new IllegalStateException("Root-element-name not set.");
        }
        
        ElementNode rootElNode = buildElementNode(rootElName);
        RootNode rootNode = new RootNode(new SequenceNode<>(rootElNode));
        
        return new SchemaGraph(rootNode);
    }
    
    /**
     * 
     * @param elName
     * @return
     */
	private ElementNode buildElementNode(String elName) {
		
		if (!elDecls.containsKey(elName)) {
			throw new IllegalStateException(
				"Element [" + elName + "] not declared."
			);
		}
		
		ElementDeclaration elDecl = elDecls.get(elName);
		List<SequenceNode<ElementNode>> seqNodes = buildSequenceNodes(elDecl);
		
		List<AttributeDeclaration> attrDecls = getAttrDeclarations(elName);
		List<SetNode<AttributeNode>> attrSetNodes = buildAttrSetNodes(attrDecls);
		
		return new ElementNode(elName, attrSetNodes, seqNodes);
	}
	
	/**
	 * 
	 * @param attrDecls
	 * @return
	 */
	private List<SetNode<AttributeNode>> buildAttrSetNodes(
		List<AttributeDeclaration> attrDecls) {
		
		return fold(
			attrDecls,
			Arrays.asList(new SetNode<>()),
			(acc, attr) -> {
				
				List<SetNode<AttributeNode>> result = new ArrayList<>();
				AttributeNode attrNode = new AttributeNode(attr.getAttrName());
				
				for (SetNode<AttributeNode> item : acc) {
					
					if (!attr.isRequired()) {
						result.add(item);
					}
					
					List<AttributeNode> nodes = new ArrayList<>();
					nodes.addAll(item.getChildNodes());
					nodes.add(attrNode);
					
					result.add(new SetNode<AttributeNode>(nodes));
				}
				
				return result;
			}
		);
	}

	/**
	 * 
	 * @param elDecl.getExpr()
	 * @return
	 */
	private List<SequenceNode<ElementNode>> buildSequenceNodes(ElementDeclaration elDecl) {
		
		List<SequenceNode<ElementNode>> result = new ArrayList<>();
		
		Language language = elDecl.getExpr().accept(evaluate);
		for (Word word : language.getWords()) {
			result.add(
				new SequenceNode<>(
					map(
						word.getSymbols(),
						s -> buildElementNode(s)
					)
				)
			);
		}
		
		return result;
	}

	/**
	 * 
	 */
    public void clear() {
    	rootElName = null;
    	elDecls.clear();
    }
}
