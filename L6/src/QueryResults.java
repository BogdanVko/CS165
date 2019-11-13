import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryResults {
    // class variables go here
	private final List<String> dictionary;
	private List<String> list = new ArrayList<String>();
	private String word;
	private int count = 0;
	
    /**
     * Constructor for the QueryResults class. <br>
     * Initialize the dictionary and list objects
     * @param filename name of the dictionary file
     * @throws FileNotFoundException exception is thrown to the calling method if unable to open file
     */
	
    public QueryResults(String filename) throws FileNotFoundException {
        dictionary = readDictionary(filename);
        
        
        
    }


    /**
     * Getter method for the dictionary object.
     * @return {@link java.util.List} dictionary object
     */
    public List<String> getDictionary() {
        return dictionary;
        
    }

    /**
     * Getter method for the list object.
     * @return {@link java.util.List} of words found in {@link QueryResults#searchDictionary} method.
     */
    public List<String> getList() {
        return list;
    }

    /**
     * Get number of recursive calls.
     * @return the number of recursive calls used in {@code searchDictionary}.
     */
    public int getCount() {
        return count;
    }

    /**
     * Getter method for the current string being queried.
     * @return the word being queried
     */
    public String getWord(){
        return word;
    }

    /**
     * Create a new {@link java.util.List} and then read each token from
     * the file called {@code filename} and add it into the
     * {@link java.util.List}.
     *
     * @param filename name of file
     * @return A list of legal words to use for this recitation
     * @throws FileNotFoundException exception is thrown to the calling method if unable to open file.
     * @see Scanner#hasNext()
     * @see Scanner#next()
     */
    public List<String> readDictionary(String filename) throws FileNotFoundException{
    	List<String> dict = new ArrayList<String>();
    	Scanner s = new Scanner(new File(filename));
    	String word;
    	for(int i = 0; s.hasNextLine();i++){
     		word = s.nextLine();
     		dict.add(word);
     			 
     	}
    	s.close();
    	
    	return dict;
    }

    /**
     * Recursively search substrings of the parameter word
     * and store uniquely in the list.
     * <p>
     *
     * <ol>
     *     <li> Assign  word into the corresponding member variable
     *     <li> Set the member variable for count back to 0
     *     <li> Assign  list to the return value for searchDictionaryHelper(List, String)} method.
     *          Pass in a new ArrayList and the word as parameters.
     * </ol>
     *
     * @param word the word to query.
     */
    public void searchDictionary(String word) {
    	
    	searchDictionaryHelper(list, word);
    }
    
    	

    /**
     * Helper method for {@code searchDictionary}. <br>
     *
     * Your recursive logic goes here:
     *
     * <ol>
     *     <li> If the word is less than or equal to two characters, return  wordList.
     *     <li> Increment the counter.
     *     <li> Add the word if it is in the dictionary but not already in the list.
     *     <li> Recursively call the method twice: Once with the first character removed
     *          and once with the last character removed.
     * </ol>
     * Use {@link ArrayList#contains(Object)}.
     * @param wordList A List of unique legal substrings from the parameter, word
     * @param word The word being queried
     * @return A List of unique legal substrings from the parameter, word
     * @see ArrayList#contains(Object)
     */
    public List<String> searchDictionaryHelper(List<String> wordList, String word){
    	if (word.length() <= 2) {
    		
    		return wordList;
    	}
    	count++;
    	if (dictionary.contains(word) && !(list.contains(word))) {
    		wordList.add(word);
    	}
    	
    	String fLetterRm = (word.substring(1));
    	String lLetterRm = (word.substring(0, word.length()-1));
    	
    	searchDictionaryHelper(wordList, fLetterRm);
    	searchDictionaryHelper(wordList, lLetterRm);
    	
    	
        return wordList;
    }

    /**
     * toString for you QueryResults class.
     * @return String that prints information about the class
     */
    @Override
    public String toString() {
    	
    	/**Dictionary has 69901 words
    	Original string is "there"
    	String size equals 5
    	Method called 7 times
    	Contains:
    	there
    	here
    	ere
    	her
    	the**/
    	String ret = "";
    	ret += "Dictionary has " + dictionary.size() + " words\n"
    			+ "Original string is \"" + word + "\"\nString size equals to " + word.length() + 
    			"\nMethod called " + count + " times\n" + "Contains:\n";
    	for(int i = 0; i < list.size(); i++) {
    		ret += list.get(i) + "\n";
    		
    	}
    	
        // Remember to double check your formatting
        return ret;
    }

    public static void main(String [] args) throws FileNotFoundException {
        // Instantiate object
        QueryResults qr = new QueryResults(args[0]);
        //System.out.printf("Entry one: %s\nEntry two: %s\n", qr.getDictionary().get(0), qr.getDictionary().get(1));
        // Call searchDictionary method with a word
        qr.searchDictionary(args[1].toLowerCase());
        qr.word = args[1];

        // Print list of words found
        // you could also type: System.out.println(qr.toString());
        System.out.println(qr);
        System.out.println();

    }
}
