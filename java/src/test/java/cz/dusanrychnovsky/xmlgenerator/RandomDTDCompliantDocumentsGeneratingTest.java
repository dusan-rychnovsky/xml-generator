package cz.dusanrychnovsky.xmlgenerator;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;

import static org.jdom2.output.Format.getPrettyFormat;
import static org.junit.Assert.assertTrue;

import org.jdom2.output.XMLOutputter;
import org.junit.Test;

import cz.dusanrychnovsky.util.xml.DTDValidator;
import cz.dusanrychnovsky.util.xml.Validator;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.RandomGenerator;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.SchemaGraph;


public class RandomDTDCompliantDocumentsGeneratingTest
{
	private static final String DTD_DIR_PATH = "/schemas/dtd";
	private static final int TRIES_PER_SCHEMA = 100;
	
	private final SchemaParser parser = new DTDParser();
	private final Validator validator = new DTDValidator();
	
	// private final XMLOutputter outputter = new XMLOutputter(getPrettyFormat());
	
    @Test
    public void canGenerateCompliantDocuments() throws IOException, ParseException {
    
    	for (File schemaFile : getFiles(DTD_DIR_PATH)) {
    		
    		SchemaGraph graph = parser.parse(schemaFile);
    		RandomGenerator generator = new RandomGenerator(graph);
    		
    		for (int i = 0; i < TRIES_PER_SCHEMA; i++) {
    			
    			Document doc = generator.next();
    			assertTrue(validator.isValid(doc, schemaFile));
    		}
    	}
    }

	/**
     * 
     * @param dirPath
     * @return
     */
	private List<File> getFiles(String dirPath) {
		
		File dir = new TestResource(dirPath).toFile();
		return asList(dir.listFiles());
	}
    
}
