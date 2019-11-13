import java.util.ArrayList;
import java.util.Collection;

/**
 * TestCode.java - Main class for polymorphism lab
 * Created by Chris Wilcox, 2/6/2017
 * modified by garethhalladay, 09/14/2017
 */
public class TestCode {

    public static void main(String[] args) throws NoSuchMethodException {
        polymorphismExercise();
       // dynamicDispatchExercise();

    }


    public static void polymorphismExercise(){
        // Polymorphism exercise. Note the naming convention:
        // The first letter represents what the object is stored in.
        // the second letter represents what the object was instantiated as (what type the compiler see the object as).
        // Instantiate C, cast to B and A references
        C cc = new C(2, 3, 4);
        B bc = (B) cc;
        A ac = (A) cc;

        // Instantiate B, cast to A reference
        B bb = new B(2, 3);
        A ab = (A) bb;

        // Instantiate A
        A aa = new A(2, 3);

        // Run the code and record what error is thrown:
      //  System.out.println("Try to cast an A object to a C reference ");
        //C c0 = (C) new A(1, 3);
       // System.out.println(c0);
        /*
         * 1. Why does the first cast throw an error, while the casts above do not?
//         *   ANS Because C is an A while A is not a C. Casting A to C is not allowed.
         */

        // Comment out the code above and run the following code:
        // (The eclipse default shortcut for commenting/uncommenting lines of code is ctrl + / )
        
        //System.out.println("Exploring classes for each objects created above:");
//        System.out.printf("cc = %s \t", cc.getClass());
//        System.out.printf("bc = %s \t", bc.getClass());
//        System.out.printf("ac = %s \n", ac.getClass());
//        System.out.printf("bb = %s \t", bb.getClass());
//        System.out.printf("ab = %s \n", ab.getClass());
//        System.out.println("aa = " + aa.getClass());
//        System.out.println();
//        System.out.println("Which objects are instances of which classes.");
//        System.out.printf("ac instanceof A = %b  \t", (ac instanceof A));
//        System.out.printf("ac instanceof B = %b  \t", (ac instanceof B));
//        System.out.printf("ac instanceof C = %b\n", (ac instanceof C));
//        System.out.printf("ab instanceof A = %b  \t", (ab instanceof A));
//        System.out.printf("ab instanceof B = %b  \t", (ab instanceof B));
//        System.out.printf("ab instanceof C = %b\n", (ab instanceof C));
//        System.out.printf("aa instanceof A = %b  \t", (aa instanceof A));
//        System.out.printf("aa instanceof B = %b  \t", (aa instanceof B));
//        System.out.println("aa instance of C = " + (aa instanceof C));
//        System.out.println("\n");
        
        /*
         * 2. When instantiating cc, bb, and aa which constructors were called in which order and why?
         * cc - C, bb - B, aa - A. First in Herarchy tree.
         *
         */

        
        // comment out the code above and run the following code
//        System.out.println("Calling toString() on all references and printing the result.");
//        System.out.println("ac.toString() = " + ac);
//        System.out.println("bc.toString() = " + bc);
//        System.out.println("cc.toString() = " + cc);
       
//        System.out.println("\n");
        /* 3. Which toString() was called in each case and why?
         *C classes because C overrides the toString() from the parent classes and since
         *the following objects were casted to C, C classes methods will run first (if found)
         *
         * 4. Are the results as expected? If not, why not?
         *yep. Becuase that's JVM
         */

        // comment out the code above and run the following code
        System.out.println("Calling getClass() on all the the references and printing the result.");
        System.out.println("aa.getClass() = " + aa.getClass());
        System.out.println("ab.getClass() = " + ab.getClass());
        System.out.println("ac.getClass() = " + ac.getClass());
        System.out.println("\n");
        /*
         * 5. What class is printed in each case and why?
         * aa - A, ab - B, ac - C. Because they are casted to C.
         *
         */


        System.out.println("the sum() method on all references");
        System.out.println("ac.sum() = " + ac.sum());
        System.out.println("bc.sum() = " + bc.sum());
       System.out.println("cc.sum() = " + cc.sum());
        System.out.println("the product() methods for all references.");
        System.out.println("ac.product() = " + ac.product());
        System.out.println("bc.product() = " + bc.product());
       System.out.println("cc.product() = " + cc.product());
        System.out.println("\n");
         /*
          * 6. With what you learned about the toString(),
          *    were you able to guess what the sum and product methods would return?
          *    They returnt the sum of method from C class.
          *
          */

       System.out.println("The power() method on all the references");
     //  System.out.println("ac.power() = " + ac.power());
       System.out.println("bc.power() = " + bc.power());
       System.out.println("cc.power() = " + cc.power());
       System.out.println("ab.power() = " + bb.power());
     //  System.out.println("The fields or instance variables for the object cc = " + Arrays.toString(cc.getClass().getFields()));
        System.out.println("\n");
        /*
         * 7. If ac is an instance of C, and ab is an instance of b, then why does the power() method still not work?
         * A doesn't have power()
         *
         * Comment ac.power() out and rerun the code.
         * 8. Observe the different fields for the C object and the B object.
         *    Notice that there is a B.y field and go to the B class and check it out in the code.
         * Was shadow the variable y in B ac good idea? Why did it shadow the b variable when we put value into the constructor?
         *Because of the casting it can do that.
         *
         * Fix the problem that occurred above by removing the variable shadow in B.
         * 9. Which power() method is called, the one in B or the overridden one in C.
         *the one from C
         */


       System.out.println("The overloaded toString() method in the A class, for all references.");
        System.out.println(cc.toString("C cc: "));
      System.out.println(bc.toString("B bc: "));
       System.out.println(ac.toString("A ac: "));
      System.out.println(bb.toString("B bb: "));
       System.out.println(ab.toString("A ab: "));
        System.out.println(aa.toString("A aa: "));
     System.out.println("\n");
        /*
          * 9. Which overridden of toString() is called from the overloaded toString(String).
          *C and then B and A on the bottom
          *
          * 10. Dynamic binding, as you will continue to work with in the next section, is when the
          * type of the object (and therefore which method to call) is determined when you run the program.
          * How did the previous examples demonstrate to you how dynamic binding works?
          *A class is called in the child if possible in Java,
          * or whatever it's casted to first.
          *Then parent
          *
          */


    }


