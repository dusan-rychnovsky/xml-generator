package cz.dusanrychnovsky.xmlgenerator;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.jdom2.Document;
import static org.jdom2.output.Format.getPrettyFormat;
import org.jdom2.output.XMLOutputter;
import org.junit.Test;

import cz.dusanrychnovsky.xmlgenerator.schema.graph.RandomGenerator;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.SchemaGraph;


public class DTDParserTest
{
	private final XMLOutputter outputter = new XMLOutputter(getPrettyFormat());
	
    @Test
    public void canParseASimpleDTD() throws IOException, ParseException {
    
    	SchemaParser parser = new DTDParser();
    	
    	File schemaFile = new TestResource("/newspaper.dtd").toFile();
    	SchemaGraph graph = parser.parse(schemaFile);
    	
    	Iterator<Document> it = new RandomGenerator(graph);
    	System.out.println(outputter.outputString(it.next()));
    }
}
