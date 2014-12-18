package cz.dusanrychnovsky.xmlgenerator;

import cz.dusanrychnovsky.xmlgenerator.schema.graph.SchemaGraph;

import java.io.File;
import java.io.IOException;

public abstract class SchemaParser {

	public abstract SchemaGraph parse(File schemaFile) throws IOException, ParseException;

}
