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
public abstract class Shape {


    /**
     * Abstract method that returns the area of polygon.
     * @return the area of a polygon
     */
    public abstract double calculateArea();

}


