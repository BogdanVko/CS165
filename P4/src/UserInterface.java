// UserInterface.java - user interface for "Terriers and Squirrels"
// Author: Chris Wilcox
// With contributions from Ethan Lambert
// Date: 10/27/2016
// Course: CS163/CS164
// Email: wilcox@cs.colostate.edu

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class UserInterface extends JFrame {

	private static final long serialVersionUID = 1L;

	// User interface
	private JPanel topPanel; // Points
	private JPanel bottomPanel; // Board
	private Image squirrel, dog, fence, tree, munch, grass; // Images
	private JLabel message; // Label
	private JButton buttons[][]; // Buttons
	private Font font; // Font

	// Game related
	private char field[][];

	public UserInterface(int height, int width, char[][] field) {

		this.field = field;
		// Look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Setup message
		font = new Font("Arial", Font.PLAIN, 24);
		message = new JLabel("");
		message.setFont(font);
		message.setForeground(new Color(0xFFFFFF));

		// Top panel for message
		topPanel = new JPanel();
		topPanel.add(message);
		topPanel.setBackground(new Color(0x0076A3));

		// Bottom panel for board
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(height, width, 0, 0));
		add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.CENTER);

		// Split panel
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sp.setEnabled(false);
		sp.setDividerSize(0);
		sp.add(topPanel);
		sp.add(bottomPanel);
		add(sp, BorderLayout.CENTER);

		// Load images
		ImageIcon icon0 = new ImageIcon("resources/images/iconTerrier.png");
		Image image0 = icon0.getImage();
		dog = image0.getScaledInstance(480 / width, 480 / width, Image.SCALE_DEFAULT);

		ImageIcon icon1 = new ImageIcon("resources/images/iconSquirrel.png");
		Image image1 = icon1.getImage();
		squirrel = image1.getScaledInstance(480 / width, 480 / width, Image.SCALE_DEFAULT);

		ImageIcon icon2 = new ImageIcon("resources/images/iconMunch.png");
		Image image2 = icon2.getImage();
		munch = image2.getScaledInstance(480 / width, 480 / width, Image.SCALE_DEFAULT);

		ImageIcon icon3 = new ImageIcon("resources/images/iconFence.png");
		Image image3 = icon3.getImage();
		fence = image3.getScaledInstance(480 / width, 480 / width, Image.SCALE_DEFAULT);

		ImageIcon icon4 = new ImageIcon("resources/images/iconTree.png");
		Image image4 = icon4.getImage();
		tree = image4.getScaledInstance(480 / width, 480 / width, Image.SCALE_DEFAULT);

		ImageIcon icon5 = new ImageIcon("resources/images/iconGrass.png");
		Image image5 = icon5.getImage();
		grass = image5.getScaledInstance(480 / width, 480 / width, Image.SCALE_DEFAULT);

		// Build panel of buttons
		buttons = new JButton[height][width];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				// Initialize and add button
				JButton button = new JButton();
				buttons[row][col] = button;
				Border border = new LineBorder(Color.black, 1);
				button.setOpaque(true);
				button.setBackground(new Color(0x006600));
				button.setBorder(border);
				button.setName(row + "," + col);
				bottomPanel.add(button);
			}
		}
		redrawField(0);

		// Window setup
		setSize(540, 540); // fixed size
		setTitle("Terriers and Squirrels");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
	}

	// Redraw the field
	void redrawField(int counter) {

		// Update Generation
		message.setText("Terriers and Squirrels, Move: " + counter);

		// Redraw board
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				switch (field[i][j]) {
				case 'D':
					buttons[i][j].setIcon(new ImageIcon(dog));
					break;
				case 'S':
					buttons[i][j].setIcon(new ImageIcon(squirrel));
					break;
				case 'F':
					buttons[i][j].setIcon(new ImageIcon(fence));
					break;
				case 'T':
					buttons[i][j].setIcon(new ImageIcon(tree));
					break;
				case 'M':
					buttons[i][j].setIcon(new ImageIcon(munch));
					break;
				default:
					buttons[i][j].setIcon(new ImageIcon(grass));
				}
				buttons[i][j].repaint();
			}
		}
	}
}
