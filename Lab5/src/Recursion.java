import java.util.Scanner;

/**
 * Recitation created by Gareth Halladay, 08/17. <br>
 * Content was gathered from two sources:
 * <ul>
 *     <li> http://www.cs.wustl.edu/~kjg/cse131/modules/recursion/lab.html
 *     <li> http://codingbat.com/prob/p273235?parent=/home/bono@usc.edu/recursionLab
 * </ul>
 *
 * Complete the 9 of the 10 methods.
 */
public class Recursion {

    /**
     * The product of an integer and all the integers below it.
     * @param n a natural number
     * @return n * (n-1) * (n-2) * ... * 1
     */
    public static int factorial(int n){
    	if(n==0)
    		return 1;
    	if (n==1) {
    		return 1;
    	}
    	
        return n * factorial(n-1);
    }

    /**
     * Every number after the first two is the sum of the two preceding ones. <br>
     * index:  0, 1, 2, 3, 4, 5, 6... <br>
     * output: 0, 1, 1, 2, 3, 5, 8...
     * @param n which fibonacci number to compute.
     * @return the fibonacci number.
     * 
     * fib(index) = fib(index -1) + fib(index -2); index >=2
     */
    public static int fib(int n){
    	
    	if (n <= 1) {
    		return n;
    	}
    	return fib(n-1) + fib(n-2);
    	
    }
    

    /**
     * Recursively sum the positive integers from n down to 0 decrementing by 2. <br>
     * sumDownBy2(7) is 7+5+3+1 = 16 <br>
     * sumDownBy2(8) is 8+6+4+2 = 20 <br>
     *
     * @param n a whole number.
     * @return the sum of the positive integers n + (n-2) + (n-4) + ...
     */
    public static int sumDownBy2(int n){
    	if (n <= 0) {
    		return 0;
    	}
    	if (n==1) {
    		return 1;
    	}
    	
        return n + sumDownBy2(n-2);
    }

    /**
     * Recursively return the sum of the harmonic series.
     * @param n a positive number.
     * @return the sum 1 + 1/2 + 1/3 + ... + 1/(n-1) + 1/n
     */
    public static double harmonicSum(int n){
    	if (n <= 0) {
    		return 0;
    	}
    	int max = n;
    	int start = 1;
    	return harmonicSumHelp(start, max);
        
    }
   
    public static double harmonicSumHelp(int n, int max){
    	
    	
        if (n == max) {
        	return (1.0/n);
        }
        
        return (1.0/n) + harmonicSumHelp(n+1, max);
   }


    /**
     * Recursively return the sum of the geometric series.
     * @param n a non-negative number.
     * @return the sum 1 + 1/2 + 1/4 + 1/8 + ... + 1/Math.pow(2,n)
     */
    public static double geometricSum(int n){
    	
    	if (n == 0) {
    		return 1;
    	}
    	return 1/Math.pow(2,n) + geometricSum(n-1);
        
    }

    

    /**
     * Write a multiplication method recursively using repeated addition. <br>
     * Do not use the multiplication operator or a loop.
     *
     * @param j a positive or negative integer.
     * @param k a positive or negative integer.
     * @return the product of the k and j.
     */
    public static int mult(int j, int k){
    	if (j < 0) {
    		return -1 * mult( j * -1, k);
    	}
    	if (k < 0) {
    		return -1 * mult( j , -1*k);
    	}
    	
         if (k == 0) {
        	 return 0;
         }
         return j + mult(j, k-1);
    }

    
    /**
     * Write a method that computes j^k. <br>
     * Do not use Math.pow() or a loop. <br>
     * @param j a non-negative number
     * @param k a non-negative number
     * @return j^k
     */
    public static int expt(int j, int k){
    	
    	if (k == 0) {
    		
       	 return 1;
        }
        return j * expt(j, k-1);
   }


    /**
     * Check to see if a word is a palindrome. Should be case-independent.
     * @param word a String without whitespace
     * @return true if the word is a palindrome
     */
    public static boolean isPalindrome(String word){
    	word = word.toLowerCase();
    	
         if (word.length() == 1 || word.length() == 0) {
        	 return true;
         }
         if (word.charAt(0) != word.charAt(word.length()-1)) {
			return false;
		}
         return isPalindrome(word.substring(1,word.length()-1));
    }

