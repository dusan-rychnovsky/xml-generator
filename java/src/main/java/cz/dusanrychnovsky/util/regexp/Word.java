package cz.dusanrychnovsky.util.regexp;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private ArrayList<String> symbols;
	
	/**
	 * 
	 * @param symbols
	 */
	public Word(String... symbols) {
		this(asList(symbols));
	}
	
	/**
	 * 
	 * @param symbols
	 */
	public Word(List<String> symbols) {
		this.symbols = new ArrayList<String>(symbols);
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public Word concatWith(Word other) {

		List<String> symbols = new ArrayList<String>();
		symbols.addAll(this.symbols);
		symbols.addAll(other.symbols);
		
		return new Word(symbols);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getSymbols() {
		return unmodifiableList(symbols);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Word)) {
			return false;
		}
		
		Word other = (Word) obj;
		return symbols.equals(other.symbols);
	}
	
	@Override
	public int hashCode() {
		return symbols.hashCode();
	}
	
	@Override
	public String toString() {
		return "WORD" + symbols;
	}
}
