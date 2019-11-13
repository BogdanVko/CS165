// C Assignment
// Author: Bogdan A Vasilchenko
//   Date: Sep 26, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class C extends B{
	protected double z;
	
	
	public C(double x, double y, double z) {
		super(x, y);
		this.z = z;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double sum() {
		
		return x + y + z;
	}
	
	@Override
	public double product() {
		return x*y*z;
	}
	
	@Override
	public double power() {
		double x_to_y = Math.pow(this.x, this.y);
		
		return Math.pow(x_to_y, z);
		
	}
	
	@Override
	public String toString() {
		
		return "("+x+", "+" "+y+", "+z+")";
		
	}

}
