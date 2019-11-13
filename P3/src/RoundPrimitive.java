// RoundPrimitive Assignment
// Author: Bogdan A Vasilchenko
//   Date: Oct 1, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class RoundPrimitive extends Primitive {
	public int x;
	public int y;
	public int width;
	public int height;
	
	

	@Override
	public void draw(UserInterface ui) {
		ui.fillColor(color);
		ui.lineColor(color);
		ui.drawOval(x, y, width, height, isFilled);
		
	}
	

}
