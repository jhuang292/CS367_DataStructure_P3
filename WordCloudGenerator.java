
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
/////////////////////////////////////////////////////////////////////////////////
// Title:            Assignment3
// Files:            WorldCloudGenerator.java
// Semester:         CS367 Spring 2016
//
// Author:           Junxiong Huang
// Email:            jhuang292@wisc.edu
// CS Login:         junxiong
// Lecturer's Name:  Amanda Strominger
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Kairat Satbekov
// Email:            satbekov@wisc.edu
// CS Login:         kairat
// Lecturer's Name:  Amanda Strominger
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;
import java.io.*;

public class WordCloudGenerator {
	/**
	 * The main method generates a word cloud as described in the program
	 * write-up. You will need to add to the code given here.
	 * 
	 * @param args
	 *            the command-line arguments that determine where input and
	 *            output is done:
	 *            <ul>
	 *            <li>args[0] is the name of the input file</li>
	 *            <li>args[1] is the name of the output file</li>
	 *            <li>args[2] is the name of the file containing the words to
	 *            ignore when generating the word cloud</li>
	 *            <li>args[3] is the maximum number of words to include in the
	 *            word cloud</li>
	 *            </ul>
	 */
	public static void main(String[] args) {
		Scanner in = null; // for input from text file
		PrintStream out = null; // for output to html file
		Scanner inIgnore = null; // for input from ignore file
		DictionaryADT<KeyWord> dictionary = new BSTDictionary<KeyWord>();

		// Check the command-line arguments and set up the input and output

		// Check whether there are exactly four command-line arguments; if not,
		// display "Four arguments required: inputFileName outputFileName
		// ignoreFileName maxWords" and quit.
		if (args.length != 4) {
			System.out.println("Four arguments required: inputFileName " + "outputFileName ignoreFileName maxWords");
			System.exit(0);
		}

		// Check whether input and ignore files (given as command-line
		// arguments) exit and are readable; if not, display "Error: cannot
		// access file fileName" where fileName is the same of the appropriate
		// file and then quit.
		try {
			File inputFile = new File(args[0]);
			if (!(inputFile.exists() || inputFile.canRead())) {
				throw new FileNotFoundException();
			}
			in = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error: cannot access file " + args[0]);
			System.exit(0);
		}

		try {
			File ignoreFile = new File(args[2]);
			if (!(ignoreFile.exists() || ignoreFile.canRead())) {
				throw new FileNotFoundException();
			}
			inIgnore = new Scanner(ignoreFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error: cannot access file " + args[2]);
			System.exit(0);
		}

		// Check whether the maxWords command-line argument is a positive
		// integer; if not, display "Error: maxWords must be a positive integer"
		// and quit.
		try {
			int maxWords = Integer.parseInt(args[3]);
			if (maxWords <= 0) {
				throw new NumberFormatException();
			}

		} catch (NumberFormatException e) {

			System.out.println("Error: maxWords must be a positive integer");
			System.exit(0);
		}

		// Write the given output file
		try {
			File outFile = new File(args[1]);
			if (outFile.exists()) {
				System.out.println("Warning: file" + args[1] + " already exists, will be overwritten");
			}

			if (outFile.exists() && !outFile.canWrite()) {
				System.out.println("Error: cannot write to file " + args[1]);
				System.exit(1);
			}
			out = new PrintStream(outFile);

		} catch (FileNotFoundException e) {
			System.out.println("Error: cannot write to file " + args[1]);
			System.exit(1);
		}

		// Create the dictionary of words to ignore
		// You do not need to change this code.
		DictionaryADT<String> ignore = new BSTDictionary<String>();
		while (inIgnore.hasNext()) {
			try {

				ignore.insert(inIgnore.next());

			} catch (DuplicateException e) {
				// if there is a duplicate, we'll just ignore it
			}
		}

		// Process the input file line by line
		// Note: the code below just prints out the words contained in each
		// line. You will need to replace that code with code to generate
		// the dictionary of KeyWords.

		int numOfKeys = 0;
		while (in.hasNext()) {
			String line = in.nextLine();
			List<String> words = parseLine(line);

			KeyWord keyword = null;

			////////////////////////////////////////
			// REPLACE THE CODE BELOW WITH YOUR CODE

			for (String word : words) {
				if (ignore.lookup(word.toLowerCase()) == null) {
					try {
						keyword = new KeyWord(word.toLowerCase());

						dictionary.insert(keyword);
						dictionary.lookup(keyword).increment();
						numOfKeys++;

					} catch (DuplicateException e) {
						dictionary.lookup(keyword).increment();
					}
				}
			}
			////////////////////////////////////////

		} // end while

		// Print out information about the dictionary of key words
		System.out.println("# keys: " + numOfKeys);
		System.out.println("avg path length: " + dictionary.totalPathLength() / numOfKeys);
		System.out.println("linear avg path: " + (1 + numOfKeys) / 2);

		// Put the dictionary of key words into a priority queue.
		// Use the priority queue to create a list of the key words with the
		// most occurrences. The number of key words in the list is the maximum
		// words value passed in as a command-line argument (or the total number
		// of key words if there are fewer key words than the maximum number of
		// words). Since this list will be used to create the word cloud, and
		// the word cloud displays words in alphabetical order, the list should
		// be represented using a DictionaryADT<KeyWord>.
		PriorityQueueADT<KeyWord> heapWorld = new ArrayHeap<KeyWord>();

		for (KeyWord keyword : dictionary) {
			heapWorld.insert(keyword);
		}

		dictionary = new BSTDictionary<KeyWord>();
		for (int i = 0; i < Integer.parseInt(args[3]); i++) {
			if (!heapWorld.isEmpty()) {
				try {
					dictionary.insert(heapWorld.getMax());

					heapWorld.removeMax();
				} catch (DuplicateException e) {

				}
			} else {
				break;
			}
		}
		generateHtml(dictionary, out);

		////////////////////////////////////////////////////////////
		// ADD YOUR CODE HERE TO
		// - Print out the information about the dictionary:
		// - # of keys
		// - average path length
		// - linear average path length
		// - Put the dictionary into a priority queue
		// - Use the priority queue to create a list of KeyWords of
		// the appropriate length
		// - Generate the html output file
		////////////////////////////////////////////////////////////

		// Close everything
		if (in != null)
			in.close();
		if (inIgnore != null)
			inIgnore.close();
		if (out != null)
			out.close();
	}

	/**
	 * Parses the given line into an array of words.
	 * 
	 * @param line
	 *            a line of input to parse
	 * @return a list of words extracted from the line of input in the order
	 *         they appear in the line
	 * 
	 *         DO NOT CHANGE THIS METHOD.
	 */
	private static List<String> parseLine(String line) {
		String[] tokens = line.split("[ ]+");
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i < tokens.length; i++) { // for each word

			// find index of first digit/letter
			boolean done = false;
			int first = 0;
			String word = tokens[i];
			while (first < word.length() && !done) {
				if (Character.isDigit(word.charAt(first)) || Character.isLetter(word.charAt(first)))
					done = true;
				else
					first++;
			}

			// find index of last digit/letter
			int last = word.length() - 1;
			done = false;
			while (last > first && !done) {
				if (Character.isDigit(word.charAt(last)) || Character.isLetter(word.charAt(last)))
					done = true;
				else
					last--;
			}

			// trim from beginning and end of string so that is starts and
			// ends with a letter or digit
			word = word.substring(first, last + 1);

			// make sure there is at least one letter in the word
			done = false;
			first = 0;
			while (first < word.length() && !done)
				if (Character.isLetter(word.charAt(first)))
					done = true;
				else
					first++;
			if (done)
				words.add(word);
		}

		return words;
	}

