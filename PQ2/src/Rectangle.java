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

    /**
     * Initializes a newly created Rectangle object. The class description goes into further details about
     * preconditions for the points.
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public Rectangle(Point a, Point b, Point c, Point d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Calculates the width of the rectangle. Read the description about the class to determine which
     * points to use for this calculation
     * @return the width of the rectangle
     */
    public double getWidth(){
        return a.distance(b);
    }

    /**
     * Calculates the length of the rectangle. Read the description about the class to determine which
     * points to use for this calculation
     * @return the length of the rectangle
     */
    public double getLength(){
        return b.distance(c);
    }

    /**
     * Calculates the area of the rectangle.
     * @return the area of the rectangle.
     */
    @Override
    public double calculateArea() {
        return getLength() * getWidth();
    }


    @Override
    public String toString() {
        return String.format("[%s, Point a = %s, Point b = %s, Point c = %s, Point d = %s]", this.getClass().getName(), a, b, c, d);
    }
}
