package cz.dusanrychnovsky.xmlgenerator.schema.graph.builder;

import cz.dusanrychnovsky.util.regexp.Expression;
import lombok.Data;

@Data
public class ElementDeclaration {

	private final String elName;
	private final Expression expr;
}
