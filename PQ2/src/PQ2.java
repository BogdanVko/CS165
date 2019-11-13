import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PQ2{

    public static List<Shape> createShapeArray(){
        // Use the following points to create your shapes
        Point a = new Point(1, 0);
        Point b = new Point(1, 5);
        Point c = new Point(3, 5);
        Point d = new Point(3, 0);
        // your code here
        List<Shape> shapez = new ArrayList<Shape>();
        
        Shape cir = new Circle(a, b);
        Shape tri = new Triangle(a, b, c);
        Shape rect = new Rectangle(a, b, c, d);
        shapez.add(cir);
        shapez.add(tri);
        shapez.add(rect);
        return shapez;
    }

    public static void displayArea(List<Shape> shapes){
       for (Shape s : shapes) {
    	   String name = s.getClass().getSimpleName();
    	   name = name.toUpperCase();
    	   System.out.printf("%s: %.2f\n", name, s.calculateArea());
       }

    }

    public static int sumOfSquares(List<Integer> num) {
        if (num.isEmpty()) {
            return 0;
        }
        
        int temp = num.get(0)*num.get(0);
        
        num.add(1, temp);
        
        return num.get(1) + sumOfSquares(num.subList(2, num.size()));
    }

    

  
    public static void main(String [] args){
        // Question 1
        // Testing the createShapeArray method
        List<Shape> shapes = createShapeArray();
        System.out.println("Created correct array: " + shapes.toString().equals("[[Circle, Point a = (1.00, 0.00), Point b = (1.00, 5.00)], [Triangle, Point a = (1.00, 0.00), Point b = (1.00, 5.00), Point c = (3.00, 5.00)], [Rectangle, Point a = (1.00, 0.00), Point b = (1.00, 5.00), Point c = (3.00, 5.00), Point d = (3.00, 0.00)]]"));

        /* Question 2
         * displayArea should print:
         *
         * CIRCLE: 78.54
         * TRIANGLE: 5.00
         * RECTANGLE: 10.00
         */
        displayArea(shapes);

        // Question 3
        // testing an odd amount of numbers
        List<Integer> exampleOdd = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println(exampleOdd); // [1, 2, 3]
        System.out.println(sumOfSquares(exampleOdd)); // 14
        System.out.println(exampleOdd); // [1, 1, 2, 4, 3, 9]

        // testing an even amount of numbers
        List<Integer> exampleEven = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        System.out.println(exampleEven); // [1, 2, 3, 4]
        System.out.println(sumOfSquares(exampleEven)); // 30
        System.out.println(exampleEven); // [1, 1, 2, 4, 3, 9, 4, 16]

    }

 }