package cz.dusanrychnovsky.xmlgenerator;

public class SchemaGraph {

	private final String content;
	
	public SchemaGraph(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return content;
	}
}
