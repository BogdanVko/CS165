// TowersOfHanoi.java - solver program for Tower of Hanoi
// Author: ?????
// Date:   ?????
// Class:  CS165
// Email:  ?????

/**
 * ACKNOWLEDGEMENTS:
 * This assignment was heavily copied from the wonderfully competent faculty at Princeton University.
 * Permission to use the assignment was granted via email by Dr. Robert Sedgewick. We gratefully
 * acknowledge the material from Princeton University used in this assignment! The original Princeton
 * assignment is here: http://introcs.cs.princeton.edu/java/23recursion/AnimatedHanoi.java.html
 * Copyright &copy; 2000–2011, Robert Sedgewick and Kevin Wayne.
 */
public class TowersOfHanoi {

    /**
     * The user interface
     */
    private static UserInterface ui;
    /**
     * The left tower, maps to the 0
     */
    private static MyStack<Integer> left = new MyStack<>();
    /**
     * The center tower, maps to 1
     */
    private static MyStack<Integer> center = new MyStack<>();
    /**
     * The right tower, maps to 2
     */
    private static MyStack<Integer> right = new MyStack<>();
    /**
     * Stack used for the iterative solution
     */
    private static MyStack<Move> moves = new MyStack<Move>();

    /**
     * The Move class represents the action of moving one disc between pegs.
     * Of course, if that disc is covered by other discs, you'll first have
     * to move them off. And after the original disc is moved over, you'll
     * want to put the other discs back on top.
     * <p>
     * You will need the following fields:
     * <ol>
     *     <li> A field to hold the disc number
     *     <li> Three fields for the to/from/auxiliary pole numbers
     *     <li> A field to tell you whether the disc is covered by smaller discs
     * </ol>
     * <p>
     * The last field doesn't have a direct analog to the recursive implementation.
     * However, before the first recursive call the disc in question was covered by
     * a tower of smaller discs. After that recursive call returns, the disc
     * is uncovered and can be moved. Then the second recursive call moved the smaller
     * discs back on top of it.
     * <p>
     * In the recursive version, the runtime stack keeps track of where in the method to
     * jump back to when the recursive call returned, this effectively keeps a bit of state
     * about how far along the computation is.
     * <p>
     * In the iterative version, we have to store this information explicitly in the object:
     * is the disc covered (and will require work before it can be moved) or is it now
     * uncovered (and ready to be moved before finally moving the smaller discs back
     * on top).
     */
    public static class Move {
        // YOUR CODE HERE: variables, constructor, and toString
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.err.println("usage1: java TowersofHanoi depth <-recursive>");
            System.err.println("usage2: java TowersofHanoi depth <-iterative>");
            System.exit(-1);
        }
        
        // Parse tower depth
        int depth = Integer.parseInt(args[0]);

        // Parse options
        String option = args[1];

        // Setup initial state
        // your code here: push discs onto left pole in inverted order
        int count = depth;
        while (count >= 1) {
        	left.push(count);
        	count--;
        }

        // Create user interface
        ui = new UserInterface(depth);

        // Call user interface to render (initial)
        ui.draw(left, center, right);
        // Solve Towers of Hanoi
        if (option.equals("-recursive"))
            recursiveHanoi(depth, 0, 1, 2);
        else
            iterativeHanoi(depth, 0, 1, 2);

        // Wait for awhile
        Thread.sleep(100000);

        // Destroy user interface
        System.exit(0);
    }


    /**
     * The recursive solution for Towers of Hanoi. <br>
     * Implement the recursive solver for Towers of Hanoi, as follows:
     * <ol>
     *     <li> The base case for the recursion is to return if asked to move disc 0.
     *     <li> Make a recursive call to move the stack above the current disc from the source to the auxiliary pole.
     *          For example, if the current disk is 5, you would want to recurse with disk 4 and the appropriate
     *          arguments.
     *     <li> Now the discs above are out of the way, {@link TowersOfHanoi#move(int, int, int)}
     *          the requested disc to the destination pole.
     *     <li> Make a recursive call to move the stack on the auxiliary pole back to the destination pole.
     * </ol>
     * Verify that your solution works by running the program using the recursive option.
     * @param disc the disc you are moving to the destination (auxiliary) pole.
     * @param from the pole that the disk is currently on
     * @param aux the auxiliary pole (provides supplementary or additional help) but won't be used to move this disk
     *            at this point of time.
     * @param to the pole that the disk will move to
     */
    public static void recursiveHanoi(int disc, int from, int aux, int to) {
    	if (disc == 0) return;
    	//hanoi(int n, int from, int temp, int to, int[] pole) 
    	recursiveHanoi(disc-1, from, to, aux);
    	//move(int disc, int source, int dest) 
    	move(disc, from, to);
    	recursiveHanoi(disc-1, aux, from, to);
    } 
    

    /**
     * Iterative solution using stack. <br>
     * <ol>
     *     <li> Create a Move object using the arguments to iterativeHanoi() and push it onto
     *          the MyStack field named "moves". Initially, this bottom disc is covered of
     *          course. This represents the initial request to move the bottom disc.
     *     <li> Enter a loop that processes moves from the stack until the stack is empty.
     *          Within the loop:
     *          <ul>
     *              <li> pop a Move object from the top of the stack.
     *              <li> If the requested move is for disc 0, just continue the loop (no work is required).
     *              <li> If moving a currently covered disc:
     *                   <ol>
     *                       <li> Push the Move object back onto the stack after marking it uncovered.
     *                            By the time we see it again, it will be uncovered.
     *                       <li> Push a new Move object requesting that the disc covering it is moved
     *                            out of the way (onto the auxiliary). This is the analog of the first
     *                            recursive call in the recursive version.
     *                   </ol>
     *               <li> If moving an uncovered disc:
     *                   <ol>
     *                       <li> Perform the actual move of the disc by calling the move method
     *                            (since it is uncovered, it is free to be moved now).
     *                       <li> Push a new Move object requesting that the disc that was previously
     *                            moved off onto the auxiliary be moved from there back on top of the
     *                            just-moved disc. This is analogous to the second recursive call.
     *                   </ol>
     *               <li> If you continue until the stack is empty, this will solve the puzzle,
     *                    effectively tracing the same steps as the recursive version, modeling the
     *                    state of the algorithm with an explicit stack, rather than the runtime stack.
     *          </ul>
     * </ol>
     *
     * @param disc the current disc
     * @param from what pole the disc is on
     * @param aux the auxiliary pole
     * @param to what pole the disc is being moved to
     */
    public static void iterativeHanoi(int disc, int from, int aux, int to) {
        // your code here
    }

    /**
     * Method to report and move a disc.
     * @param disc the current disc
     * @param source the pole the disc is on
     * @param dest the pole the disc is being moved to
     */
    static void move(int disc, int source, int dest) {
        
        MyStack<Integer> fromStack, toStack;
        String fromName, toName;
        
        // Translate integer to stacks and names
        switch (source) {
            case 0: fromStack = left;   fromName = "left"; break;
            case 1: fromStack = center; fromName = "center"; break;
            default: fromStack = right; fromName = "right"; break;
        }
        switch (dest) {
            case 0: toStack = left;   toName = "left"; break;
            case 1: toStack = center; toName = "center"; break;
            default: toStack = right; toName = "right"; break;
        }

        // Print the move
        System.err.println("Moved disc " + disc + " from " + fromName + " to " + toName + ".");

        // Perform the move
        toStack.push(fromStack.pop());
        
        // Call user interface to render (updated)
        ui.draw(left, center, right);
    }
}
