///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WorldCloudGenerator.java
// File:             KeyWord.java
// Semester:         CS367 Spring 2016
//
// Author:           Junxiong Huang
// CS Login:         junxiong
// Lecturer's Name:  Amanda Strominger
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Kairat Satbekov
// Email:            satbekov@wisc.edu
// CS Login:         kairat
// Lecturer's Name:  Amanda Strominger
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * The dictionary created from the input file will store <tt> KeyWord
 * <tt> objects, each of which contains a word and a non-negative integer
 * representing the number of times the word occurs in the input file. For the
 * purposes of the <tt> KeyWord <tt> class, a word is a non-empty sequence of
 * characters in which all the letters have been converted to lower-case.
 * 
 * @author Junxiong Huang
 *
 */
public class KeyWord implements Comparable<KeyWord>, Prioritizable {

	/**
	 * A KeyWord consists of a word and an integer (representing the number of
	 * occurrences of the word). A word is a non-empty sequence of characters
	 * whose letters are all lower-case.
	 */
	private String word;
	private int numOccur;

	/**
	 * Constructs a KeyWord with the given word (converted to lower-case) and
	 * zero occurences. If the word is null or an empty string, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param word
	 */
	public KeyWord(String word) {
		if (word == null || word == "")
			throw new IllegalArgumentException();
		this.word = word.toLowerCase();
		numOccur = 0;
	}

	/**
	 * Return the word for this KeyWord.
	 * 
	 * @return - the word for this KeyWord
	 */
	public String getWord() {
		return this.word;
	}

	/**
	 * Return the number of occurrences for this KeyWord
	 * 
	 * @return - the number of occurrences for this KeyWord.
	 */
	public int getOccurrences() {
		return numOccur;
	}

	/**
	 * Adds one to the number of occurrences for this KeyWord
	 */
	public void increment() {
		numOccur = numOccur + 1;
	}

	/**
	 * Return the priority for the KeyWord. The priority of a KeyWord is the
	 * number of occurrences it has.
	 * 
	 * @return - the priority for this item
	 */
	public int getPriority() {
		return numOccur;
	}

	/**
	 * Compare the KeyWord with the one given. Two KeyWords are compared by
	 * comparing the word associated with the two KeyWords, ignoring case
	 * differences in the names.
	 * 
	 * @param other - the KeyWord with which to compare this KeyWord
	 */
	public int compareTo(KeyWord other) {
		return this.word.compareToIgnoreCase(other.getWord());
	}

	/**
	 * Compare this KeyWord to the specified object. The result is true if and
	 * only if the argument is not null and is a KeyWord object whose word is
	 * the same as the word of this KeyWord, ignoring case difference.
	 * 
	 * @param - other - the object with which to compare this KeyWord
	 */
	public boolean equals(Object other) {
		if (other instanceof KeyWord) {
			// Compare only the word string
			if (other != null && this.word.equalsIgnoreCase(((KeyWord) other).getWord())) {
				return true;
			}
		}
		return false;
	}
}
