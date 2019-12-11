// GraphImplementation.java - supplied code for graph assignment
// Author: ?????
// Date:   ?????
// Class:  CS165
// Email:  ?????

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class GraphImplementation extends GraphAbstract {

    // Main entry point
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("usage: java GraphImplementation <distanceFile> <graphFile>");
            System.exit(-1);
        }

        // Instantiate code
        GraphImplementation impl = new GraphImplementation();

        // Read distances chart
        System.out.println("Reading Chart: " + args[0]);
        impl.readGraph(args[0]);
        System.out.println();

        // Write distances graph
        System.out.println("Writing Graph: " + args[1]);
        impl.writeGraph(args[1]);
        System.out.println();

        // Print depth first search
        System.out.println("Depth First Search:");
        impl.depthFirst("Fort Collins");
        System.out.println();

        // Print breadth first search
        System.out.println("Breadth First Search:");
        impl.breadthFirst("Aspen");
        System.out.println();

        /*
        // EXTRA CREDIT: Print all shortest paths
        for (int from = 0; from < cities.size(); from++) {
            for (int to = 0; to < cities.size(); to++)
                if (from != to) {
                    String fromCity = cities.get(from).name;
                    String toCity = cities.get(to).name;
                    System.out.print("Shortest Path: ");
                    impl.shortestPath(fromCity, toCity);
                }
        }
        */
    }

    // Reads mileage chart from CSV file
    public void readGraph(String filename) {
    	 ArrayList<String> lines = readFile(filename);
    	 
    	 String firstLine = lines.get(0);
    	 String firstLineArray[] = firstLine.split(",");
    	 
    	 //fill the cities
    	 for(int i = 1; i < firstLineArray.length; i++) {
    		 GraphNode node = new GraphNode(firstLineArray[i]);
    		 cities.add(node);
    	 }
    	 // can't use a for each one here
    	 
    	 
    	 for(int  i = 1; i < lines.size(); i ++) {
    		 
    	 
    		 String[] distances = lines.get(i).split(",");
    		 
    		 for(int j = 1; j < distances.length; j++) {
    			 if(distances[j].isEmpty()) {
    				 continue;
    			 }
    			 
    			 
    			 GraphEdge e = new GraphEdge(i-1,  j-1,  Integer.parseInt(distances[j]));
    			 cities.get(i-1).edges.add(e);
    			 GraphEdge e2 = new GraphEdge(j-1, i-1, Integer.parseInt(distances[j]));
    			 cities.get(j-1).edges.add(e2);
    			 mileages.add(e);
    			 
    			 
    			 
    			 
    			 
    		 }		 
    	 }
    	 
    	 
    }

    public void writeGraph(String filename) {

        ArrayList<String> output = new ArrayList<>();
        output.add("digraph BST {");
        output.add("    ratio = 1.0;");
        output.add("    node [style=filled]");
        output.add("    node [fillcolor=darkslateblue]");
        output.add("    node [fixedsize=true]");
        output.add("    node [shape=oval]");
        output.add("    node [width=6]");
        output.add("    node [height=4]");
        output.add("    node [fontname=Arial]");
        output.add("    node [fontsize=60]");
        output.add("    node [fontcolor=white]");
        output.add("    edge [dir=none]");
        output.add("    edge [penwidth=24]");
        output.add("    edge [fontname=Arial]");
        output.add("    edge [fontsize=110]");
        
        for (GraphNode n : cities){
            output.add(String.format("    Node%d [label=\"%s\"]", n.edges.get(0).fromIndex, n.name));
        }
        for (GraphEdge e : mileages){
            output.add(String.format("    Node%d -> Node%d [label=\"%d\" color=\"%s\"]",
                                     e.fromIndex, e.toIndex, e.mileage, getColor(e.mileage)));
        }
        output.add("}");
        writeFile(filename, output);
    }

    private String getColor(int mileage){
        if(mileage <= 100) {
            return "green";
        }if(mileage <= 200) {
            return "blue";
        }if(mileage <= 300){
            return "magenta";
        }
        return "red";
    }


    public void depthFirst(String startCity) {
        ArrayList<Integer> visited = new ArrayList<Integer>();
    }

    // Recursive helper method
    public void depthFirst(int index, ArrayList<Integer> visited) {
        // YOUR CODE HERE
    }

    public void breadthFirst(String startCity) {
        // YOUR CODE HERE
    }


    public void shortestPath(String fromCity, String toCity) {
        // YOUR CODE HERE
    }

    // Helper functions

    /**
     * Reads the contents of file to {@code ArrayList}
     * @param filename the file to read from
     * @return an ArrayList of the contents
     */
    static ArrayList<String> readFile(String filename) {
        ArrayList<String> contents = new ArrayList<>();
        try(Scanner reader = new Scanner(new File(filename))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (!line.isEmpty())
                    contents.add(line);
            }
        } catch (IOException e) {
            System.err.println("Cannot read chart: " + filename);
        }
        return contents;
    }

    /**
     * Write contents of {@code ArrayList} to file
     * @param filename the name of the file to write to
     * @param contents an ArrayList of contents to write
     */
    static void writeFile(String filename, ArrayList<String> contents) {
        try(PrintWriter writer = new PrintWriter(filename)) {
            for (String line : contents)
                writer.println(line);
        } catch (IOException e) {
            System.err.println("Cannot write graph: " + filename);
        }
    }
}
