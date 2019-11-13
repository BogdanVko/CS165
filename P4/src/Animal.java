// Animal.java - abstract class for "Terriers and Squirrels"
// Author: Chris Wilcox
// Date: 10/27/2016
// Course: CS163/CS164
// Email: wilcox@cs.colostate.edu

public abstract class Animal {

	// Enumeration for animal moves
	public enum eMove {
		NO_MOVE,
		LEFT,
		UP_LEFT,
		UP,
		UP_RIGHT,
		RIGHT,
		DOWN_RIGHT,
		DOWN,
		DOWN_LEFT
	}

	// Instance data for animal classes
	public int currentRow;
	public int currentCol;
	public int previousRow;
	public int previousCol;
	public int closestRow;
	public int closestCol;
	public char[][] field;

	// Constructor for animal classes
	public Animal(int initialRow, int initialCol, char[][] field){
		this.currentRow = initialRow;
		this.currentCol = initialCol;
		this.previousRow = -1;
		this.previousCol = -1;
		this.closestRow = -1;
		this.closestCol = -1;
		this.field = field;
	}

	// Getters for animal classes
	public int getCurrentRow(){ return currentRow; }
	public int getCurrentCol(){ return currentCol; }
	public int getPreviousRow(){ return previousRow; }
	public int getPreviousCol(){ return previousCol; }
	public int getClosestRow(){ return closestRow; }
	public int getClosestCol(){ return closestCol; }

	// ABSTRACT METHODS, must be implemented by terrier and squirrel

    /**
     * Figure out the next move for an animal
     * @return best next move depending on if the animal is squirrel or terrier
     */
	public abstract eMove findMove();

	// Is the move valid for the animal?

    /**
     * Is the best move a valid move.
     * @param row possible new move for row
     * @param col possible new move for col
     * @return true if the move is valid
     */
	public abstract boolean isValid(int row, int col);

	// IMPLEMENTED METHODS, shared behavior between terrier and squirrel

    /**
     * Find the closest other animal to current position.
     * The remainder of the method is a nested loop traversing
     * the field of play to find the closest animal of the
     * opposite type. Call computeDistance each
     * time from the inner loop. When you find the closest animal,
     * store its position in closestRow and closestCol.
     */
    public void findClosest(){

		double minimum = Double.MAX_VALUE;
		closestRow = -1;
		closestCol = -1;

		// Terriers look for closest squirrels, and vice versa
		char lookFor = ' ';
		if (this instanceof Terrier) lookFor = 'S';
		if (this instanceof Squirrel) lookFor = 'D';

		for(int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				char cs = field[i][j];
				if(cs == lookFor) {
					double dist = computeDistance(currentRow, currentCol, i, j);
					if (dist < minimum) {
						minimum = dist;
						closestRow = i;
						closestCol = j;
					}
				}
			}
		}
	}

    /**
     * Find, adjust, make the move for an animal
     */
	public void moveAnimal() {
		makeMove(adjustMove(findMove()));
	}

    /**
     * Adjust move to avoid obstacles
     * @param move figure out which move is best and valid
     * @return the best valid move MOVES
     */
	private eMove adjustMove(eMove move) {

		if (move == eMove.DOWN_LEFT)
			if (!isValid(currentRow+1,currentCol-1))
				move = eMove.LEFT;
		if (move == eMove.LEFT)
			if (!isValid(currentRow,currentCol-1))
				move = eMove.UP_LEFT;
		if (move == eMove.UP_LEFT)
			if (!isValid(currentRow-1,currentCol-1))
				move = eMove.UP;
		if (move == eMove.UP)
			if (!isValid(currentRow-1,currentCol))
				move = eMove.UP_RIGHT;
		if (move == eMove.UP_RIGHT)
			if (!isValid(currentRow-1,currentCol+1))
				move = eMove.RIGHT;
		if (move == eMove.RIGHT)
			if (!isValid(currentRow,currentCol+1))
				move = eMove.DOWN_RIGHT;
		if (move == eMove.DOWN_RIGHT)
			if (!isValid(currentRow+1,currentCol+1))
				move = eMove.DOWN;
		if (move == eMove.DOWN)
			if (!isValid(currentRow+1,currentCol))
				move = eMove.DOWN_LEFT;
		if (move == eMove.DOWN_LEFT)
			if (!isValid(currentRow+1,currentCol-1))
				move = eMove.NO_MOVE;
		return move;
	}


    /**
     * Move the animal.
     * Finish the makeMove method that moves the animal based on the move
     * returned from findMove and adjustMove. This is done by
     * incrementing, decrementing the currentRow and currentCol, based on
     * the parameter of type eMove passed in. Before changing the current
     * position, you must set the previousRow and previousCol to the current
     * position, to allow the UserInterface to erase the icon before moving
     * the animal.
     * @param move the direction the animal should move.
     */
	private void makeMove(eMove move) {
		switch (move) {
		case UP:
			previousRow = currentRow;
			previousCol = currentCol;
			currentRow--;
			break;
		case DOWN:
			previousRow = currentRow;
			previousCol = currentCol;
			currentRow++;
			break;

		case LEFT:
			previousRow = currentRow;
			previousCol = currentCol;
			currentCol--;
			break;
		case RIGHT:
			previousRow = currentRow;
			previousCol = currentCol;
			currentCol++;
			break;
		case DOWN_LEFT:
			previousRow = currentRow;
			previousCol = currentCol;
			currentCol--;
			currentRow++;
			break;
		case UP_LEFT:
			previousRow = currentRow;
			previousCol = currentCol;
			currentCol--;
			currentRow--;
			break;
		case UP_RIGHT:
			previousRow = currentRow;
			previousCol = currentCol;
			currentCol++;
			currentRow--;
			break;
		case DOWN_RIGHT:
			previousRow = currentRow;
			previousCol = currentCol;
			currentCol++;
			currentRow++;
			break;
		
		default:
			previousRow = currentRow;
			previousCol = currentCol;
			break;
		}
		
		
	}


	// UTILITY METHODS, just code to do stuff

	/**
	 * Compute the Euclidean distance between two animals. Find the formula online.
	 * @param row0 row of the first animal
	 * @param col0 col of the first animal
	 * @param row1 row of the second animal
	 * @param col1 col of the second animal
	 * @return the distance between the two animals.
	 */
	double computeDistance(int row0, int col0, int row1, int col1) {
		int x1 = row0;
		int x2 = row1;
		int y1 = col0;
		int y2 = col1;
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}

    /**
     * Figure out if move will stay on board
     * @param row
     * @param col
     * @return
     */
	public boolean stayOnBoard(int row, int col){

		// Stay on board?
		if (row < 0 || row >= field.length)
			return false;
		if (col < 0 || col >= field[0].length)
			return false;

		return true;
	}
}
