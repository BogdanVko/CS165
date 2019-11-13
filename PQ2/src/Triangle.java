/**
 * Content originated from Kushagra Tiwary, 10/30/2016
 * Modified by garethhalladay on 9/12/17
 *
 * Use point1.distance(point2) to find the distance between two Point objects.
 * side1 is the distance between Points a and b.
 * side2 is the distance between Points b and c.
 * side3 is the distance between Points c and a.
 */
public class Triangle extends Shape {

    private Point a;
    private Point b;
    private Point c;

    /**
     * Initializes a newly created Triangle object. The class description goes into more detail about
     * the Points.
     * @param a the initial value of Point a
     * @param b the initial value of Point b
     * @param c the initial value of Point c
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * The length of the first side of the triangle. Use the class description to determine which points to use.
     * @return the length of side1
     */
    public double getSide1(){
        return a.distance(b);
    }

    /**
     * The length of the second side of the triangle. Use the class description to determine which points to use.
     * @return the length of side2
     */
    public double getSide2(){
        return b.distance(c);
    }

    /**
     * The length of the third side of the triangle. Use the class description to determine which points to use.
     * @return the length of side3
     */
    public double getSide3(){
        return c.distance(a);
    }

    /**
     * Calculate the area of the triangle.
     * @return the area of the triangle
     */
    @Override
    public double calculateArea() {
        double p = calculatePerimeter() / 2.0;
        return Math.sqrt(p * (p - getSide1()) * (p - getSide2()) * (p - getSide3()));
    }

    /**
     * Calculate the perimeter of the triangle.
     * @return the perimeter of the triangle
     */
    public double calculatePerimeter() {
        return getSide1() + getSide2() + getSide3();
    }


    @Override
    public String toString() {
        return String.format("[%s, Point a = %s, Point b = %s, Point c = %s]", this.getClass().getName(), a, b, c);
    }

}
