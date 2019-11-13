/**
 * Created by garethhalladay 08/2017 for a recitation in CS165 at Colorado State University to increase
 * the understanding of Objects and OO concepts in Java. <br> <br>
 *
 * The KeyWord class consists of a word that describes a movie and the frequency of that word.
 * At this point, the class just accepts data parsed from a file but could be extended to increment the count of
 * frequency if the user wanted to collect new data.
 */
public class KeyWord {
    private String label;
    private int frequency;

    /**
     * Constructor for the KeyWord class. <br>
     * Assigns values to the member variables.
     * @param label a descriptive word
     * @param frequency the amount of times the word is used
     */
    public KeyWord(String label, int frequency) {
        this.label = label;
        this.frequency = frequency;
    }

    /**
     * Gets the {@link String} instance representing the keyword.
     * @return the word associated with the movie
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the {@link int} instance representing the frequency.
     * @return the number of occurrences of the word
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Returns a string representation for the KeyWord class. <br>
     * A string representation of this class includes the label but not the frequency. <br>
     * @return the label
     */
    @Override
    public String toString(){
        return String.format("%s", label, frequency);
    }

    public static void main(String[] args){
        System.out.println("The KeyWord class:");
        KeyWord one = new KeyWord("magical", 20);
        KeyWord two = new KeyWord("waste of time", 3);

        System.out.printf("%s, frequency: %d\n", one.getLabel(), two.getFrequency());
        System.out.println(one);
        System.out.println(two);

    }
}
