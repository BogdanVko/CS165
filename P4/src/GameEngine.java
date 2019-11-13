import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * GameEngine.java - game engine for "Terriers and Squirrels"
 * Author: Chris Wilcox
 * With contributions from Ethan Lambert
 * Date: 10/27/2016
 */
public class GameEngine{

    // Playing field
    private char[][] field;
    private int width;
    private int height;

    // Game variables
    private static int counter = 0;
    private boolean done = false;

    // Animal arrays
    private ArrayList<Terrier> dogs = new ArrayList<Terrier>();
    private ArrayList<Squirrel> squirrels = new ArrayList<Squirrel>();

    // Entry point
    public static void main(String[] args) {

        // Create game, based on specified file
        GameEngine game = new GameEngine();
        game.readFile(args[0]);
        game.height = game.field.length;
        game.width = game.field[0].length;

        // Initialize user interface
        UserInterface ui = new UserInterface(game.height, game.width, game.field);
 
        // Main loop
        while(!game.done){

            // Wait for awhile
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            // Advance game and redraw
            game.done = game.next();
            ui.redrawField(counter);
        }
    }

    // Advance game
    private boolean next(){

        // Move squirrels
        moveSquirrels();
        
        // Process terriers
        moveTerriers();

        // Limit iterations
        if (++counter > 30){
            System.err.println("Game stopped after 30 rounds!");
            return true;
        }

        // Any squirrels left?
        if (squirrels.size() == 0) {
            System.err.println("Oh my, all the squirrels are gone!");
            return true;
        }
        
        return false;
    }
    
    // Move squirrels
    private void moveSquirrels() {
        
        // Use iterator to allow dynamic removal
        for (Iterator<Squirrel> iterator = squirrels.iterator(); iterator.hasNext();) {
            
            // Get next squirrel
            Squirrel s = iterator.next();

            // Call squirrel to find closest terrier
            s.findClosest();

            // Call squirrel to move and get position
            s.moveAnimal();
            int currentRow = s.getCurrentRow();
            int currentCol = s.getCurrentCol();
            int previousRow = s.getPreviousRow();
            int previousCol = s.getPreviousCol();

            // Erase previous squirrel
            if ((previousRow != -1) && 
                (field[previousRow][previousCol] != 'F') &&
                (field[previousRow][previousCol] != 'D'))
                    field[previousRow][previousCol] = '-';

            // Check for invalid move
            if (currentRow < 0 || currentRow >= field.length ||
            	currentCol < 0 || currentCol >= field[0].length) {
                System.err.print("Squirrel[" + squirrels.indexOf(s) + "] exited the board at ");
                System.err.println("(" + currentRow + "," + currentCol + ")");
                System.exit(0);
            }
            
            if (field[currentRow][currentCol] == 'D')
                System.err.print("Squirrel[" + squirrels.indexOf(s) + "] moved into Terrier at ");
            else if (field[currentRow][currentCol] == 'S')
                System.err.print("Squirrel[" + squirrels.indexOf(s) + "] moved into Squirrel at ");
            else if (field[currentRow][currentCol] == 'T')
                System.err.print("Squirrel[" + squirrels.indexOf(s) + "] climbed tree at ");
            else if (field[currentRow][currentCol] == 'F')
                System.err.print("Squirrel[" + squirrels.indexOf(s) + "] under fence at ");
            else
                System.err.print("Squirrel[" + squirrels.indexOf(s) + "] moved to ");
            System.err.println("(" + currentRow + "," + currentCol + ")");

            // Special handling
            if (field[currentRow][currentCol] == 'T') {

                // Squirrel found tree
                iterator.remove();
                
            } else if (field[currentRow][currentCol] == 'F') {
                
                // Squirrel under fence

            } else {

                // Valid move
                field[currentRow][currentCol] = 'S';
            }
        }
    }

    // Move terriers
    private void moveTerriers() {
            
        // Without squirrels, don't bother
        if (squirrels.size() == 0)
            return;

        // Iterate terriers
        for (Terrier d : dogs) {

            // Call terrier to find closest squirrel
            d.findClosest();

            // Call terrier to move and get position
            d.moveAnimal();
            int currentRow = d.getCurrentRow();
            int currentCol = d.getCurrentCol();
            int previousRow = d.getPreviousRow();
            int previousCol = d.getPreviousCol();
            
            // Erase previous terrier
            if (previousRow != -1) field[previousRow][previousCol] = '-';

            // Check for invalid move
            if (currentRow < 0 || currentRow >= field.length ||
            	currentCol < 0 || currentCol >= field[0].length) {
                System.err.print("Terrier[" + dogs.indexOf(d) + "] exited the board at ");
                System.err.println("(" + currentRow + "," + currentCol + ")");
                System.exit(0);
            }
            
            if (field[currentRow][currentCol] == 'D')
                System.err.print("Terrier[" + dogs.indexOf(d) + "] moved into Terrier at ");
            else if (field[currentRow][currentCol] == 'F')
                System.err.print("Terrier[" + dogs.indexOf(d) + "] moved into Fence at ");
            else if (field[currentRow][currentCol] == 'T')
                System.err.print("Terrier[" + dogs.indexOf(d) + "] moved into Tree at");
            else if (currentRow < 0)
                System.err.print("Terrier[" + dogs.indexOf(d) + "] exited the top at ");
            else if (currentRow >= field.length)
                System.err.print("Terrier[" + dogs.indexOf(d) + "] exited the bottom at ");
            else if (currentCol < 0)
                System.err.print("Terrier[" + dogs.indexOf(d) + "] exited the left at ");
            else if (currentCol >= field[0].length)
                System.err.print("Terrier[" + dogs.indexOf(d) + "] exited the right at ");
            else
                System.err.print("Terrier[" + dogs.indexOf(d) + "] moved to ");

            // Print location
            System.err.println("(" + currentRow + "," + currentCol + ")");

            // Ate a squirrel?
            if (field[currentRow][currentCol] == 'S') {

                // Munched squirrel
                System.err.print("Terrier[" + dogs.indexOf(d) + "] ate Squirrel at ");
                System.err.println("(" + currentRow + "," + currentCol + ")");
                field[currentRow][currentCol] = 'M';
                removeSquirrel(currentRow, currentCol);

            } else {
                
                // Valid move
                field[currentRow][currentCol] = 'D';
            }
        }
    }
    
    // Remove a squirrel
    private void removeSquirrel(int row, int col) {
        
        Squirrel r = null;
        for (Squirrel s : squirrels){

            if (s.getCurrentRow() == row && s.getCurrentCol() == col)
                r = s;
        }
        squirrels.remove(r);
    }

    // Read maze
    private void readFile(String filename) {
        try {

            // Open file
            Scanner scan = new Scanner(new File(filename));

            // Read numbers
            height = scan.nextInt();
            width = scan.nextInt();

            // Allocate maze
            field = new char[height][width];

            // Read maze
            for (int row = 0; row < height; row++) {

                // Read line
                String line = scan.next();
                for (int col = 0; col < width; col++) {
                    
                    // Fill in character
                    field[row][col] = line.charAt(col);

                    // Make objects
                    if (field[row][col] == 'D') {
                        Terrier dog = new Terrier(row, col, field);
                        dogs.add(dog);
                    } else if (field[row][col] == 'S') {
                        Squirrel squirrel = new Squirrel(row, col, field);
                        squirrels.add(squirrel);
                    }
                }
            }
            scan.close();

        } catch (IOException e) {
            System.err.println("Cannot read maze: " + filename);
            System.exit(0);
        }
    }
}
