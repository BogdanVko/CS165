// PolygonPromitive Assignment
// Author: Bogdan A Vasilchenko
//   Date: Oct 1, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class PolygonPrimitive extends Primitive {
	public int[] xPoints;
	public int[] yPoints;
	
	public void draw(UserInterface ui) 
	{
		ui.fillColor(super.color);
		ui.lineColor(super.color);
		
		ui.drawPolygon(xPoints, yPoints, super.isFilled);
	}
	

}
