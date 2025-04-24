package GraphFramework;

import java.util.LinkedList;

// This abstract class represents a single location (vertex) in the graph
public abstract class Vertex {

    // === Attributes ===

    protected char label;                    // A character label like 'A', 'B', etc.
    protected boolean isVisited;             // Used in some algorithms to mark visited nodes
    protected int ID;                        // Unique ID for each vertex
    protected LinkedList<Edge> adjList;      // List of edges connected to this vertex

    // === Constructors ===

    public Vertex() {
        // Default constructor - doesn't set anything yet
    }

    public Vertex(int ID) {
        this.ID = ID;                        // Set vertex ID
        this.adjList = new LinkedList<>();   // Initialize its adjacency list
    }

    // === Useful Methods ===

    // Checks if this vertex is connected to another vertex
    public boolean isAdjacent(Vertex other) {
        for (Edge edge : adjList) {
            if (edge.target == other) {
                return true;
            }
        }
        return false;
    }

    // === Setters & Getters ===

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LinkedList<Edge> getAdjList() {
        return adjList;
    }

    public void setAdjList(LinkedList<Edge> adjList) {
        this.adjList = adjList;
    }

    // Each subclass (like Location) must provide how to display info
    public abstract String displyInfo();
}
