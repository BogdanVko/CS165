import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;

class P8_Unit_Testing_HashTable {

	
	// For each test we are starting with a new Hashtable 
	// In order to test your code with different hash functions,
	// change the HASHER variable to one of the following:
	//
	// FIRST
	// SUM
	// PRIME
	// JAVA
	
	HashTable testHash;
	Iterator testIter;
	String HASHER = "FIRST";
	@BeforeEach
	void init() {
		testHash= new HashTable(5,Hasher.make(HASHER));
		testIter = null;
		
	}
	
	
	//EXPECTED SUCCESSES
	//==================
	
	//Test to make sure the insert method runs as expected
	@Test
	void testInsert() {
		assertTrue(testHash.insert("micheal bay"),"Insertion should return true on successful insert");
	}
	
	
	//Test to make sure the remove method runs as expected
	@Test
	void testRemove() {
		testHash.insert("micheal bay");
		assertTrue(testHash.remove("micheal bay"),"Deletion should return true on successful remove");
	}
	
	//Test to make sure the search method runs as expected
	@Test
	void testSearch() {
		testHash.insert("michael bay");
		testHash.insert("michael jackson");
		testHash.insert("Mr. T");
		assertEquals("Mr. T",testHash.search("Mr. T"));
	}
	
	//Test to make sure the size() method runs as expected
	@Test
	void testSize() {
		testHash.insert("1234");
		testHash.insert("5678");
		testHash.insert("1337");
		assertEquals(3,testHash.size());
	}
	
	
	//Test to make sure the iterator next() runs as expected
	@Test
	void testIteratorNext() {
		testHash.insert("Paul");
		testHash.insert("Paula");
		testIter = testHash.iterator();
		
		assertEquals("Paul",testIter.next());
	}
	
	//Test to make sure the iterator hasNext() runs as expected
	@Test
	void testIteratorHasNext() {
		testHash.insert("Paul");
		testHash.insert("Paula");
		testIter = testHash.iterator();
		
		assertTrue(testIter.hasNext());
	}
	
	//EXPECTED FAILURES
	//=================
	
	
	//Test to make sure the insert method runs as expected
	@Test
	void testInsert_FALSE() {
		testHash.insert("m");
		assertFalse(testHash.insert("m"),"Insertion should return false on duplicate insert");
	}
	
	
	//Test to make sure the remove method runs as expected
	@Test
	void testRemove_FALSE() {
		assertFalse(testHash.remove("micheal bay"),"Deletion should return false on non-existant key");
	}
	
	//Test to make sure the search method runs as expected
	@Test
	void testSearch_FALSE() {
		testHash.insert("michael bay");
		testHash.insert("michael jackson");
		testHash.insert("Mr. T");
		assertEquals(null,testHash.search("Mr."));
	}
	
	
	//Test to make sure the size(int) method runs as expected
	@Test
	void testSizeIndex_FALSE() {
		assertEquals(0,testHash.size(0));
	}
	
	
	//Test to make sure the iterator next() runs as expected
	@Test
	void testIteratorNext_FALSE() {
		testIter = testHash.iterator();
		
		assertThrows(IllegalStateException.class,()->{testIter.next();});
	}
	
	//Test to make sure the iterator hasNext() runs as expected
	@Test
	void testIteratorHasNext_FALSE() {
		testIter = testHash.iterator();
		
		assertFalse(testIter.hasNext());
	}
}
