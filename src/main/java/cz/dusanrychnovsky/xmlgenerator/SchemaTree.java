package cz.dusanrychnovsky.xmlgenerator;

public class SchemaTree {

	private final String content;
	
	public SchemaTree(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return content;
	}
}
