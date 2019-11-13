// DrawPrimitives.java - Drawing program for graphical lab 
// Author: Chris Wilcox
// Date:   Jan 31, 2017
// Class:  CS165
// Email:  wilcox@cs.colostate,edu

import java.awt.Color;
import java.util.Random;

public class DrawPrimitives {

    // Random numbers
    private static Random random;

    public static void main(String[] args) throws InterruptedException {

        // Instantiate user interface
        UserInterface ui = new UserInterface();

        // Initialize graphics
        ui.initializeGraphics();

        // Get size
        int width = ui.graphicsWidth();
        int height = ui.graphicsHeight();

        // Random numbers
        random = new Random(1256540008);
        
        while (true) {

            // Loop
            int repeats = 2;
            while (repeats-- > 0) {
                // create boundaries for triangle dimensions
                int x = rand(height); int y = rand(width); int w = rand(100); int h = rand(100);
                // Draw some stuff
                Color color;
                color = new Color(rand(0x0066AA));
                ui.setColor(color);
                ui.drawCircle(rand(height), rand(width), rand(100), true);
                color = new Color(rand(0xffffff));
                ui.setColor(color);
                ui.drawRectangle(rand(height), rand(width), rand(100), rand(100), true);
                color = new Color(rand(0xffffff));
                ui.setColor(color);
                ui.drawText(rand(height),  rand(width),  "Hello!");
                color = new Color(rand(0xffffff));
                ui.setColor(color);
                ui.drawTriangle(x, y, x, y+h, x+w, y);

            }
            Thread.sleep(1000);
        }
    }

    /**
     * Random number in range
     * @param range range for the size or location of the shape.
     * @return a number for the size or location of the shape.
     */
    protected static int rand(int range) {
        return (int) (random.nextFloat() * range);
    }

}

