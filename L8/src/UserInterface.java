

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.util.Random;
public class UserInterface extends JFrame implements DrawInterface
{
    // User interface variables
    private static final long serialVersionUID = 1L;
    private JPanel topPanel; // Text panel
    private JPanel middlePanel; // Drawing panel
    private JPanel bottomPanel; // Buttons panel
    private JLabel textLabel;
    private Font font = new Font("Serif", Font.PLAIN, 24);
    private Color topColor = new Color(0x0076A3);
    private Color middleColor = new Color(0x808080);
    private Color bottomColor = new Color(0x6B8E23);
    private JButton loadButton = new JButton("Load");
    private JButton saveButton = new JButton("Save");
    private JButton exitButton = new JButton("Exit");
    private JButton clearButton = new JButton("Clear");
    private BufferedImage surface;
    private Graphics2D gc; // Graphics context
    private int gWidth; // Surface width
    private int gHeight; // Surface height
    private int numberPrimitives = 0;
    String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    /**
     * User interface constructor
     */
    public UserInterface() {

        // Platform customization
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setupButtons(); // Setup user interface buttons
        setupTopPanel(); // Setup text area (top panel)
        setupMiddlePanel(); // Setup drawing area (middle panel)
        setupBottomPanel(); // Setup buttons area (bottom panel)

        // Combine panels
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Window setup
        setupWindow();
    }

    /**
     * Button handler
     * @param e called when one of the boxes is clicked. This includes
     *          load, save, and exit.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton)
            load();
        else if (e.getSource() == saveButton)
            save();
        else if (e.getSource() == exitButton)
            exit();
        else if (e.getSource() == clearButton)
            clear();
    }


    /**
     * Load file using the graphical interface
     */
    protected void load() {
        String filename = selectFile("Select file to load", true);
        System.out.println("Selected " + filename);
    }

    /**
     * save the program. Currently not functional.
     */
    protected void save() {
        String filename = selectFile("Select file to save", true);
        System.out.println("Selected " + filename);
    }


    /**
     * Exits program by hiding the window, destroying it, then stopping the application.
     */
    protected void exit() {

        // Dialog box
        textLabel = new JLabel("Exiting, goodbye!");
        repaint();
        JOptionPane.showMessageDialog(null,
                "Goodbye!",
                "Dialog Box",
                JOptionPane.WARNING_MESSAGE);
        setVisible(false); // hide window
        dispose(); // destroy window
        System.exit(0); // stop application
    }

