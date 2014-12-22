package cz.dusanrychnovsky.xmlgenerator;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cz.dusanrychnovsky.xmlgenerator.schema.graph.SchemaGraph;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.SerializeGraph;


public class DTDParserTest
{
    @Test
    public void canParseASimpleDTD() throws IOException, ParseException {
    
    	SchemaParser parser = new DTDParser();
    	
    	File schemaFile = new TestResource("/newspaper.dtd").toFile();
    	SchemaGraph graph = parser.parse(schemaFile);
    	
    	System.out.println(graph.accept(new SerializeGraph()));
    }
}
