// Rectangle Assignment
// Author: Bogdan A Vasilchenko
//   Date: Sep 23, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class Rectangle  extends PolygonPrimitive{
	
	
	
	
	public Rectangle(int x, int y, int w, int h) {
		super.xPoints = new int[] {x, x+w, x+w, x};
		super.yPoints = new int[] {y, y, y+h, y+h};
		
	
	}
	
	
	

}
