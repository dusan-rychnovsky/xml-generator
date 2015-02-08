package cz.dusanrychnovsky.util.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import org.jdom2.output.XMLOutputter;

public class DTDValidator extends Validator {
	
	private SAXBuilder builder = new SAXBuilder(XMLReaders.DTDVALIDATING);
	private XMLOutputter outputter = new XMLOutputter();
	
	@Override
	public boolean isValid(Document doc, File schemaFile) {
		
		String rootElName = doc.getRootElement().getName();
		
		// TODO: we don't want to modify the original doc here
		DocType docType = new DocType(rootElName, "file:////" + schemaFile.getPath());
		doc.setDocType(docType);
		
		String docVal = outputter.outputString(doc);
		InputStream docIn = IOUtils.toInputStream(docVal);
				
		try {
			builder.build(docIn);
		}
		catch (JDOMException | IOException ex) {
			return false;
		}
		
		return true;
	}
}
