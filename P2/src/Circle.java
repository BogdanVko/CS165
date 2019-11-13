// Circle Assignment
// Author: Bogdan A Vasilchenko
//   Date: Sep 23, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class Circle extends Primitive{
	
	private int x;
	private int y;
	private int rad;
	private boolean isFilled;
	int color;
	
	
	
	public Circle(int x, int y, int radius) {
	this.x = x;
	this.y = y;
	this.rad = radius;
	isFilled = false;
	color = 0;
	
	}
	
	public void setFilled(boolean isF) {
		
		if (isF) {
			this.isFilled = true;
		}
		else {
			this.isFilled = false;
		}
	}
	
	public void setColor(int color) {
		this.color = color;
		
	}
	public void draw(UserInterface ui) {
		ui.fillColor(color);
		ui.lineColor(color);
		ui.drawOval(x, y, rad, rad, isFilled);
		
		
	}

}
