package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import cz.dusanrychnovsky.util.regexp.EvaluateRegexp;
import cz.dusanrychnovsky.util.regexp.Expression;
import cz.dusanrychnovsky.util.regexp.Language;
import cz.dusanrychnovsky.util.regexp.Word;

public class SchemaGraphBuilder {

	private final EvaluateRegexp evaluate = new EvaluateRegexp();
	
	private final Map<String, Expression> elDecls = new HashMap<String, Expression>();
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
    	
    	elDecls.put(elName, elDecl.getExpr());
    	
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
     * @return
     */
    public SchemaGraph getResult() {
    	
        if (rootElName == null) {
        	throw new IllegalStateException("Root-element-name not set.");
        }
        
        ElementNode rootElNode = buildElementNode(rootElName);
        RootNode rootNode = new RootNode(new SequenceNode(rootElNode));
        
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
		
		Expression expr = elDecls.get(elName);
		
		List<SequenceNode> seqNodes = buildSequenceNodes(expr);
		return new ElementNode(elName, seqNodes);
	}
	
	/**
	 * 
	 * @param expr
	 * @return
	 */
	private List<SequenceNode> buildSequenceNodes(Expression expr) {
		
		List<SequenceNode> result = new ArrayList<SequenceNode>();
		
		Language language = expr.accept(evaluate);
		for (Word word : language.getWords()) {
			result.add(
				new SequenceNode(
					word.getSymbols().stream()
						.map(s -> buildElementNode(s))
						.collect(toList())
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
