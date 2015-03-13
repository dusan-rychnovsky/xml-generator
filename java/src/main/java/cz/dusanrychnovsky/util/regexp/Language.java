package cz.dusanrychnovsky.util.regexp;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableSet;

import java.util.*;

public class Language {

	private final Set<Word> words;

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
	public Language(Collection<Word> words) {
		this.words = new HashSet<>(words);
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
	 * @param other
	 * @return
	 */
	public Language concatWith(Language other) {
		
		Set<Word> words = new HashSet<>();
		for (Word firstWord : this.words) {
			for (Word secondWord : other.words) {
				words.add(firstWord.concatWith(secondWord));
			}
		}
		
		return new Language(words);
	}

    /**
     *
     * @param languages
     * @return
     */
    public static Language union(Collection<Language> languages) {

        Set<Word> uniqueWords = new HashSet<>();
        for (Language language : languages) {
            for (Word word : language.words) {
                uniqueWords.add(word);
            }
        }

        return new Language(uniqueWords);
    }

	/**
	 * 
	 * @return
	 */
	public Set<Word> getWords() {
		return unmodifiableSet(words);
	}
	
	@Override
	public String toString() {
		return "LANG [" + words + "]";
	}
}
