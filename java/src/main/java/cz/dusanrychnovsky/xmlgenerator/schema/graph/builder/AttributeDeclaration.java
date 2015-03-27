package cz.dusanrychnovsky.xmlgenerator.schema.graph.builder;

import lombok.Value;

@Value
public class AttributeDeclaration {
	
	private final String elName;
	private final String attrName;
	private final boolean required;
}
