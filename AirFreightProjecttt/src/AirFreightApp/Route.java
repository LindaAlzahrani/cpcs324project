package AirFreightApp;

import GraphFramework.Edge;
import GraphFramework.Vertex;

// This class represents a "Route" between two locations
// It extends the generic Edge class and customizes the display behavior
public class Route extends Edge {

    // === Constructors ===

    // Constructor to create a route between two vertices with a specific weight
    public Route(Vertex source, Vertex destination, int weight) {
        super(source, destination, weight); // call the parent constructor
    }

    // Constructor when only weight is known (vertices can be set later)
    public Route(int weight) {
        super(weight); // just store the weight for now
    }

    // Default constructor (in case we want to build later)
    public Route() {
        super(); // let the Edge class handle default init
    }

    // === Method to show route info in the output ===
    @Override
    public String displyInfo() {
        // Return the route length in a formatted string
        return " --- route length: " + getWeight();
    }
}
