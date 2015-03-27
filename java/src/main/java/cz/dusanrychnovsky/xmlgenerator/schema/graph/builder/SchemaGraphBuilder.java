package cz.dusanrychnovsky.xmlgenerator.schema.graph.builder;

import java.util.*;

import org.apache.commons.collections15.MultiMap;
import org.apache.commons.collections15.multimap.MultiHashMap;

import static cz.dusanrychnovsky.util.ListUtils.map;
import static cz.dusanrychnovsky.util.ListUtils.fold;
import static java.util.Arrays.asList;

import cz.dusanrychnovsky.util.regexp.EvaluateRegexp;
import cz.dusanrychnovsky.util.regexp.Language;
import cz.dusanrychnovsky.util.regexp.Word;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.AttributeNode;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.ElementNode;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.RootNode;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.SchemaGraph;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.SequenceNode;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.SetNode;

/**
 * 
 * @author Du�an Rychnovsk�
 *
 */
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

        if (this.rootElName != null) {
            throw new IllegalStateException(
                "Root element already declared [" + rootElName + "]."
            );
        }

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
    	attrDecls.forEach(attrDecl -> addDeclaration(attrDecl));
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
        RootNode rootNode = new RootNode(rootElNode);
        
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
		
		return new ElementNode(elName, elDecl.getContentType(), attrSetNodes, seqNodes);
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
			asList(new SetNode<>()),
			(acc, attrDecl) -> {
				
				List<SetNode<AttributeNode>> result = new ArrayList<>();
				AttributeNode attrNode = new AttributeNode(attrDecl.getAttrName(), attrDecl.getContentType());
				
				for (SetNode<AttributeNode> item : acc) {
					
					if (!attrDecl.isRequired()) {
						result.add(item);
					}
					
					List<AttributeNode> nodes = new ArrayList<>();
					nodes.addAll(item.getChildNodes());
					nodes.add(attrNode);
					
					result.add(new SetNode<>(nodes));
				}
				
				return result;
			}
		);
	}

	/**
	 * 
	 * @param elDecl
	 * @return
	 */
	private List<SequenceNode<ElementNode>> buildSequenceNodes(ElementDeclaration elDecl) {
		
		List<SequenceNode<ElementNode>> result = new ArrayList<>();
		
		Language language = elDecl.getExpr().accept(evaluate);
        language.getWords().forEach(
            word ->
			    result.add(
                    new SequenceNode<>(
                        map(
                            word.getSymbols(),
                            s -> buildElementNode(s)
                        )
                    )
                )
        );
		
		return result;
	}

	/**
	 * 
	 */
    public void clear() {
    	rootElName = null;
    	elDecls.clear();
        attrDecls.clear();
    }
}
