// HashInterface.java - interface for hashing assignment
// Author: Chris Wilcox/Fritz Sieker
// Date:   3/29/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

import java.util.Iterator;

public interface IHash {

    /** Add a key to the hash table, if it is not currently in the table
     *  @param key - the key to add
     *  @return true on success, false on failure (duplicate)
     */
    public abstract boolean insert(String key);

    /** Remove a key from the hash table
     *  @param key - the key to remove
     *  @return true on success, false on failure (not in table)
     */
    public abstract boolean remove(String key);

    /** Search for a key in the hash table
     *  @param key - the key to search for 
     *  @return the key on success, null on failure (not in table)
     */
    public abstract String search(String key);

    /** Get the number of elements in the hash table
     *  @return the number of elements in the table
     */
    public abstract int size();

    /** Get the number of elements in the hash table at the given index
     *  @param i the index in the hash table (0 to size-1)
     *  @return the size of the list in the i<sup>th</sup> bucket
     */
    public abstract int size(int i);

    /** Get an iterator that return the Strings stored in
     *  the hash table one at a time. The order should be in order of entries in
     *  the hash table itself and for a given bucket, the order in that list.
     *  @return an Interator
     */
    public abstract Iterator<String> iterator();

    /** Get an iterator for the i<sup>th</sup> bucket
     *  @param i the index in the hash table (0 to size-1)
     *  @return null if the bucket is empty, otherwise an iterator for the bucket
     */
    public abstract Iterator<String> iterator(int i);
    
    /** Print the entire hash table.
     */
    public abstract void print();
}
