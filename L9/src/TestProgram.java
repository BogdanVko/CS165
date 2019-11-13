import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Content originated from Kushagra Tiwary, 10/30/2016
 * Modified by garethhalladay, 19/13/2017
 */
public class TestProgram {


    /**
     * Test cases for the Triangle class
     * @param a initial value for the Triangle constructor
     * @param b initial value for the Triangle constructor
     * @param c initial value for the Triangle constructor
     */
    static void testTriangle(Point a, Point b, Point c){
        Triangle tri1 = new Triangle(a,b,c);
        System.out.printf("Perimeter of triangle=%.2f",tri1.calculatePerimeter());
        System.out.printf("->%b\n", String.format("%.2f", tri1.calculatePerimeter()).equals("13.66"));
        System.out.printf("Area of triangle=%.2f", tri1.calculateArea());
        System.out.printf("->%b\n", String.format("%.2f", tri1.calculateArea()).equals("8.00"));
        System.out.print(tri1); //prints the return value from tri1.toString()
    }

    /**
     * Test cases for the Rectangle class
     * @param a initial value for the Rectangle constructor
     * @param b initial value for the Rectangle constructor
     * @param c initial value for the Rectangle constructor
     * @param d initial value for the Rectangle constructor
     */
    static void testRectangle(Point a, Point b, Point c, Point d){
        Rectangle rect1 = new Rectangle(a,b,c,d);
        System.out.print("Perimeter of rectangle="+rect1.calculatePerimeter());
        System.out.printf("->%b\n", String.format("%.2f", rect1.calculatePerimeter()).equals("16.00"));
        System.out.print("Area of rectangle="+rect1.calculateArea());
        System.out.printf("->%b\n", String.format("%.2f", rect1.calculateArea()).equals("16.00"));
        System.out.println(rect1); //prints the return value from rect1.toString()
    }

    /**
     * Test cases for the Circle class
     */
    static void testCircle(){
        List<Circle> cArr = new ArrayList<>(Arrays.asList(new Circle(new Point(0, 0), new Point(0, 1)),
                                                            new Circle(new Point(0,0),new Point(0,4)),
                                                            new Circle(new Point(0,0),new Point(0,2))));

        System.out.println(cArr);
        System.out.printf("Circle class->%b\n", cArr.toString().equals(
                                        "[[radius = 1.00, area = 3.14, perimeter = 6.28], " +
                                        "[radius = 4.00, area = 50.27, perimeter = 25.13], " +
                                        "[radius = 2.00, area = 12.57, perimeter = 12.57]]"));
    }

    static void testSorting(){
        List<Shape> shapeArray = createShapeArray();
        // Print out polygons before sorting
        printShapes(shapeArray, "UNSORTED: ");
        // Calling sort method on List, assumes Comparable interface
        shapeArray.sort(Shape::compareTo);
        // Print out polygons after sorting
        printShapes(shapeArray, "SORTED: ");
    }

    /**
     * Creates a list with various shapes.
     * @return a list of polygons
     */
    static List<Shape> createShapeArray(){
        return Arrays.asList(new Rectangle(new Point(0,0),new Point(0,4),new Point(2,4), new Point(2,0)),
                             new Rectangle(new Point(0,0),new Point(0,2),new Point(2,2), new Point(2,0)),
                             new Rectangle(new Point(0,0),new Point(0,3),new Point(2,3), new Point(2,0)),
                             new Circle(new Point(0,0),new Point(0,1)),
                             new Circle(new Point(1,0),new Point(1,4)),
                             new Circle(new Point(1,0),new Point(0,2)),
                             new Triangle(new Point(0,0),new Point(0,2),new Point(2,0)),
                             new Triangle(new Point(0,0),new Point(0,4),new Point(4,0)),
                             new Triangle(new Point(0,0),new Point(0,1),new Point(1,0)));

    }

    /**
     * prints the shapes, one per line.
     * @param array list of various shapes
     * @param label a label that's printed before the shapes are printed. In this case SORTED or UNSORTED
     */
    static void printShapes(List<Shape> array, String label) {

        System.out.println(label + "\n");
        // Iterate array of polygons using a foreach loop
        for (Shape shape : array) {
            // Use getClass to print the class of the Object and then print the Object (calls the toString)
            System.out.printf("%s: %s\n", shape.getClass(), shape);
        }
        System.out.println();
    }

    public static void main(String[]args){

        Point a = new Point(0,0);
        Point b = new Point(0,4);
        Point c = new Point(4,4);
        Point d = new Point(4,0);

        testTriangle(a, b, c);
        testRectangle(a, b, c, d);
        testCircle();
        testSorting();

        /*
         * 1. Can an abstract class have both concrete and abstract methods?
         *
         *YEP
         * 2. Can abstract classes inherit from interfaces?
         *YEP
         *
         * 3. Why can't you instantiate abstract classes?
         *Because it's abstract. 
         *
         * 4. What is the @Override annotation and what does it do?
         *Indicated that the method is beig overriden. Not necassary, but usefull
         *
         * 5. Explain each statement in the equals method for one of the shapes (it's doesn't matter which one).
         *    Be specific. Use the provided link at the top of the recitation to analyze the code.
         * public boolean equals(Object o) {
        if (o == this) return true; <- if object is being comared to itself. if so then true
        if (!(o instanceof Circle)) return false; <- if the other obj is not an instace of Shape, return false.
        Circle other = (Circle) o; <- cast the other shape to this class.
        return this.a.equals(other.a) && this.b.equals(other.b); <- see if points
    }

         *
         * 6. Off the top of my head, I can think of two other ways you could test shape equality.
         *    Name another way you could check if the shapes are equal.
         *		Check is the getClass and perimiters are equal, and then check if each side is equal too  (the getters)
         *
         * 7. What is the instanceof operator in Java and what does it do (google for additional information)?
         *	Checks if the objects are the same class.
         *	
         * 8. Why is compareTo not implemented in each shape subclass?
         * not necassary. 
         *
         * 9. Why is the equals method not just implemented in the shape class?
         * because you can't check if this.same class as (other)
         */

    }



}
