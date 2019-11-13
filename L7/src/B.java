// B Assignment
// Author: Bogdan A Vasilchenko
//   Date: Sep 26, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class B extends A {

	public B(double x, double y) {
		super(x, y);
		System.out.println("Constructor: B");
		
		// unsure of it is how it should be.
	}
	
	public double power() {
		return Math.pow(this.x, this.y);
	}

}
