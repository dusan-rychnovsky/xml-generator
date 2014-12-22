package cz.dusanrychnovsky.xmlgenerator.schema.graph;

public class ElementNode extends SchemaGraphNode {
	
	private final String elementName;

	/**
	 * 
	 * @param elementName
	 */
	public ElementNode(String elementName) {
		this.elementName = elementName;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof ElementNode)) {
			return false;
		}
		
		ElementNode other = (ElementNode) obj;
		return elementName.equals(other.elementName);
	}
	
	@Override
	public int hashCode() {
		return elementName.hashCode();
	}
	
	@Override
	public String toString() {
		return "e(" + elementName + ")";
	}
}
