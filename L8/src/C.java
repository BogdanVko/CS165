/**
 * javadoc created by garethhalladay on 8/13/17.
 * This is the third part of a set of classes that help demonstrate properties of inheritance.
 * By extending the B class, C now inherits all the public methods and members from class A.
 */
public class C extends B {

    // SubSub class variables
    public double z = 44;

    /**
     * Initializes and newly created C object. <br>
     * Call the B constructor with x and y by using the <b>super</b> keyword. <br>
     * Assign z into the corresponding field.
     * @param x the value for x
     * @param y the value for y
     * @param z the value for z
     */
    public C(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    /**
     * The sum of the attributes. <br>
     * Use @Override annotation to make sure that you have the method signature correct. <br>
     * First use sum method in the super class to sum x and y and add in z
     * @return the sum of x, y and z
     */
    @Override
    public double sum(){
        return super.sum() + z;
    }

    /**
     * The product of the attributes. <br>
     * Use @Override annotation to make sure that you have the method signature correct. <br>
     * First use product method in the super class to get the product of x and y, then multiply that by z.
     * @return return the product of x, y, and z.
     */
    @Override
    public double product(){
        return super.product() * z;
    }

    /**
     * The product of the attributes. <br>
     * Use @Override annotation to make sure that you have the method signature correct. <br>
     * Use power method in the super class for the first argument in {@link Math#pow(double, double)}
     * @return ((x to the y) to the z)
     */
    @Override
    public double power(){
        return Math.pow(super.power(), z);
    }


    /**
     * toString method for the C class. <br>
     * @return A string with the following format: "(x, y, z)"
     */
    @Override
    public String toString(){
        return String.format("C class: (%.1f,%.1f,%.1f)", getX(), getY(), z);
    }

}
