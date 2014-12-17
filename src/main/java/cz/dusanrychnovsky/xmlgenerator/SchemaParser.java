package cz.dusanrychnovsky.xmlgenerator;

import java.io.File;
import java.io.IOException;

public abstract class SchemaParser {

	public abstract SchemaTree parse(File schemaFile) throws IOException;

}
