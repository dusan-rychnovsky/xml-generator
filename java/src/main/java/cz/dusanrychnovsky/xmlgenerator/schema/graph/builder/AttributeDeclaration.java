package cz.dusanrychnovsky.xmlgenerator.schema.graph.builder;

import cz.dusanrychnovsky.xmlgenerator.schema.graph.ContentType;
import lombok.Value;

@Value
public class AttributeDeclaration {
	
	private final String elName;
	private final String attrName;
	private final ContentType contentType;
	private final boolean required;
}
