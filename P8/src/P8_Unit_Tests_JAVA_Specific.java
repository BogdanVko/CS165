import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class P8_Unit_Tests_JAVA_Specific {

	
	HashTable testHash;
	Iterator testIter;
	String HASHER = "JAVA";
	@BeforeEach
	void init() {
		testHash= new HashTable(5,Hasher.make(HASHER));
		testIter = null;
		
	}
	
	//Test to make sure the size(int) method runs as expected
	@Test
	void testSizeIndex_JAVA() {
		testHash.insert("Katy Perry");
		testHash.insert("Kimberly");
		testHash.insert("Emma Stone");
		assertEquals(1,testHash.size(2));
	}
	
	//Test to make sure the iterator next() runs as expected
	@Test
	void testIteratorNext() {
		testHash.insert("Paul");
		testHash.insert("Paula");
		testIter = testHash.iterator();
		
		assertEquals("Paul",testIter.next());
	}

}
