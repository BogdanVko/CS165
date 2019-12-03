import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestCode extends Shell {

    private static final int INSERT = 0;
    private static final int REMOVE = 1;
    private static final int SEARCH = 2;
    private static final String[] opNames = { "ADDED", "REMOVED", "FOUND" };

    private HashFunction hasher;
    private IHash table;
    private int tabSize;

    /** Display a brief help summary for commands implemented by this class */
    public void showHelp() {
        System.out.println("TestCode commands:");
        System.out.println("  hasher  <FIRST|SUM|PRIME|JAVA> - define hasher");
        System.out.println("  table   <size>     - create with size and hasher");
        System.out.println();
        System.out.println("  hash    <key>      - exercise hasher.hash()");
        System.out.println("  size    [index]    - num elements in table or [bucket]");
        System.out.println("  insert  <key>      - add key to table");
        System.out.println("  insertf <fileName> - add keys in file");
        System.out.println("  search  <key>      - find key in table");
        System.out.println("  searchf <fileName> - find keys in file");
        System.out.println("  remove  <key>      - remove key from table");
        System.out.println("  removef <fileName> - remove keys in file");
        System.out.println("  iterate [index]    - iterate table or [bucket]");
        System.out.println("  stats              - displays size of buckets");
        System.out.println("  print              - prints data structure");
        super.showHelp();
    }

    private boolean tableOp(int op, String key) {
        boolean result = false;
        switch (op) {
        case INSERT:
            result = table.insert(key);
            break;
        case REMOVE:
            result = table.remove(key);
            break;
        case SEARCH:
            result = (table.search(key) != null);
            break;
        }

        return result;
    }

    private void printBool(int op, String key) {
        boolean result = tableOp(op, key);
        System.out.printf("'%s' %s %s\n", key, (result ? "" : "NOT"), opNames[op]);
    }

    private String[] fromFile(String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(fileName));
        ArrayList<String> list = new ArrayList<String>(5000);

        while (reader.hasNextLine()) {
            String line = reader.nextLine().trim();

            if (!line.isEmpty())
                list.add(line);
        }
        reader.close();
        
        Object[] oa = list.toArray();
        return Arrays.copyOf(oa, oa.length, String[].class);
    }

    private void tableMultiOp(int op, String fileName) throws FileNotFoundException {

        int successes = 0;
        int failures = 0;
        String[] words = fromFile(fileName);

        Shell.startClock();
        for (String w : words) {
            Debug.printf("word: %s", w);
            if (tableOp(op, w))
                successes++;
            else
                failures++;
        }
        long elapsed = Shell.stopClock();

        System.out.printf("Op: %s numOps: %d successes: %d failures: %d\n", opNames[op], words.length, successes,
                failures);
        System.out.printf("Elapsed Time: %d\n", elapsed);
    }

    private void iterate(String prefix, Iterator<String> iter, String extra) {
        if (prefix != null)
            System.out.print(prefix);

        if (iter != null) {
            while (iter.hasNext())
                System.out.print(iter.next() + extra);
        }

        System.out.println();
    }

    private void printStats() {
        int counts[] = new int[table.size()];
        int maxSize = -1;

        for (int i = 0; i < tabSize; i++) {
            int bucketSize = table.size(i);
            maxSize = Math.max(maxSize, bucketSize);
            counts[bucketSize]++;
        }

        for (int i = 0; i <= maxSize; i++) {
            int cnt = counts[i];
            if (cnt > 0)
                System.out.printf("%d buckets with length %d\n", cnt, i);
        }
    }

    public void processOneCommand(String cmd, String params) throws FileNotFoundException, NoSuchElementException {
        String key;
        Scanner paramScanner = new Scanner(params);

        switch (cmd) {
        case "hash":
            key = paramScanner.next();
            int hc = hasher.hash(key);
            System.out.printf("'%s' hash: %d index: %d\n", key, hc, hc % tabSize);
            break;

        case "hasher":
            hasher = Hasher.make(paramScanner.next());
            System.out.println("OK");
            break;

        case "size":
            if (!paramScanner.hasNextInt())
                System.out.printf("NumElements: %d\n", table.size());
            else {
                int i = paramScanner.nextInt();
                System.out.printf("NumElements[%d]: %d\n", i, table.size(i));
            }
            break;

        case "table":
            tabSize = paramScanner.nextInt();
            table = new HashTable(tabSize, hasher);
            System.out.println("OK");
            break;

        case "insert":
            printBool(INSERT, paramScanner.nextLine());
            break;

        case "insertf":
            tableMultiOp(INSERT, paramScanner.next());
            break;

        case "search":
            printBool(SEARCH, paramScanner.nextLine());
            break;

        case "searchf":
            tableMultiOp(SEARCH, paramScanner.next());
            break;

        case "iterate":
            if (!paramScanner.hasNextInt())
                iterate(null, table.iterator(), "\n");
            else {
                int i = paramScanner.nextInt();
                iterate("bucket[" + i + "] ", table.iterator(i), ",");
            }
            break;

        case "remove":
            printBool(REMOVE, paramScanner.nextLine());
            break;

        case "removef":
            tableMultiOp(REMOVE, paramScanner.next());
            break;

        case "stats":
            printStats();
            break;

        case "print":
            table.print();
            break;

        default:
            super.processOneCommand(cmd, params);
        }

        paramScanner.close();

    }

    public static void main(String[] args) {
        TestCode p9 = new TestCode();
        p9.ooMain(args);
    }
}
