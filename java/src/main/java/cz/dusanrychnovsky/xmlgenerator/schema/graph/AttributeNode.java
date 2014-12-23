package cz.dusanrychnovsky.xmlgenerator.schema.graph;

public class AttributeNode extends SchemaGraphNode {

    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
    
}
