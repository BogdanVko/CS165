import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * UserInterface.java - Graphics Code for drawing program
 * Author: Chris Wilcox
 * Date: 2/2/2017
 * Class: CS165
 * Email: wilcox@cs.colostate.edu
 */
public class UserInterface extends JFrame implements DrawInterface {
    // User interface variables
	private static final long serialVersionUID = 1L;
	private JPanel topPanel; // Text panel
	private JPanel bottomPanel; // Drawing panel
	private JLabel textLabel;
	private Font font = new Font("Serif", Font.PLAIN, 24);
	private Color topColor = new Color(0x23A34A);
	private Color bottomColor = new Color(0x6B8E23);
	private BufferedImage surface; // Drawing surface
	private Graphics2D gc; // Graphics context
	private Color colorLine = Color.WHITE;
	private Color colorFill = Color.WHITE;
	private Color colorText = Color.WHITE;

	private int gWidth; // Surface width
	private int gHeight; // Surface height
	private int numberPrimitives = 0; // Counts primitives

	/**
	 * noargs constructor for UserInterface
	 */
	public UserInterface() {

		// Platform customization
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setupTopPanel();    // Setup text area (top panel)
		setupBottomPanel(); // Setup drawing area (bottom panel)

		// Combine panels
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.CENTER);

		// Window setup
		setupWindow();
		
		// Initialize graphics
		initializeGraphics();
	}


	/**
	 * Sets up the top panel for status area
	 */
	private void setupTopPanel() {

		textLabel = new JLabel("Number Primitives: " + numberPrimitives);
		textLabel.setFont(font);
		textLabel.setForeground(new Color(0xFFFFFF));
		topPanel = new JPanel();
		topPanel.add(textLabel);
		topPanel.setBackground(topColor);
	}

    /**
     * Sets up the bottom panel for drawing primitives
     */
	@SuppressWarnings("serial")
	private void setupBottomPanel() {

		// Setup for rendering
		bottomPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(surface, 0, 0, null);
			}
		};
		bottomPanel.setBackground(bottomColor);
	}

	/*
	 * Sets up the window attributes
	 */
	private void setupWindow() {
		setSize(550, 650);
		setTitle("Drawing Application");
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Sets up the graphics context
	 */
	public void initializeGraphics() {

		gWidth = bottomPanel.getWidth();
		gHeight = bottomPanel.getHeight();
		surface = new BufferedImage(gWidth, gHeight, BufferedImage.TYPE_INT_RGB);
		gc = (Graphics2D) surface.getGraphics();
		gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gc.setBackground(bottomColor);
		gc.clearRect(0, 0, gWidth, gHeight);
	}

	/**
	 * Opens the window
	 */
	public void open() {
		setVisible(true);
	}

	/**
	 * Closes the window
	 */
	public void close() {
		setVisible(false);
		dispose();
	}

	/**
	 * Clears the screen
	 */
	public void clear() {
		gc.setBackground(bottomColor);
		gc.clearRect(0, 0, gWidth, gHeight);
	}

	/**
	 * Sets the line color
	 * @param color the line color for the shapes
	 */
	public void lineColor(int color) {
		colorLine = new Color(color);
	}

	/**
	 * Sets the fill color
	 * @param color the fill color for the shapes
	 */
	public void fillColor(int color) {
		colorFill = new Color(color);
	}

	/**
	 * Sets the text color
	 * @param color hexadecimal color stored as an int
	 */
	public void textColor(int color) {
		colorText = new Color(color);
	}

	/**
	 * Sets the font
	 * @param fontName
	 * @param fontSize
	 */
	public void setFont(String fontName, int fontSize) {
		gc.setFont(new Font(fontName, Font.BOLD, fontSize));
	}

	/**
	 * Draws text
	 * @param x location on the x-axis
	 * @param y location on the y-axis
	 * @param string the text to render
	 */
	public void drawText(int x, int y, String string) {
		gc.setColor(colorText);
		gc.drawChars(string.toCharArray(), 0, string.length(), x, y);
		update();
	}

	/**
	 * Draws a line
	 * @param x0 location on the x-axis for the first point
	 * @param y0 location on the y-axis for the first point
	 * @param x1 location on the x-axis for the second point
	 * @param y1 location on the y-axis for the second point
	 */
	public void drawLine(int x0, int y0, int x1, int y1) {
		gc.setColor(colorLine);
		gc.drawLine(x0, y0, x1, y1);
		update();
	}

	/**
	 * Draws a rectangle
	 * @param x location on the x-axis
	 * @param y location on the y-axis
	 * @param width width of the square or rectangle
	 * @param height height of the square or rectangle
	 * @param isFilled whether or not the shape is filled
	 */
	public void drawRectangle(int x, int y, int width, int height, boolean isFilled) {
		if (isFilled) {
			gc.setColor(colorFill);
			gc.fillRect(x, y, width, height);
		}
		else {
			gc.setColor(colorLine);
			gc.drawRect(x, y, width, height);
		}
		update();
	}

	/**
	 * Draws a polygon
	 * @param xPoints the x coordinates for the polygon
	 * @param yPoints the y coordinates for the polygon
	 * @param isFilled whether or not the polygon is filled
	 */
	public void drawPolygon(int[] xPoints, int[] yPoints, boolean isFilled) {
		if (isFilled) {
			gc.setColor(colorFill);
			gc.fillPolygon(xPoints, yPoints, xPoints.length);
		}
		else {
			gc.setColor(colorLine);
			gc.drawPolygon(xPoints, yPoints, xPoints.length);
		}
		update();
	}

	/**
	 * Draws an oval or circle
	 * @param x location on the x-axis
	 * @param y location on the y-axis
	 * @param width the width of the oval or circle
	 * @param height the height of the oval or circle
	 * @param isFilled whether or not the shape is filled
	 */
	public void drawOval(int x, int y, int width, int height, boolean isFilled) {
		if (isFilled) {
			gc.setColor(colorFill);
			gc.fillOval(x, y, width, height);
		}
		else {
			gc.setColor(colorLine);
			gc.drawOval(x, y, width, height);
		}
		update();
	}

    /**
     * Update number of primitives
     */
	private void update() {
		numberPrimitives++;
		textLabel.setText("Number Primitives: " + numberPrimitives);
		repaint();
	}
}
