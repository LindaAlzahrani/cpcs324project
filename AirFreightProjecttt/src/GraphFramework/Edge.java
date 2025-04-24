package GraphFramework;

// This abstract class represents a generic edge in any graph (can be road, route, etc.)
public abstract class Edge {

    // === Attributes ===

    // Represents the cost or distance of the edge
    int weight;

    // The source and target vertices (where the edge starts and ends)
    Vertex source;
    Vertex target;

    // Optional parent reference (used during path calculations like Dijkstra)
    Vertex parent;

    // === Constructors ===

    // Default constructor (for flexibility)
    public Edge() {
        // can be initialized later
    }

    // Constructor to only assign the weight (vertices can be added later)
    public Edge(int weight) {
        this.weight = weight;
    }

    // Constructor to assign everything at once
    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;

        // Set parent as the source by default (common in shortest path logic)
        this.parent = source;
    }

    // === Getters and Setters ===

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    // === Abstract Method ===
    // This method will be defined in the child class (like Route)
    // It controls how edge info is shown in the output
    public abstract String displyInfo();
}
