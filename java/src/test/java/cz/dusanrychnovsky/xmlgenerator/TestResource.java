package cz.dusanrychnovsky.xmlgenerator;

import java.io.File;

import org.apache.commons.io.FileUtils;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class TestResource {

	private final String path;
	
	/**
	 * 
	 * @param path
	 */
	public TestResource(String path) {
		this.path = path;
	}
	
	/**
	 * 
	 * @return
	 */
	public File toFile() {
		return FileUtils.toFile(getClass().getResource(path));
	}
}
