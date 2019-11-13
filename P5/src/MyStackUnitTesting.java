import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// P5Test Assignment
// Author: Jordan Peterson
// Date:   Mar 4, 2019
// Class:  CS165
// Email:  jordantp@colostate.edu

class MyStackUnitTesting {

	Stack<String> javaStack;
	MyStack<String> myStack;
	
	@BeforeEach
	void initArray() {
		// Make a Java stack
        javaStack = new Stack<>();
        // Make a student stack
        myStack = new MyStack<>();
	}
	
	//Test if the toString is implemented as the AbstractCollection<E> toString method
	// * In this test we have created a string for what the toString() should return like when 
	//  the integers 1, 3, and 6 are pushed onto the stack.
	@Test
	void testToString() {
		String expected = "[1, 3, 6]";
		myStack.push(Integer.toString(1));
		myStack.push(Integer.toString(3));
		myStack.push(Integer.toString(6));
		assertEquals(expected,myStack.toString(),"toString should look like [1, 3, 6]");

	}
	
	
	//Test if pushing to the stack has been implemented correctly
	// * In this test we are checking to see if the toString() that you have written
	//   produces the same output as the java native stack
	@Test
	void testPush() {
		javaStack.push(Integer.toString(5));
		myStack.push(Integer.toString(5));
		assertEquals(javaStack.toString(),myStack.toString(),"myStack should look just like the javaStack");
	}
	
	
	//Test if popping from the stack has been implemented correctly
	// * In this test we are pushing integers 5 and 8 to the stack.
	//   We then check to see if the pop() functionality matches that
	//   of the java built in stack implementation
	@Test
	void testPop() {
		javaStack.push(Integer.toString(5));
		javaStack.push(Integer.toString(8));
		myStack.push(Integer.toString(5));
		myStack.push(Integer.toString(8));
		String expected1 = javaStack.pop();
		assertEquals(expected1, myStack.pop(),"Popped string should be 8");
		String expected2 = javaStack.pop();
		assertEquals(expected2, myStack.pop(),"Popped string should be 5");
	}
	
	//Test if peeking at the top of the stack has been implemented correctly
	// * This test is similar to the testPop() test, but with this one we
	//   are checking to see if the peek() functionality matches java's
	@Test
	void testPeek() {
		javaStack.push(Integer.toString(5));
		javaStack.push(Integer.toString(8));
		myStack.push(Integer.toString(5));
		myStack.push(Integer.toString(8));
		assertEquals(javaStack.peek(), myStack.peek(),"Popped string should be 8");
		javaStack.pop();
		myStack.pop();
		assertEquals(javaStack.peek(), myStack.peek(),"Popped string should be 5");
	}
	
	
	//Test if Size method correctly keeps track of the size.
	@Test
	void testSize() {
		myStack.push(Integer.toString(5));
		myStack.push(Integer.toString(8));
		assertEquals(myStack.size(), 2, " should be 2");
	}
	
	
	//Test if the clear method is implemented correctly.
	@Test
	void testClear() {
		myStack.push(Integer.toString(5));
		myStack.push(Integer.toString(8));
		myStack.clear();
		assertEquals(myStack.size(), 0, " should be 0");
	}	

	//Test if the isEmpty method returns the correct boolean.
	@Test
	void testIsEmptyTrue() {
		myStack.push(Integer.toString(5));
		myStack.push(Integer.toString(8));
		myStack.clear();
		assertEquals(myStack.isEmpty(), true, " should be true");
	}

	@Test
	void testIsEmptyFalse() {
		myStack.push(Integer.toString(5));
		myStack.push(Integer.toString(8));
		
		assertEquals(myStack.isEmpty(), false, " should be false");
	}
	
	//Test if get correctly returns the element requested.
	@Test
	void testGet() {
		myStack.push(Integer.toString(5));
		myStack.push(Integer.toString(8));
		
		assertEquals(myStack.get(0), Integer.toString(5), " should be 5");
		
	}
	
	//Test if the last index of an element is returned correctly.
	// * In this test we are making sure the strings are pushed onto
	//   the stack correctly. We check if lastIndexOf() returns the position
	//   of the most recent push of test1
	@Test
	void testLastIndexOf() {
		myStack.push("test1");
		myStack.push("test2");
		myStack.push("test1");
		assertEquals(2, myStack.lastIndexOf("test1"), "The last index of 'test1' should be 2");
	}
	
	//Test if the index of method works correctly.
	// * In this test we are checking if IndexOf()
	//   will return the position of test2 which
	//   should be 1 (the second element)
	@Test
	void testIndexOf() {
		myStack.push("test1");
		myStack.push("test2");
		assertEquals(1, myStack.indexOf("test2"),"test2 has an index of 1");
	}
	
	//Test if you can search for an element correctly.
	// * In this test we check to see if the search functionality
	//   return the elements correct distance away from the top of the stack.
	//   In a stack of 2 elements, the first element will have a distance of 2
	//   and the second elements will have a distance of 1
	@Test
	void testSearchFound() {
		myStack.push("test1");
		myStack.push("test2");
		assertEquals(1, myStack.search("test2"),"test2 has a distance of 1 on the stack");
		assertEquals(2, myStack.search("test1"),"test1 has a distance of 2 on the stack");
	}
	
	@Test
	void testSearchNotFound() {
		myStack.push("test1");
		myStack.push("test2");
		assertEquals(-1, myStack.search("test3"),"test3 should not exist on the stack");
	}
	
	//Test if the contains method is implemented correctly
	@Test
	void testContainsTrue() {
		myStack.push("test1");
		myStack.push("test2");
		assertEquals(myStack.contains("test1"), true, "should be true");
	}
	

	@Test
	void testContainsFalse() {
		myStack.push("test1");
		myStack.push("test2");
		assertEquals(myStack.contains("test144"), false, "should be false");
	}
}

