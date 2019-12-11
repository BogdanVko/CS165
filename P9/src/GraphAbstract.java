// GraphAbstract.java - abstract class for graph assignment
// Author: Chris Wilcox
// Date:   4/16/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

import java.util.ArrayList;

public abstract class GraphAbstract {

    // Represents a graph edge
    public class GraphEdge implements Comparable<GraphEdge> {
        public int fromIndex; // index of "from" city
        public int toIndex; // index of "to" city
        public int mileage; // mileage between cities
        public GraphEdge (int from, int to, int mileage) {
            this.fromIndex = from;
            this.toIndex = to;
            this.mileage = mileage;
        }
        public int compareTo(GraphEdge edge) {
            return this.mileage - edge.mileage;
        }
        public String toString() {
        return " " + mileage + " ";
        }
    }

    // Represents a graph node (and incident edges)
    public class GraphNode {
        public String name; // City name
        public ArrayList<GraphEdge> edges; // Distances
        public GraphNode(String name) {
            this.name = name;
            edges = new ArrayList<>();
        }
        public boolean equals(Object object) {
            return object instanceof GraphNode && ((GraphNode) object).name.equals(this.name);
        }
    }
    
    // Graph data structures
    public static ArrayList<GraphNode> cities = new ArrayList<>();
    public static ArrayList<GraphEdge> mileages = new ArrayList<>();


    /**
     * Reads mileage chart from CSV file and builds lists of nodes (cities) and edges (distances).
     * <p>
     * The first line contains all the cities which should be represented as {@link GraphNode}s <br>
     * The successive lines start with a city, followed by a list of mileages to other cities.
     * <p>
     * To avoid redundancy, not all the values are filled in, ignore empty entries. <br>
     * When you read a mileage, for example from Fort Collins to Denver, create only one
     * entry in the mileages array, but add the edge to both cities.
     * <p>
     * First extract all the edges, then sort the edges by mileage, then add the edges
     * associated with each node.
     * @param filename the CSV file
     */
    public abstract void readGraph(String filename);

    /**
     * Writes mileage graph to DOT file.
     * <p>
     * Traverses the data structures created above and writes nodes and edges in GraphViz format.
     * You will build the GraphViz format into an {@code ArrayList}, which will then be written to file
     * using the {@link GraphImplementation#writeFile(String, ArrayList)} method.
     * <p>
     * Use the provided example and the following directions to implement this method:
     * <ul>
     *     <li> All attributes of nodes and edges that are identical are put into the header of the .dot file.
     *     <li> The nodes are labeled Node0, Node1, etc., in the same order as the input file,
     *          and they have city names as labels.
     *     <li> The edges are then written, in the format Node0 -> Node1, etc. and labeled with a distance and color.
     * </ul>
     * The edge color should be green for {@code ≤100 miles}, blue for {@code ≤200} miles,
     * magenta for {@code ≤300 miles}, and red otherwise.
     * <p>
     * HINT: Match the file format exactly as provided in order to pass automated grading!
     * @param filename the output file name
     */
    public abstract void writeGraph(String filename);
    
    // Print graph in depth first search order
    public abstract void depthFirst(String startCity);

    // Print graph in breadth first search order
    public abstract void breadthFirst(String startCity);

    // Calculate and print shortest path
    public abstract void shortestPath(String fromCity, String toCity);
}
