/* "Copyright (c) 2012-2016 by Fritz Sieker."
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for any purpose, without fee, and without written
 * agreement is hereby granted, provided that the above copyright notice
 * and the following two paragraphs appear in all copies of this software,
 *
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE TO ANY PARTY FOR DIRECT,
 * INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
 * OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE AUTHOR
 * HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * THE AUTHOR SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS ON AN "AS IS"
 * BASIS, AND THE AUTHOR NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT,
 * UPDATES, ENHANCEMENTS, OR MODIFICATIONS."
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * This is a base class for several assignments you will write. Do <b>NOT</b>
 * change anything in this file. Its purpose it to executes commands, one
 * command per line. Commands may be entered via the keyboard or read from a
 * file. It contains a variey of utility routines to make it easy to enter for
 * {@code null} values and to enter arrays ({@code String/int}) as a
 * list of comma separated values.
 * <p>
 * 
 * @author Fritz Sieker
 */
public class Shell {

    /**
     * A class to help end processing that is only used internally by Shell.
     */
    private static class StopProcessing extends RuntimeException {
        private static final long serialVersionUID = 1L; // keep Eclipse happy

        public StopProcessing(String msg) {
            super(msg);
        }
    }

    /** Random number generator */
    private static Random random = null;

    /** Start of clock */
    private static long startNano;

    /** End of clock */
    private static long stopNano;

    /** Start the millisecond timer */
    public static void startClock() {
        startNano = System.nanoTime();
    }

    /**
     * Stop the millisecond timer
     * 
     * @return the elapsed time
     */
    public static long stopClock() {
        stopNano = System.nanoTime();
        return (stopNano - startNano);
    }

    /**
     * Get the shells random number generator. If one has not yet been created,
     * create one. To get repeatable results, use the {@code random}
     * command with a seed.
     */

    public static Random getRandom() {
        if (random == null)
            random = new Random();

        return random;
    }

    /** Display a brief help summary for commands implemented by this class */
    public void showHelp() {
        System.out.println("Shell commands:");
        System.out.println("  debug <int>");
        System.out.println("  echo <message>");
        System.out.println("  exit/quit");
        System.out.println("  help");
        System.out.println("  input <filename>");
        System.out.println("  random [seed]");
        System.out.println("  start");
        System.out.println("  stop");
        System.out.println();
    }

    /**
     * Filter input for some special cases.
     * 
     * @param input
     *            - the original string
     * @return The string is trimmed. If it is the String {@code "empty!"},
     *         return {@code ""}. If it is the String {@code "null!"},
     *         return {@code null}. Otherwise, return the trimmed String.
     *         Override this for more special conditions.
     */
    protected String filter(String input) {
        input = input.trim();

        if ("null!".equals(input))
            input = null;

        else if ("empty!".equals(input))
            input = "";

        return input;
    }

    /**
     * Retrieve a String from the scanner, filtering it for convenience. A
     * future enhancement will include quoting so that a "parameter" may have
     * embedded blanks, quotes, etc.
     * 
     * @param scanner
     *            - retrieve String from this Scanner.
     * @return the filtered String (possibly {@code null}).
     */
    public String getStringOrNull(Scanner scanner) {
        String result = null;
        if (scanner.hasNext()) {
            result = filter(scanner.next());
        }
        return result;
    }

    /**
     * Retrieve a String from the scanner using {@code nextLine()}, to get
     * everything else in the line and filter it for special values. This
     * differs from {@code Scaner.nextLine()} in that the result is trimmed
     * of leading and trailing blanks, and the special string
     * {@code null!</code is returned as {@code null} and
     * {@code empty!</code is returned as {@code ""}.
     * 
     * @param scanner
     *            - retrieve String from this Scanner.
     * @return the trimmed, filtered String (possibly {@code null}).
     */
    public String rest(Scanner scanner) {
        String result = null;
        if (scanner.hasNextLine()) {
            result = filter(scanner.nextLine());
        }
        return result;
    }

    /**
     * Get an array of {@code String} separated by commas from the scanner.
     * A future enhancement will allow quoting so that individual elements may
     * contain escaped commas.
     * 
     * @param scanner
     *            - source of the array
     * @return an array (possibly {@code null}, containing the filtered
     *         values.
     */
    public String[] getStrArray(Scanner scanner) {
        String[] result = null;
        String cslString = getStringOrNull(scanner);

        if (cslString != null) {
            if (cslString.length() == 0) {
                result = new String[0];
            } else {
                result = cslString.split(",");
                for (int i = 0; i < result.length; i++) {
                    result[i] = filter(result[i]);
                }
            }
        }

        return result;
    }

