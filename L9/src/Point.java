// Point class for R15 lab
// Author: Kushagra Tiwary
// Date:   10/30/2016
// Class:  CS163/CS164

/**
 * Content originated from Kushagra Tiwary, 10/30/2016
 * Modified by garethhalladay on 9/12/17
 */
public class Point {

    /**
     * Instance variables that represent the coordinate values in a 2D space.
     */
    private double x,y;


    /**
     * Initializes a newly created Point object which has an x coordinate and a y coordinate. These
     * Points will be used to determine the location and size of different shape objects.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * Computes the distance between the current Point object and another Point.
     * @param b the other point
     * @return the distance between this and Point b
     */
    public double distance(Point b){
        return Math.sqrt((this.x-b.x)*(this.x-b.x)+((this.y-b.y)*(this.y-b.y)));
    }

    /**
     * String representation of the Point object
     * @return "(x,y)"
     */
    @Override
    public String toString(){
        return String.format("(%.2f, %.2f)", x, y);
    }

    /**
     * Compares this point to a specified object. The result is true
     * if and only if the argument is not null and is a Point object
     * that represents the same set of doubles as this object.
     * @param o the object to compare the point against
     * @return true if the given object represents a point equivalent to this point, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Point)) return false;
        Point other = (Point) o;
        return (this.x - other.x + this.y - other.y) < 0.00001;
    }

    /**
     * Auto-generated hashCode method using IntelliJ
     * @return hashCode for the Point class
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
