package AirFreightApp;

import GraphFramework.Edge;
import GraphFramework.Graph;
import GraphFramework.Vertex;

// This class represents the Air Freight Route Map
// It extends the generic Graph class and defines how vertices and edges are created
public class AFRouteMap extends Graph {

    // Default constructor (used when we don't know size yet)
    public AFRouteMap() {
        // Call the constructor from the parent Graph class
        super();
    }

    // Constructor with number of vertices, edges, and graph type
    public AFRouteMap(int verticesNo, int edgeNo, boolean isDigraph) {
        // Send values to the parent Graph constructor
        super(verticesNo, edgeNo, isDigraph);
    }

    // This method creates a vertex of type "Location"
    // It's overriding the abstract method in Graph class
    @Override
    public Vertex creatVertex(int ID) {
        return new Location(ID); // create a new Location object with the given ID
    }

    // This method creates an edge of type "Route" between two vertices with weight
    @Override
    public Edge creatEdge(Vertex v, Vertex u, int w) {
        return new Route(v, u, w); // create a new Route between v and u with weight w
    }

    // This one just creates a Route with only a weight
    @Override
    public Edge creatEdge(int w) {
        return new Route(w); // used when we don't have vertex references yet
    }

    // This one creates an empty Route (default constructor)
    @Override
    public Edge creatEdge() {
        return new Route(); // useful when we want to set properties later
    }
}
