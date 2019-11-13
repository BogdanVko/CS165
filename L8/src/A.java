/**
 * javadoc created by garethhalladay on 8/13/17.
 * This is the first class in a series to help demonstrate properties of inheritance.
 */
public class A {

    // Base class variables
    public double x = 11;
    public double y = 22;

    /**
     * Initializes a newly create A object. <br>
     * At the top of the constructor print: "Constructor: A". <br>
     * Assign the fields to the corresponding parameters.
     * @param x the x value
     * @param y the y value
     */
    public A(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the value of the x attribute.
     * @return x
     */
    public double getX(){
        return x;
    }

    /**
     * Returns the value of the y attribute
     * @return y
     */
    public double getY(){
        return y;
    }

    /**
     * toString method for the A class.
     * @return A string with the following format: "(x, y)"

     */
    public String toString(){
        return String.format("A class: (%.1f,%.1f)", getX(), getY());
    }

    /**
     * An overloaded method that takes a string and returns the result of
     * the original {@link A#toString()} appended to it.
     * @param message the string to prepend to the original toString
     * @return the new message
     */
    public String toString(String message){
        return message + toString();
    }

    /**
     * @return the sum of x and y
     */
    public double sum(){
        return x + y;
    }

    /**
     * @return the product of x and y
     */
    public double product(){
        return x * y;
    }

}
