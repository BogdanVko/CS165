
/**
 * javadoc created by garethhalladay on 8/13/17.
 * This is the second part of a set of classes that help demonstrate properties of inheritance.
 * By extending class A, B now immediately inherits all the public members and methods of A.
 */
public class B extends A {

    // Shadow variable!
    public double y = 33;

    /**
     * Initializes a newly created B object. <br>
     * Use these parameters to call the constructor of class A with the <b>super</b> keyword. <br>
     * If you aren't sure how to do this, check out
     * <a href=https://docs.oracle.com/javase/tutorial/java/IandI/super.html>this</a> out.
     * <p>
     * At the top of the constructor before the super call, print: "Constructor: B"
     * <p>
     * Note the error. Move the call to super to the start of the method. Now it should compile.
     * @param x value for the field x
     * @param y value for the field y
     */
    public B(double x, double y){
        super(x, y);
    }

    @Override
    public String toString() {
        return String.format("B class: (%.1f, %.1f)", x, y);
    }

    /**
     * New method
     * @return return x^y
     * @see Math#pow
     */
    public double power(){
        return Math.pow(x, y);
    }
}
