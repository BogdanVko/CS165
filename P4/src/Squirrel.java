

// Squirrel.java - class for Squirrel animals
// Author: Chris Wilcox
// Date: 10/27/2016
// Course: CS163/CS164
// Email: wilcox@cs.colostate.edu

public class Squirrel extends Animal {

	// Constructor
	public Squirrel(int initialRow, int initialCol, char[][] field) {
		super(initialRow, initialCol, field);
	}

    /**
     * Make squirrel flee from closest terrier.
     * use the location of the closest terrier (closestRow, closestCol),
     * and return a variable of type eMove that represents a move away
     * from the terrier. For example, if the closest terrier is left of
     * your location (currentRow, currentCol), you should return eMove.RIGHT,
     * and if the closest terrier is above and to the right of you, you
     * should return eMove.DOWN_LEFT, etc. The reasoning here is that squirrels
     * do not like terriers, for a perfectly good reason.
     * @return the move away from the closest terrier
     */
	public eMove findMove() {
		if (getClosestCol() < getCurrentCol() && getClosestRow() < getCurrentRow()) {
			return eMove.DOWN_RIGHT;
		}
		else if (getClosestCol() > getCurrentCol() && getClosestRow() < getCurrentRow()) {
			return eMove.DOWN_LEFT;
		}
		else if (getClosestCol() > getCurrentCol() && getClosestRow() > getCurrentRow()) {
			return eMove.UP_LEFT;
		}
		else if (getClosestCol() < getCurrentCol() && getClosestRow() > getCurrentRow()) {
			return eMove.UP_RIGHT;
		} 
		else if(getClosestCol() < getCurrentCol()) {
			return eMove.RIGHT;
		} else if (getClosestCol() > getCurrentCol()) {
			return eMove.LEFT;
		} else if (getClosestRow() < getCurrentRow()) {
			return eMove.DOWN;
		}else if (getClosestRow() > getCurrentRow()) {
			return eMove.UP;
		}else {
			return eMove.NO_MOVE;
		
		}
	}

    /**
     * Figure out if move is valid for squirrel
     * You should call the stayOnBoard method in Animal to figure out if
     * the move will stay on the playing field. If not, check if the move
     * will cause the squirrel to run into a terrier or another squirrel,
     * and if so return false. Otherwise return true. You must examine the
     * playing field to make this decision.
     * @param row possible new move for row
     * @param col possible new move for col
     * @return returns a boolean that is true if the squirrel can move, and false otherwise.
     */
	public boolean isValid(int row, int col){
		
			
		if(!stayOnBoard(row, col)) {
			return false;
		}
		if(field[row][col] == 'D' || field[row][col] == 'S') {
			return false;
		}
        return true;
	}

}
