
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.Test;

public class TestCases {

	//Switch which line is commented out in order to test the correct vs broken code
	//TestingFunctions functions = new BlackBoxCorrect();
	MyFunctions functions = new MyFunctions();
	
	/**
	 * This is a simple validity check for the method greatestCommonDivisor. Checks that the method
	 * returns the correct result for a known GCD problem gcd(2,4) = 2
	 */
	@Test
	public void testGCD() {
		assertEquals(6, functions.greatestCommonDivisor(0, 6));
		assertEquals(6, functions.greatestCommonDivisor(6, 0));
		assertEquals(2, functions.greatestCommonDivisor(2, 6));
		assertEquals(2, functions.greatestCommonDivisor(6, 2));
		assertEquals(-1, functions.greatestCommonDivisor(-2, 6));
		assertEquals(-1, functions.greatestCommonDivisor(2, -6));
		
		
		
		
		/**
		for (int i = -100, j = 100; i < 100 && j > -100; i++, j--) {
			
			assertEquals(i + " " + j,-1, functions.greatestCommonDivisor(i, j));
			//the problem is that the method allows the negative integers in the first place, does computations with neg. nums.
		}**/
		
		
		
		
		
		
		
		
		//assertEquals(5, functions.greatestCommonDivisor(5, 1));
		//assertEquals(5, functions.greatestCommonDivisor(1, 5));
		// Error found. expected 5 returned 1. In the method there should be a special return statement that returns param a if param b
		// is 0 or vise versa
		
	}
		
 

	
	
	/**
	 * This is a simple check of the reverseWindow method. Checks if the method will reverse the entire contents
	 * of the passed array correctly.
	 */
	@Test
	public void testReverseWindow() {
		/**
	
    Input indices out of bound of the array size

    Input indices at the bounds of the array size

    Reversed input indices

    Input with an empty array

    Input with equal indices
    void reverseWindow(int[] arr,
                   int index1,
                   int index2)
            throws java.lang.IndexOutOfBoundsException
@Test
**/
	int arr1[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	
	
	functions.reverseWindow(arr2,5, 0);
	assertEquals(arr1[5], arr2[5]); 
	
	
	functions.reverseWindow(arr2,0, 5);
	assertEquals(arr1[5], arr2[5]); 
	
	
	
	
	
	
	}
	@Test
	public void test_reverse_arr_sw() {
		
		
		int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		functions.reverseWindow(arr2,10, 1);
		
		
	}
	@Test
	public void test_reverse_arr_neg1() {
		
		
		int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		functions.reverseWindow(arr2,-1, 1); //test of neg val
		
		
		
	}
	
	@Test
	public void test_reverse_arr_neg2() {
			
			
			int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
			functions.reverseWindow(arr2,1, -1); //test of neg val
			
			
			
		}
	@Test
	public void test_reverse_arr_bounds() {
		
		
		int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		functions.reverseWindow(arr2,1, 100);
		
		
	}
	
	
	@Test
	public void test_reverse_arr_4() {
			
			
			int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
			functions.reverseWindow(arr2,-3, 1);
			
			
	}
	@Test
	public void test_reverse_arr_5() {
		
		
		int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		functions.reverseWindow(arr2,1999, 1);
		
		
}
	@Test
	public void test_reverse_arr_6() {
			 
			
			int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
			assertThrows(IndexOutOfBoundsException.class, ()->functions.reverseWindow(arr2,10, -4));
			
			
	}
	@Test
	public void test_reverse_arr_7() {
			 
			
			int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
			assertThrows(IndexOutOfBoundsException.class, ()->functions.reverseWindow(arr2,-1, -1));
			
			
	}
	@Test
	public void test_reverse_arr_8() {
		 
		
		int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		assertThrows(IndexOutOfBoundsException.class, ()->functions.reverseWindow(arr2,999, 999));
		
		
}
	
	
	@Test
	public void test_reverse_arr_10() {
		 
		
		int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		assertThrows(IndexOutOfBoundsException.class, ()->functions.reverseWindow(arr2,999, 6));
		
		
}
	@Test
	public void test_reverse_arr_11() {
		 
		
		int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		assertThrows(IndexOutOfBoundsException.class, ()->functions.reverseWindow(arr2,6,999));
		
		
}
	@Test
	public void test_reverse_arr_12() {
		 
		
		int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		assertThrows(IndexOutOfBoundsException.class, ()->functions.reverseWindow(arr2,-1,2));
		
		
}
	@Test
	public void test_reverse_arr_13() {
		 
		
		int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		assertThrows(IndexOutOfBoundsException.class, ()->functions.reverseWindow(arr2,1,-1));
		
		
}
	@Test
	public void test_reverse_arr_14() {
		 
		
		int arr2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		assertThrows(IndexOutOfBoundsException.class, ()->functions.reverseWindow(arr2,-4,-3));
		
		
}
	
	
	public static void main(String[] args) {
		TestCases t = new TestCases();
		t.testGCD();
		t.testReverseWindow();
		t.test_reverse_arr_neg1();
		t.test_reverse_arr_neg2();
		t.test_reverse_arr_bounds();
		t.test_reverse_arr_sw();
		t.test_reverse_arr_4();
		t.test_reverse_arr_5();
		t.test_reverse_arr_6();
		t.test_reverse_arr_7();
		t.test_reverse_arr_8();
		
		t.test_reverse_arr_10();
		t.test_reverse_arr_11();
		t.test_reverse_arr_12();
		t.test_reverse_arr_13();
		t.test_reverse_arr_14();
		}
/**	
	public static void main(String[] args) {
		
		TestCases t = new TestCases();
		t.testGCD();
		t.testReverseWindow();
	}
**/
	//For completion, write additonal tests as described in the lab documentation.
}