    /**
     * Returns length of the longest word in the given String using recursion (no loops).
     * Hint: a Scanner may be helpful for finding word boundaries. After delimiting by space,
     * use the following method on your String to remove punctuation {@code .replaceAll("[^a-zA-Z]", "")}
     * If you use a Scanner, you will need a helper method to do the recursion on the Scanner object.
     *
     * @param words A String containing one or more words.
     * @return The length of the longest word in the String.
     * @see Scanner#Scanner(String)
     * @see Scanner#next()
     * @see String#split(String)
     * @see String#replaceAll(String, String)
     * @see Math#max(int, int)
     */
    
    

    
   
    public static int longestWordLength(String words){
       Scanner scan = new Scanner(words);
       
       
       String[] wordsArr = words.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ",-1);
       
       return longestWordLengthHelp(wordsArr, 0, 0);
       
    }
    public static int longestWordLengthHelp(String[] words, int index, int max){
    	words[index].replaceAll("[^a-zA-Z]", "");
        if (index == words.length-1) {
			return max;
		}
        if (words[index].length() > max) {
			max = words[index].length();
			
        }
        
        return longestWordLengthHelp(words,index+1, max);
    }
    



    /**
     * Remove consecutive duplicate characters from a String. <br>
     * Case should not matter, if two or more consecutive duplicate <br>
     * characters have different cases, then the first letter should be kept.
     * @param word A word with possible consecutive duplicate characters.
     * @return A word without any consecutive duplicate characters.
     */
    
    
    
    
    public static String dedupeChars(String word){
    	//if(word.length == )
    	
        return  dedupeChar(dedupeChar(dedupeChar(word)));
        
    }
    
    
    public static String dedupeChar(String word){
    	
       return dedupeCharsHelp(word, "", 0);
        
    }
    
    
    
    
    public static String dedupeCharsHelp(String word, String result, int index){
    	if (word.length() == 1 && index == 0) {
    		return word;
    	}
    	if(index == word.length()-1) {
    		return result +  word.charAt(index);
    	}
    	
    	
    	if (word.length() == 1) {
    		return result;
    	}
    	if (word.length() == 0) {
    		return result;
    	}
    	
    	
    	if ( Character.toLowerCase((word.charAt(index))) == Character.toLowerCase(word.charAt(index+1))) {
    		
    		
    		StringBuilder sb = new StringBuilder(word);
    		sb.deleteCharAt(index+1);
    		//System.out.println("Popped char  " + word.charAt(index+1) + " from the word: " + word);
    		String newWord= sb.toString();
    		result += newWord.charAt(index);
    		//System.out.println("DEB 1st " + newWord + ". index is "+ index);
    		
    		return dedupeCharsHelp(newWord, result, index+1);
    	}else {
    		result += word.charAt(index);
    		//System.out.println("DEB 2nd " + word);
    		return dedupeCharsHelp(word, result, index+1);
    	}
    	
        
    }


