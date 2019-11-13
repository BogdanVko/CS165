// UserInterface.java - Interface class for graphical lab 
// Author: Chris Wilcox
// Date: 10/15/2016
// Class: CS165
// Email: wilcox@cs.colostate.edu

import java.awt.event.ActionListener;

public interface DrawInterface extends ActionListener
{
    //
    // Graphics methods
    //


    void initializeGraphics();

    /**
     * Returns drawing surface width.
     * @return returns the width
     */
    int graphicsWidth();

    /**
     * Returns drawing surface height.
     * @return returns the height
     */
    int graphicsHeight();

    /**
     * Draws a circle. <br>
     * <ol>
     *     <li> Use the graphics context to draw a circle (filled or outline).
     *     <li> Call the update method.
     * </ol>
     * @param x0 the x coordinate of the upper left corner of the oval to be drawn.
     * @param y0 the y coordinate of the upper left corner of the oval to be drawn.
     * @param circleRadius the radius of the circle.
     * @param isFilled true if the oval is solid, false if it's the outline.
     */
    void drawCircle(int x0, int y0, int circleRadius, boolean isFilled);

    /**
     * Draws a rectangle. <br>
     * <ol>
     *     <li> Use the graphics context to draw a rectangle (filled or outline)
     *     <li> Call the update method.
     * </ol>
     * @param x0 the x coordinate of the rectangle to be drawn.
     * @param y0 the y coordinate of the rectangle to be drawn.
     * @param w the width of the rectangle to be drawn.
     * @param h the height of the rectangle to be drawn.
     * @param isFilled true if the rectangle is solid, false if it's the outline.
     */
    void drawRectangle(int x0, int y0, int w, int h, boolean isFilled);

    /**
     * Draws a triangle. <br>
     * <ol>
     *     <li> Use the graphics context to draw a triangle (outline).
     *     <li> Call the update method.
     * </ol>
     * @param x0 the x coordinate of the location where the first angle should be rendered.
     * @param y0 the y coordinate of the location where the first angle should be rendered.
     * @param x1 the x coordinate of the location where the second angle should be rendered.
     * @param y1 the y coordinate of the location where the second angle should be rendered.
     * @param x2 the x coordinate of the location where the third angle should be rendered.
     * @param y2 the y coordinate of the location where the third angle should be rendered.
     */
    void drawTriangle(int x0, int y0, int x1, int y1, int x2, int y2);

    /**
     * Draws text. <br>
     * <ol>
     *     <li> Create a new Font with the following arguments:
     *         <ul>
     *             <li> A random font from the instance called fonts. To select and random legal font, call the rand method
     *                  in the DrawPrimitives class with the length of the Font array.
     *             <li> Call {@link DrawPrimitives#rand(int)} with 0, 1, or 2. These numbers represent plain, bold, or
     *                  italic.
     *             <li> Call {@link DrawPrimitives#rand(int)} with 35. This will return a random number for the size of
     *                  the font.
     *         </ul>
     *     <li> Use the graphics context to draw a text string.
     *     <li> Call the update method.
     * </ol>
     * @param x0 the x coordinate of the location where the String should be rendered.
     * @param y0 the y coordinate of the location where the String should be rendered.
     * @param text the text to be rendered.
     */
    void drawText(int x0, int y0, String text);

    /**
     * Clears the screen. <br>
     * Use the graphics context to clear screen and set the field numberPrimitives back to 0.
     */
    void clear();
}
