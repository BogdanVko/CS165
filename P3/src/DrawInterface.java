// DrawInterface.java - Interface class for drawing program
// Author: Chris Wilcox
// Date: 2/2/2017
// Class: CS165
// Email: wilcox@cs.colostate.edu

public interface DrawInterface {
	
	//
	// Graphics control
	//

	/**
	 * Open window
	 */
	public abstract void open();

	/**
	 * Close window
	 */
	public abstract void close();

	/**
	 * Clear surface
	 */
	public abstract void clear();

	//
	// Graphics attributes
	//
	// Color selection
	public abstract void lineColor(int color);
	public abstract void fillColor(int color);
	public abstract void textColor(int color);

	/**
	 * Font selection
	 * @param fontName either Ariel or Times Roman
	 * @param fontSize size of the font
	 */
	public abstract void setFont(String fontName, int fontSize);
	
	//
	// Graphics primitives

	/**
	 * Draw text
	 * @param x the location on the x-axis
	 * @param y the location on the y-axis
	 * @param string the text that will be rendered
	 */
	public abstract void drawText(int x, int y, String string);

	/**
	 * Draw line
	 * @param x0 location on the x-axis for the first point
	 * @param y0 location on the y-axis for the first point
	 * @param x1 location on the x-axis for the second point
	 * @param y1 location on the y-axis for the second point
	 */
	public abstract void drawLine(int x0, int y0, int x1, int y1);

	/**
	 * Draw rectangle (and square)
	 * @param x location on the x-axis
	 * @param y location on the y-axis
	 * @param width the width of the rectangle
	 * @param height the height of the rectangle
	 * @param isFilled whether or not the rectangle is solid
	 */
	public abstract void drawRectangle(int x, int y, int width, int height, boolean isFilled);

	/**
	 * Draw polygon (and triangle)
	 * @param xPoints the coordinates for each point on the x-axis
	 * @param yPoints the coordinates for each point on the y-axis
	 * @param isFilled whether or not the polygon is solid
	 */
	public abstract void drawPolygon(int[] xPoints, int[] yPoints, boolean isFilled);

	/**
	 * Draw oval (and circle)
	 * @param x location on the x-axis
	 * @param y location on the y-axis
	 * @param width the width of the oval or circle
	 * @param height the height of the oval or circle
	 * @param isFilled whether or not the shape is solid
	 */
	public abstract void drawOval(int x, int y, int width, int height, boolean isFilled);
}
