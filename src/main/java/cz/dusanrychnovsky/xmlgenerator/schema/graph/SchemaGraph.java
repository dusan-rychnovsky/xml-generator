package cz.dusanrychnovsky.xmlgenerator.schema.graph;

public class SchemaGraph {

    private final RootNode rootNode;

    /**
     *
     * @param rootNode
     */
    public SchemaGraph(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
	public String toString() {
		return rootNode.toString();
	}
}
