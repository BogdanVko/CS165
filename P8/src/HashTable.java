// HashTable.java - code for hashing assignment
// Author: ?????
// Date:   ?????
// Class:  CS165
// Email:  ?????

import java.util.*;

public class HashTable implements IHash {
    // Method of hashing
    private HashFunction hasher;
    // Hash table : an ArrayList of "buckets", which store the actual strings
    private ArrayList<List<String>> hashTable;
    /**
     * Number of Elements
     */
    private int numberOfElements;
    private int numberOfBuckets;

    /**
     * Initializes a new instance of HashTable.
     * <p>
     * Initialize the instance variables. <br>
     * Note: when initializing the hashTable, make sure to allocate each entry in the HashTable
     *       to a new a HashBucket or null, your choice.
     * @param numberOfBuckets   the size of the hashTable
     * @param hasher the type of hashing function
     */
    public HashTable(int numberOfBuckets, HashFunction hasher) {
       this.numberOfBuckets = numberOfBuckets;
       this.hasher = hasher;
       hashTable = new ArrayList<List<String>>();
        for (int i = 0; i < numberOfBuckets; i++) {
			hashTable.add(new ArrayList<String>());
		}
        
    }

    public boolean insert(String key) {
        if (hashTable.get(hasher.hash(key) % numberOfBuckets).contains(key)) {return false;}
       hashTable.get(hasher.hash(key) % numberOfBuckets).add(key);
       numberOfElements++;
       return true;
        
    }

    public boolean remove(String key) {
    	//if key is found
    	if(hashTable.get(hasher.hash(key) % numberOfBuckets).contains(key)) {
    		hashTable.get(hasher.hash(key) % numberOfBuckets).remove(key);
    		return true;
    		
    	}
        return false;
    }

    public String search(String key) {
    	if(hashTable.get(hasher.hash(key) % numberOfBuckets).contains(key)) {return key;}
        return null;
    }

    public int size() {
        return numberOfElements;
    }

    public int size(int index) {
        if (index < 0 || index > size()-1) {
        	//should I do that?
        	throw new IndexOutOfBoundsException();
        }
        return hashTable.get(index).size();
    }

    // Return iterator for the entire hashTable,
    // must iterate all hashBuckets that are not empty
    public class HashIterator implements Iterator<String> {
        // The current index into the hashTable 
        private int currentIndex;
        // The current iterator for that hashBucket
        private Iterator<String> currentIterator = null;

        HashIterator() {
            // YOUR CODE HERE
        }


        public String next() {
            // YOUR CODE HERE
            return null;
        }

        public boolean hasNext() {
            // YOUR CODE HERE
            return false;
        }
    }

    // Return an iterator for the hash table
    public Iterator<String> iterator() {
        // YOUR CODE HERE
        return null;
    }

    /**
     * Does not use the iterator above. Iterates over one bucket.
     *
     * @param index the index of bucket to iterate over
     * @return an iterator for that bucket
     */
    public Iterator<String> iterator(int index) {
        List<String> bucket = hashTable.get(index);
        return bucket != null ? bucket.iterator() : null;
    }

    // Prints entire hash table.
    // NOTE: This method is used extensively for testing.
    public void print() {
        Debug.printf("HashTable size: " + size());

        for (int index = 0; index < hashTable.size(); index++) {
            List<String> list = hashTable.get(index);
            if (list != null) {
                Debug.printf("HashTable[%d]: %d entries", index, list.size());
                for (String word : list) {
                    Debug.printf("String: %s (hash code %d)", word, hasher.hash(word));
                }
            }else {
                Debug.printf("HashTable[%d]: %d entries", index, 0);
            }
        }
    }
}
