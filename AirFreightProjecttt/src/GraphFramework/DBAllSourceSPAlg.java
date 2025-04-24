package GraphFramework;

// This class runs Dijkstra's algorithm for every vertex in the graph (all-pairs shortest paths)
public class DBAllSourceSPAlg extends ShortestPathAlgorithm {

    // Constructor - connects the algorithm to a specific graph
    public DBAllSourceSPAlg(Graph inputGraph) {
        super(inputGraph); // pass the graph to the parent class
    }

    // This method applies Dijkstra from each vertex in the graph to compute all shortest paths
    public void computeDijkstraBasedSPAlg(boolean loadedFromFile) {

        // Create an object to run Dijkstra from one source vertex
        SingleSourceSPAlg dijkstra = new SingleSourceSPAlg(graph);


        // Go through each vertex (as source) and run Dijkstra
        int index = 0;
        while (index < graph.verticesNo) {
            dijkstra.computeDijkstraAlg(graph.vertices[index], loadedFromFile);
            index++;
        }

    }
}
