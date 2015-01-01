package cz.dusanrychnovsky.xmlgenerator.schema.graph;

public class AttributeDeclaration {
	
	private final String elName;
	private final String attrName;
	private final boolean required;
	
	/**
	 * 
	 * @param elName
	 * @param attrName
	 * @param required
	 */
	public AttributeDeclaration(String elName, String attrName, boolean required) {
		this.attrName = attrName;
		this.elName = elName;
		this.required = required;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAttrName() {
		return attrName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getElName() {
		return elName;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRequired() {
		return required;
	}
}
