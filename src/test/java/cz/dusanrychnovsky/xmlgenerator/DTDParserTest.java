package cz.dusanrychnovsky.xmlgenerator;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class DTDParserTest
{
    @Test
    public void canParseASimpleDTD() throws IOException, ParseException {
    
    	SchemaParser parser = new DTDParser();
    	
    	File schemaFile = new TestResource("/newspaper.dtd").toFile();
    	assertEquals("SchemaTree", parser.parse(schemaFile).toString());
    }
}
