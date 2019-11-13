/**
 * Content originated from Kushagra Tiwary, 10/30/2016
 * Modified by garethhalladay on 9/13/17
 * Use point1.distance(point2) to find the distance between two Point objects.
 */
public class Circle extends Shape {
    private Point a;
    private Point b;

    
    public Circle(Point a, Point b) {
    	this.a = a;
    	this.b = b;
    }
    /**
     * Compares this circle to a specified object. The result is true
     * if and only if the argument is not null and is a Circle object
     * that represents the same sequence of Points as this object.
     *
     * @param o the object to compare the circle against
     * @return true if the given object represents a circle equivalent to this triangle, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Circle)) return false;
        Circle other = (Circle) o;
        return this.a.equals(other.a) && this.b.equals(other.b);
    }
    public double getRadius() {
    	return a.distance(b);
    }
    /**
     * Auto-generated hashcode using intelliJ. Note the use of a prime number.
     * @return hashcode for the Circle class
     */
    @Override
    public int hashCode() {
        return 31 * a.hashCode() + b.hashCode();
    }

	@Override
	public double calculateArea() {
		
		return a.distance(b)*a.distance(b)*Math.PI;
	}

	@Override
	public double calculatePerimeter() {
		// TODO Auto-generated method stub
		return 2*Math.PI*a.distance(b);
	}

    @Override
    public String toString() {
        return String.format("[radius = %.2f, ", getRadius()) +
                       String.format("area = %.2f, ", this.calculateArea()) +
                       String.format("perimeter = %.2f]", this.calculatePerimeter());
    }
}
