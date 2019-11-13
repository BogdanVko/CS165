// Square Assignment
// Author: Bogdan A Vasilchenko
//   Date: Sep 23, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class Square  extends PolygonPrimitive{
	
	
	public Square(int x, int y, int s) {
		
		//top (x, y)left top right(x+s, y)
		//bot (x, y+x) bot (x+s, y+s)
		
	super.xPoints = new int[] {x, x+s, x+s, x};
	super.yPoints = new int[] {y, y, y+s, y+s};
	
	}
	
	
	
	

}
