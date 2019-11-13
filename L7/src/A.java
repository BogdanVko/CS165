// A Assignment
// Author: Bogdan A Vasilchenko
//   Date: Sep 26, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class A {
	
	protected double x;
	protected double  y;
	
	public A(double x, double y) {
		System.out.println("Constructor: A");
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double sum() {
		return x+y;
	}
	
	public double product() {
		return x*y;
	}
	
	@Override
	public String toString() {
		
		return "("+x+", "+" "+y+")";
		
	}
	
	public String toString(String message) {
		return  message + this.toString();
		
		
	}

}