    public static void dynamicDispatchExercise() throws NoSuchMethodException{
        // Quick demo using the private API for clarity
        // Other classes should use the public API (Call, On)
        DynamicDispatch.log_to_err(true);
        String s = "hello, world!";
        System.out.println("Length is: " + DynamicDispatch.dynamicallyCallMethodOnObject("length", s));
        System.out.println("Index of 'o' is: " + DynamicDispatch.dynamicallyCallMethodOnObject("indexOf", s, 'o'));
        System.out.println("equalsIgnoreCase(\"Hello, World!\") is: " +
                                   DynamicDispatch.dynamicallyCallMethodOnObject("equalsIgnoreCase", s, "Hello, World!"));
        DynamicDispatch.log_to_err(false);

        // YOUR CODE HERE
        // 1. Create a new `ArrayList` and assign it to a variable of type `Collection`.
        //    Print out the result of calling `getClass()` on that variable. What does it show you?
        ArrayList<Collection> list = new ArrayList<Collection>();
        System.out.println(list.getClass());
        //shows me where a method is found and what it doesn. Neat-o!


        // 2. Comment the following code in when you are finished testing dynamicallyCallMethodOnObject method.
        //    Run the method a few more times with objects from classes A, B, and C. I suggest using the C class to call
        //    the overloaded toString. Because this method is in the A class, your method will have to recurse through all
        //    three classes before successfully finding the method.
//        DynamicDispatch.log_to_err(true); (String method_name, Object o, Object... arguments)
        C c = new C(3.4, 45.6, 0.3482);
        DynamicDispatch.log_to_err(true);
        System.out.println("C class testing:  " + DynamicDispatch.dynamicallyCallMethodOnObject("toString", c));
        DynamicDispatch.log_to_err(false);



    }


}
