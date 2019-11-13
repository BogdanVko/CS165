// MyFunctions Assignment
// Author: Bogdan A Vasilchenko
//   Date: Sep 10, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class MyFunctions implements TestingFunctions{

	@Override
	public int greatestCommonDivisor(int a, int b) {
		
		if(a < 0 || b < 0)
			return -1;
		if(a == 0 || b == 0)
			return a+b; // base case	
		
		return greatestCommonDivisor(b,a%b);
		
	}

	
	
	
    
    public void reverseWindow(final int[] array, int i, int j) throws IndexOutOfBoundsException {
    	if (i < 0 || j < 0 ) {
        	
            throw new IndexOutOfBoundsException(String.format("The passed indexes are not valid (%d,%d)", i, j));
        }
        if (i > j) {
            final int n = i;
            i = j;
            j = n;
        }
        
        if (i - 1 >= array.length || j - 1 >= array.length ) {
                	
            throw new IndexOutOfBoundsException(String.format("The passed indexes are not valid (%d,%d)", i, j));
         }	
        
        
        revWinRec(array, i, j - 1);
    }
    
    private static void revWinRec(final int[] array, final int n, final int n2) {
        if (n == n2) {
            return;
        }
        
        final int n3 = array[n];
        array[n] = array[n2];
        array[n2] = n3;
        revWinRec(array, n + 1, n2 - 1);
    }
	
	

}
