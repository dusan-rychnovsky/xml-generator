package cz.dusanrychnovsky.xmlgenerator.schema.graph;

public class RootNode extends SchemaGraphNode {

    private final SequenceNode childNode;

    /**
     *
     * @param childNode
     */
    public RootNode(SequenceNode childNode) {
        this.childNode = childNode;
    }

    @Override
    public String toString() {
        return "SchemaTree";
    }
}