	/**
	 * Generates the html file using the given list of words. The html file is
	 * printed to the provided PrintStream.
	 * 
	 * @param words
	 *            a list of KeyWords
	 * @param out
	 *            the PrintStream to print the html file to
	 * 
	 *            DO NOT CHANGE THIS METHOD
	 */
	private static void generateHtml(DictionaryADT<KeyWord> words, PrintStream out) {
		String[] colors = { "6F", "6A", "65", "60", "5F", "5A", "55", "50", "4F", "4A", "45", "40", "3F", "3A", "35",
				"30", "2F", "2A", "25", "20", "1F", "1A", "15", "10", "0F", "0A", "05", "00" };
		int initFontSize = 80;

		// Print the header information including the styles
		out.println("<head>\n<title>Word Cloud</title>");
		out.println("<style type=\"text/css\">");
		out.println("body { font-family: Arial }");

		// Each style is of the form:
		// .styleN {
		// font-size: X%;
		// color: #YYAA;
		// }
		// where N and X are integers and Y is two hexadecimal digits
		for (int i = 0; i < colors.length; i++)
			out.println(".style" + i + " {\n    font-size: " + (initFontSize + i * 20) + "%;\n    color: #" + colors[i]
					+ colors[i] + "AA;\n}");

		out.println("</style>\n</head>\n<body><p>");

		// Find the minimum and maximum values in the collection of words
		int min = Integer.MAX_VALUE, max = 0;
		for (KeyWord word : words) {
			int occur = word.getOccurrences();
			if (occur > max)
				max = occur;
			else if (occur < min)
				min = occur;
		}

		double slope = (colors.length - 1.0) / (max - min);

		for (KeyWord word : words) {
			out.print("<span class=\"style");

			// Determine the appropriate style for this value using
			// linear interpolation
			// y = slope *(x - min) (rounded to nearest integer)
			// where y = the style number
			// and x = number of occurrences
			int index = (int) Math.round(slope * (word.getOccurrences() - min));

			out.println(index + "\">" + word.getWord() + "</span>&nbsp;");
		}

		// Print the closing tags
		out.println("</p></body>\n</html>");
	}
}