    /**
     * Get an array of {@code int} separated by commas from the scanner.
     * 
     * @param scanner
     *            - source of the array
     * @return an array (possibly {@code null}, containing the values
     */
    public int[] getIntArray(Scanner scanner) {
        return strArrayToIntArray(getStrArray(scanner));
    }

    /**
     * Convert an array of {@code String} to an array of {@code int}.
     * 
     * @param strResult
     *            - array of Strings to be converted to ints
     * @return Each String is converted to an int
     */
    public int[] strArrayToIntArray(String[] strResult) {
        int[] result = null;

        if (strResult != null) {
            result = new int[strResult.length];
            for (int i = 0; i < strResult.length; i++) {
                result[i] = Integer.parseInt(strResult[i]);
            }
        }

        return result;
    }

    /**
     * Process one command.
     * 
     * @param cmd
     *            - the command to process
     * @param params
     *            - a string containing the parameter(s) (if any)
     */
    public void processOneCommand(String cmd, String params) throws FileNotFoundException, NoSuchElementException {
        Scanner paramScanner = new Scanner(params);

        switch (cmd) {
        case "debug":
            Debug.debugLevel = paramScanner.nextInt();
            break;

        case "echo":
            System.out.println(params);
            break;

        case "exit":
        case "quit":
            paramScanner.close();
            throw new StopProcessing("exit command");

        case "help":
        case "?":
            showHelp();
            break;

        case "input":
            processCmdsFromFile(paramScanner.next());
            break;

        case "random":
            if (paramScanner.hasNextLong())
                random = new Random(paramScanner.nextLong());
            else
                random = new Random();
            break;

        case "start":
            startClock();
            break;

        case "stop":
            long elapsed = stopClock();
            System.out.println("elapsed time: " + elapsed);
            break;

        default:
            System.err.println("Unknown cmd: " + cmd + " " + params);
        }

        paramScanner.close();
    }

    /**
     * Command interpreter to test code. Process the commands where each command
     * is contained on a separate line. End-of-line comments begin with
     * {@code '#'}. Comments are removed and the resulting string trimmed.
     * Empty lines are ignored.
     * 
     * @param scanner
     *            - the source of the commands
     */
    public void processCommands(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            int pound = line.indexOf('#'); // end-of-line comments start with #
            if (pound != -1)
                line = line.substring(0, pound).trim(); // remove comment

            if (line.length() == 0)
                continue; // ignore blank lines

            Scanner cs = new Scanner(line);
            String cmd = cs.next().toLowerCase(); // make cmd case independent
            String params = (cs.hasNextLine() ? cs.nextLine().trim() : "");
            cs.close();

            try {
                processOneCommand(cmd, params);
            } catch (StopProcessing sp) {
                break;
            } catch (FileNotFoundException fnfe) {
                System.err.println(fnfe);
            } catch (NoSuchElementException nsee) {
                System.err.println("syntax error: " + line);
                if (Debug.debugLevel != 0)
                    nsee.printStackTrace();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    /**
     * Process commands from a file.
     * 
     * @param fileName
     *            - name of file containing commands
     */
    public void processCmdsFromFile(String fileName) {
        try {
            processCommands(new Scanner(new File(fileName)));
        } catch (FileNotFoundException fnfe) {
            System.err.println("can not input commands from file '" + fileName + "'");
            System.err.flush();
        }
    }

    /**
     * Provide a main() like method that can be inherited and/or overridden.
     * 
     * @param args
     *            - arguments to process. If {@code args} is
     *            {@code null} or of length 0, commands are taken from
     *            {@code System.in}. If {@code args.length} > 0, the
     *            {@code args} are assumed to be one or more file to be
     *            processed using the {@code input} command..
     */
    public void ooMain(String[] args) {
        args = Debug.init(args);

        if ((args == null) || (args.length == 0)) {
            System.out.println("Enter commands:");
            processCommands(new Scanner(System.in));
        } else
            for (String fileName : args) {
                processCmdsFromFile(fileName);
            }
    }

    /**
     * Entry point to shell.
     * 
     * @param args
     *            - an array of Strings
     */
    public static void main(String[] args) {
        Shell shell = new Shell();
        shell.ooMain(args);
    }
}
