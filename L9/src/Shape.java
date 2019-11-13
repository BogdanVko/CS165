/**
 * Content originated from Kushagra Tiwary, 10/30/2016
 * Modified by garethhalladay on 9/12/17
 * An abstract class may contain one or more abstract methods and may also contain
 * non-abstract methods. Abstract methods are methods that are declared but not implemented,
 * and therefore must be overridden by the class that extends this class. They are declared
 * using the keyword 'abstract'.
 *
 * An equivalent interface to this abstract class would be as follows:
 *
 * public interface Shape {
 *    public int area();
 *    public int perimeter();
 * }
 *
 * The Comparable interface supports sorting according the criteria defined by the class.
 * The Comparable interface requires only one method called compareTo that compares objects.
 */
public abstract class Shape implements Comparable<Shape> {

	
    /**
     * Abstract method that returns the area of polygon.
     * @return the area of a polygon
     */
    public abstract double calculateArea();

    /**
     * Abstract method that returns the perimeter of polygon.
     * @return the perimeter of a polygon
     */
    public abstract double calculatePerimeter();

    /**
     * compareTo method required for implementing the Comparable interface
     * Return:
     * <ul>
     *     <li> -1 from compareTo if the area of this Shape is less than the area of the Shape parameter
     *     <li> 0 from compareTo if the area of this Shape equals the area of the Shape parameter
     *     <li> 1 from compareTo if the area of this Shape is greater than the area of the Shape parameter
     * </ul>
     *
     * @param shape
     * @return
     */
    public int compareTo(Shape shape){
        if(this.calculateArea() == shape.calculateArea()) {
        	return 0;
        } else if (this.calculateArea() < shape.calculateArea()) {
        	
        	return -1;
        } else {
        	return 1;
        }
    }
}


