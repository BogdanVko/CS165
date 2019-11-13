// TestCode.java - test code for expression tree assignment.
// Author: Chris Wilcox
// Date:   3/19/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.MalformedInputException;
import java.util.*;

public class TestCode {

    // Test code for 
    public static void main(String[] args) {

        // Instantiate student code
        ExpressionTree eTree = new ExpressionTree();

        String expression = args[0];
        System.out.println("Original Expression: " + expression);

        // Verify parse
        Queue<String> infix = eTree.parse(expression);
        System.out.println("Infix Tokens: " + infix.toString());

        // Verify convert
        List<String> postfix = eTree.convert(infix);
        System.out.println("Postfix Tokens: " + postfix.toString());

        // Verify build
        eTree.build(postfix);
        System.out.println("Build: complete");

        // Verify prefix
        System.out.println("Prefix: " + eTree.prefix());

        // Verify infix
        System.out.println("Infix: " + eTree.infix());

        // Verify postfix
        System.out.println("Postfix: " + eTree.postfix());

        // Verify evaluate
        System.out.println("Evaluate: " + eTree.evaluate());

        // Verify display
        System.out.println("Display: complete");
        ArrayList<String> graph = eTree.display();
        writeFile(String.format("resources/Graph%s.dot", graph.hashCode()%100), graph);
    }

    // Utility method to write contents of ArrayList to file
    public static void writeFile(String filename, ArrayList<String> contents) {
        try {
            PrintWriter writer = new PrintWriter(new File(filename));
            for (String line : contents)
                writer.println(line);
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filename + "!");
        }
    }
}
