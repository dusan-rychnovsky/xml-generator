package cz.dusanrychnovsky.util.xml;

import java.io.File;

import org.jdom2.Document;

public abstract class Validator {
	
	/**
	 * 
	 * @param doc
	 * @param schemaFile
	 * @return
	 */
	public abstract boolean isValid(Document doc, File schemaFile);
}
