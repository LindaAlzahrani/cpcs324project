package GraphFramework;

// This class acts as a base for all shortest path algorithms (like Dijkstra)
public class ShortestPathAlgorithm {

    // === Attributes ===

    protected Graph graph; // The graph on which we apply the algorithm

    // === Constructor ===

    // We pass the graph when we create the algorithm object
    public ShortestPathAlgorithm(Graph graph) {
        this.graph = graph;
    }
}

