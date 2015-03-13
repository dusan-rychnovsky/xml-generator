package cz.dusanrychnovsky.util.regexp;

import lombok.Data;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Du�an Rychnovsk�
 *
 */
@Data
public class Word {

	private final List<String> symbols = new ArrayList<>();
	
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
        symbols.forEach(symbol -> this.symbols.add(symbol));
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public Word concatWith(Word other) {

		List<String> symbols = new ArrayList<>();
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
}