    /**
     * Setup buttons for user interface
     */
    private void setupButtons() {

        loadButton.setToolTipText("Load file");
        saveButton.setToolTipText("Save file");
        exitButton.setToolTipText("Exit application");
        clearButton.setToolTipText("Clear the screen");
        loadButton.addActionListener(this);
        saveButton.addActionListener(this);
        exitButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    /**
     * Setup top panel for status area
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
     * Setup middle panel for puzzle area for rendering and set the background
     */
    private void setupMiddlePanel() {
        middlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(surface, 0, 0, null);
            }
        };
        middlePanel.setBackground(middleColor);

    }

    /**
     *  Setup bottom panel for buttons area
     */
    private void setupBottomPanel() {

        bottomPanel = new JPanel();
        bottomPanel.add(loadButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(exitButton);
        bottomPanel.add(clearButton);
        bottomPanel.setBackground(bottomColor);
    }

    /**
     * Setup window attributes
     * <ol>
     *     <li> Set window size to 550 x 650.
     *     <li> Set window title to "Drawing Application".
     *     <li> Make window resizable.
     * </ol>
     */
    protected void setupWindow() {
        // Your code here
    	
    	gWidth = 550;
    	gHeight = 650;
    	setSize(gWidth, 650);
    	setTitle("Drawings Application");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * File selector. <br>
     * Not functional.
     * @param title Set's the dialog title.
     * @param open true if opening dialog, false if saving.
     * @return the filename
     */
    private String selectFile(String title, boolean open) {

        String fileName = null;
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File("."));
        jfc.setDialogTitle(title);

        int result;
        if (open)
            result = jfc.showOpenDialog(this);
        else
            result = jfc.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            fileName = file.getAbsolutePath();
        }
        return fileName;
    }


    //
    // Interface methods
    //

    /**
     * Setup the graphics context
     * <ol>
     * 
  1  Store the width of the field middlePanel into the field "gWidth".
  2  Store the height of the field middlePanel into the field "gHeight".
  3  Set the background color to middleColor.
  4  Clear a rectangle equal to the surface width and height. 
     *     <li> Store the width of the field middlePanel into the field "gWidth".
     *     <li> Store the height of the field middlePanel into the field "gHeight".
     *     <li> Set the background color to middleColor.
     *     <li> Clear a rectangle equal to the surface width and height.
     * </ol>
     * @see Graphics2D#setFont(Font)
     * @see Font
     * @see Graphics2D#setBackground(Color)
     * @see Graphics2D#clearRect(int, int, int, int)
     */
    public void initializeGraphics() {

        // Your code here: steps 1 and 2
    	gWidth = middlePanel.getWidth();
    	gWidth = middlePanel.getHeight();
        surface = new BufferedImage(gWidth, gHeight, BufferedImage.TYPE_INT_RGB);

        gc = surface.createGraphics();
        gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // Your code here: step 4
        setBackground(middleColor);
        gc.clearRect(0, 0, gWidth, gHeight);
        // Your code here: step 5
    }

    public int graphicsWidth() { return gWidth; }
    public int graphicsHeight() { return gHeight; };

    
    //
    // Attribute methods

    /**
     * The color for the outline or fill. <br>
     * Use the graphics context to set the rendering color.
     * @param color the color of the shape.
     */
    void setColor(Color color) {
    	gc.setColor(color);
    }

    //
    // Draw primitives
    //

    public void drawCircle(int x0, int y0, int circleRadius, boolean isFilled) {
    	
    	
    	gc.drawOval(x0, y0, circleRadius*2, circleRadius*2);
    	
    	if (isFilled) {
    		gc.fillOval(x0, y0, circleRadius*2, circleRadius*2);
    	}
    	update();
    }


    public void drawRectangle(int x0, int y0, int w, int h, boolean isFilled) {
    	gc.drawRect(x0, y0, w, h);
    	if (isFilled)
    		gc.fillRect(x0, y0, w, h);
    	update();
    }

    public void drawTriangle(int x0, int y0, int x1, int y1, int x2, int y2) {
    	int[] x = {x0,x1,x2};
    	int[] y = {y0,y1,y2};
    	
    		gc.drawPolygon(x, y, 3);
    		update();
    }
    /**Draws text.

    Create a new Font with the following arguments:
        A random font from the instance called fonts. To select and random legal font,
         call the rand method in the DrawPrimitives class with the length of the Font array.
        Call DrawPrimitives.rand(int) with 0, 1, or 2. These numbers represent plain, bold, 
        or italic.
        Call DrawPrimitives.rand(int) with 35. This will return a random number for the size of the font. 
    Use the graphics context to draw a text string.
    Call the update method. 

Specified by:
    drawText in interface DrawInterface
Parameters:
    x0 - the x coordinate of the location where the String should be rendered.
    y0 - the y coordinate of the location where the String should be rendered.
    text - the text to be rendered. **/
    public void drawText(int x0, int y0, String text) {
 
    	Random rand = new Random();
    	int randomInt = rand.nextInt(2);
    	int randomSize = rand.nextInt(35);
    	Font f = new Font(fonts[randomInt], randomInt, randomSize);
    	gc.setFont(f);
    	gc.drawString(text, x0, y0);
    	
    	update();

    }


    public void clear() {
    	gc.clearRect(0, 0, gWidth, gHeight);
    	numberPrimitives = 0;
    }

    /**
     * Updates the surface
     * <ol>
     *     <li> Increment the number of primitives.
     *     <li> Set the textLabel instance using the following message: "Number Primitives: " + numberPrimitives.
     *     <li> Call the repaint method on super.
     * </ol>
     * @see JLabel#setText(String)
     * @see super#repaint()
     */
    void update() {
    	numberPrimitives++;
    	textLabel.setText("Number Primitives: " + numberPrimitives);
    	super.repaint();
    }
}
