package cz.dusanrychnovsky.xmlgenerator.schema.graph.builder;

import cz.dusanrychnovsky.util.regexp.Expression;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.ContentType;
import lombok.Value;

@Value
public class ElementDeclaration {

	private final String elName;
	private final ContentType contentType;
	private final Expression expr;
}
