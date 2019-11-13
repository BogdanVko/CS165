

// Terrier.java - class for Terrier animals
// Author: Chris Wilcox
// Date: 10/27/2016
// Course: CS163/CS164
// Email: wilcox@cs.colostate.edu

public class Terrier extends Animal {

    // Constructor
    public Terrier(int initialRow, int initialCol, char[][] field) {
    		super(initialRow, initialCol, field);
    }

    //

    /**
     * Make terrier chase closest squirrel
     * Use the location of the closest squirrel (closestRow, closestCol),
     * and return a variable of type eMove that represents a move towards
     * the squirrel. For example, if the closest squirrel is left of your
     * location (currentRow, currentCol), you should return eMove.LEFT,
     * and if the closest squirrel is above and to the right, you should
     * return eMove.UP_RIGHT, etc. The reasoning here is that terriers like
     * squirrels, for all the wrong reasons! You should be able to leverage
     * this method from Squirrel.java by reversing all the moves.
     * @return the next move the Terrier should make
     */
    public eMove findMove() {
    	if (getClosestCol() < getCurrentCol() && getClosestRow() < getCurrentRow()) {
			return eMove.UP_LEFT;
		}
		else if (getClosestCol() > getCurrentCol() && getClosestRow() < getCurrentRow()) {
			return eMove.UP_RIGHT;
		}
		else if (getClosestCol() > getCurrentCol() && getClosestRow() > getCurrentRow()) {
			return eMove.DOWN_RIGHT;
		}
		else if (getClosestCol() < getCurrentCol() && getClosestRow() > getCurrentRow()) {
			return eMove.DOWN_LEFT;
		} 
		else if(getClosestCol() < getCurrentCol()) {
			return eMove.LEFT;
		} else if (getClosestCol() > getCurrentCol()) {
			return eMove.RIGHT;
		} else if (getClosestRow() < getCurrentRow()) {
			return eMove.UP;
		}else if (getClosestRow() > getCurrentRow()) {
			return eMove.DOWN;
		}else {
		
		return eMove.NO_MOVE;
		
		}
	}

    /**
     * Figure out if move is valid for terrier
     * You should call the stayOnBoard method in Animal to figure
     * out if the move will stay on the playing field. If not, check
     * if the move will cause the terrier to run into another terrier,
     * fence, or tree, and if so return false. Otherwise return true.
     * You must examine the playing field to make this decision.
     * You should be able to leverage this method from Squirrel.java,
     * then change what you're looking for on the playing field.
     * @param row possible new move for row
     * @param col possible new move for col
     * @return true if the terrier can move, and false otherwise
     */
    public boolean isValid(int row, int col){
    	if(!stayOnBoard(row, col)) {
			return false;
		}
		
		if(field[row][col] != '-' && field[row][col] != 'S') {
			return false;
		}
		return true;
    }
}
