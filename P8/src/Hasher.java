// Hasher.java - code for hashing class
// Author: ?????
// Date:   ?????
// Class:  CS165
// Email:  ?????

@FunctionalInterface
interface HashFunction {
    int hash(String key);
}


public class Hasher {

    // Hashing algorithms, see specification

    /**
     * Hashing algorithms, see provided documentation in assignment
     * @param hashFunction FIRST, SUM, PRIME, OR JAVA
     * @return the corresponding HashFunction
     */
    public static HashFunction make(String hashFunction) {
        switch (hashFunction) {
        case "FIRST":
            return (String key) -> {
            	return Math.abs(key.charAt(0));
            };
            

        case "SUM":
        	return (String key) -> {
        		int sum = 0;
        		for (int r = 0; r < key.length(); r++) {
            	sum += Math.abs(key.charAt(r));
            	}
            	
        		
        		return sum;
            };

        case "PRIME":
        	return (String key) -> {
        		int ret = 0;
        		int code = 7;
        		int prev = code * 31 + Math.abs(key.charAt(0));
        		for (int r = 1; r < key.length(); r++) {
            	
            	ret = prev * 31 + Math.abs(key.charAt(r));
            	prev = ret;
            	
            	}
            	
        		
        		return prev;
            };

        case "JAVA":
        	return (String key) -> {
        		return key.hashCode();
        	};

        default:
            usage();
        }
        return null;
    }


    // Usage message
    private static void usage() {
        System.err.println("Usage: java Hasher <FIRST|SUM|PRIME|JAVA> <word>");
        System.exit(1);
    }



    // Test code for hasher
    public static void main(String[] args) {
        args = Debug.init(args);
        if (args.length != 2)
            usage();

        HashFunction sh = make(args[0]);
        int hashCode = sh != null ? sh.hash(args[1]) : 0;
        System.out.printf("'%s' hashes to %d using %s\n", args[1], hashCode, args[0]);
    }
}
