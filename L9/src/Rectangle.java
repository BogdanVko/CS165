/**
 * Content originated from Kushagra Tiwary, 10/30/2016
 * Modified by garethhalladay on 9/12/17
 *
 * Use point1.distance(point2) to find the distance between two Point objects.
 * You can assume that the distance between a and d is the same as the distance between b and c
 * and that the distance between a and b is the same as the distance between c and d.
 * The length is defined as the distance between a and d or the distance between b and c. <br>
 * The width is defined as the distance between a and b or the distance between c and d. <br>
 */
public class Rectangle extends Shape {
    private Point a;
    private Point b;
    private Point c;
    private Point d;

    public Rectangle(Point a, Point b, Point c, Point d) {
    	this.a = a;
    	this.b = b;
    	this.c = c;
    	this.d = d;
    }
    
    public double getWidth() {
    	return a.distance(b);
    }
    
    public double getLength() {
    	return a.distance(d); 
    }
    /**
     * Compares this rectangle to a specified object. The result is true
     * if and only if the argument is not null and is a Rectangle object
     * that represents the same sequence of Points as this object.
     * @param o the object to compare the rectangle against
     * @return true if the given object represents a rectangle equivalent to this rectangle, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle other = (Rectangle) o;
        return this.a.equals(other.a) && this.b.equals(other.b) &&
               this.c.equals(other.c) && this.d.equals(other.d);
    }

    /**
     * hashCode auto-generated using intelliJ. Note the use of a prime number.
     * @return hashCode for the Rectangle class.
     */
    @Override
    public int hashCode() {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        result = 31 * result + c.hashCode();
        result = 31 * result + d.hashCode();
        return result;
    }

	@Override
	public double calculateArea() {
		return getWidth() * getLength();
	}

	@Override
	public double calculatePerimeter() {
		
		return (getWidth() * 2) + (2 *getLength());
	}

    @Override
    public String toString() {
        return String.format("[length =  %.2f, ", getLength()) +
                       String.format("width = %.2f, ", getWidth()) +
                       String.format("area = %.2f, ", this.calculateArea()) +
                       String.format("perimeter = %.2f]", this.calculatePerimeter());
    }
}