    public static void main(String [] args){
        // Test your methods here!
        // Uncomment each block as you are ready to test it.

        
        System.out.println("Testing the factorial method");
        System.out.printf("factorial(4) should be 24 -> %d\n", factorial(4));
        System.out.printf("factorial(6) should be 720 -> %d\n", factorial(6));
        System.out.printf("factorial(0) should be 1 -> %d\n", factorial(0));
        System.out.println();

        System.out.println("Testing the fibonacci method");
        System.out.printf("fib(0) should be 0 -> %d\n", fib(0));
        System.out.printf("fib(1) should be 1 -> %d\n", fib(1));
        System.out.printf("fib(2) should be 1 -> %d\n", fib(2));
        System.out.printf("fib(5) should be 5 -> %d\n", fib(5));
        System.out.printf("fib(10) should be 55 -> %d\n", fib(10));
        System.out.printf("fib(13) should be 233 -> %d\n", fib(13));
        System.out.println();

        System.out.println("Testing out the sumDownBy2 method");
        System.out.printf("sumDownBy2(-1) should be 0 -> %d\n", sumDownBy2(-1));
        System.out.printf("sumDownBy2(0) should be 0 -> %d\n", sumDownBy2(0));
        System.out.printf("sumDownBy2(7) should be 16 -> %d\n", sumDownBy2(7));
        System.out.printf("sumDownBy2(8) should be 20 -> %d\n", sumDownBy2(8));
        System.out.printf("sumDownBy2(13) should be 49 -> %d\n", sumDownBy2(13));

        System.out.println();

        System.out.println("Testing out the harmonicSum method");
        System.out.printf("harmonicSum(0) should be 0.0000 -> %.4f\n", harmonicSum(0));
        System.out.printf("harmonicSum(7) should be 2.5929 -> %.4f\n", harmonicSum(7));
        System.out.printf("harmonicSum(8) should be 2.7179 -> %.4f\n", harmonicSum(8));
        System.out.printf("harmonicSum(13) should be 3.1801 -> %.4f\n", harmonicSum(13));
        System.out.printf("harmonicSum(24) should be 3.7760 -> %.4f\n", harmonicSum(24));
        System.out.println();

        System.out.println("Testing out the geometricSum method");
        System.out.printf("geometricSum(0) should be 1.0000 -> %.4f\n", geometricSum(0));
        System.out.printf("geometricSum(1) should be 1.5000 -> %.4f\n", geometricSum(1));
        System.out.printf("geometricSum(2) should be 1.7500 -> %.4f\n", geometricSum(2));
        System.out.printf("geometricSum(7) should be 1.9922 -> %.4f\n", geometricSum(7));
        System.out.printf("geometricSum(24) should be 2.0000 -> %.4f\n", geometricSum(24));
        System.out.println();

        System.out.println("Testing out the multiplication method");
        System.out.printf("mult(8, 2) should be 16 -> %d\n", mult(8, 2));
        System.out.printf("mult(2, 8) should be 16 -> %d\n", mult(2, 8));
        System.out.printf("mult(4, -3) should be -12 -> %d\n", mult(4, -3));
        System.out.printf("mult(-3, 4) should be -12 -> %d\n", mult(-3, 4));
        System.out.println();


        System.out.println("Testing out the exponent method");
        System.out.printf("expt(2, 5) should be 32 -> %d\n", expt(2, 5));
        System.out.printf("expt(5, 2) should be 25 -> %d\n", expt(5, 2));
        System.out.println();

        System.out.println("Testing out the palindrome method");
        System.out.printf("isPalindrome(\"a\") should be true -> %b\n", isPalindrome("a"));
        System.out.printf("isPalindrome(\"Aibohphobia\") should be true -> %b\n", isPalindrome("Aibohphobia"));
        System.out.printf("isPalindrome(\"noon\") should be true -> %b\n", isPalindrome("noon"));
        System.out.printf("isPalindrome(\"Recursion\") should be false -> %b\n", isPalindrome("Recursion"));
        System.out.println();

        System.out.println("Testing out the longestWordLength method\n");
        String quoteOne =
                "Grit, one of the keys to success. The person who perseveres is the one who\n" +
                        "will surely win. Success does not come from giving up, it comes from believing\n" +
                        "in yourself and continuously working towards the realization of a worthy ideal.\n" +
                        "Do not ever give up on what you want most. You know what you truly want.\n" +
                        "Believe in your dreams and goals and take daily consistent action in order to\n" +
                        "make your dreams a reality.";
        System.out.printf("The longest word in the following quote:\n%s\nshould be 12 -> %d\n\n", quoteOne, longestWordLength(quoteOne));
        String quoteTwo = "Try to be like the turtle – at ease in your own shell.";
        System.out.printf("The longest word in the following quote:\n%s\nshould be 6 -> %d\n\n", quoteTwo, longestWordLength(quoteTwo));
        System.out.println();

        System.out.println("Testing the dedupeChars method");
        System.out.printf("dedupeChars(\"a\") should be a -> %s\n", dedupeChars("a"));
        System.out.printf("dedupeChars(\"aa\") should be a -> %s\n", dedupeChars("aa"));
        System.out.printf("dedupeChars(\"MiSsisSiPpi\") should be MiSisiPi -> %s\n", dedupeChars("MiSsisSiPpi"));
        System.out.printf("dedupeChars(\"swimMmMming\") should be swiming -> %s\n", dedupeChars("swimMmMming"));
        
    }
}
