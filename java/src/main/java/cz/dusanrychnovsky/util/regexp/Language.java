package cz.dusanrychnovsky.util.regexp;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Language {

	private final List<Word> words;
	
	/**
	 * 
	 * @param languages
	 * @return
	 */
	public static Language union(List<Language> languages) {
		
		Set<Word> uniqueWords = new HashSet<>();
		for (Language language : languages) {
			for (Word word : language.words) {
				uniqueWords.add(word);
			}
		}
		
		return new Language(new ArrayList<>(uniqueWords));
	}
	
	/**
	 * 
	 * @param languages
	 * @return
	 */
	public static Language concat(List<Language> languages) {
		
		Language acc = new Language(new Word());
		for (Language language : languages) {
			acc = acc.concatWith(language);
		}
		
		return acc;
	}
	
	/**
	 * 
	 * @param words
	 */
	public Language(Word... words) {
		this(asList(words));
	}
	
	/**
	 * 
	 * @param words
	 */
	public Language(List<Word> words) {
		this.words = new ArrayList<>(words);
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public Language concatWith(Language other) {
		
		List<Word> words = new ArrayList<Word>();
		for (Word firstWord : this.words) {
			for (Word secondWord : other.words) {
				words.add(firstWord.concatWith(secondWord));
			}
		}
		
		return new Language(words);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Word> getWords() {
		return unmodifiableList(words);
	}
	
	@Override
	public String toString() {
		return "LANG" + words;
	}
}
