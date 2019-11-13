// Triangle Assignment
// Author: Bogdan A Vasilchenko
//   Date: Sep 23, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class Triangle  extends Primitive{

	private int x0;
	private int y0;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	private boolean isFilled;
	int color;
	
	
	
	public Triangle(int x0, int y0, int x1, int y1, int x2, int y2) {
	this.x0 = x0;
	this.y0 = y0;
	this.x1 = x1;
	this.y1 = y1;
	this.x2 = x2;
	this.y2 = y2;
	
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
		// drawPolygon(int[] xPoints, int[] yPoints, boolean isFilled) 
		ui.fillColor(color);
		ui.lineColor(color);
		int[] x = {x0, x1, x2};
		int[] y = {y0, y1, y2};
		
		ui.drawPolygon(x, y, isFilled);
		
		
	}
}
