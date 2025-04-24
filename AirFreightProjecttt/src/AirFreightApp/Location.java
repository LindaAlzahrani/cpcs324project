package AirFreightApp;

import GraphFramework.Vertex;

// This class represents a specific location (like a city) in the graph
// It extends the generic Vertex class and adds a custom display format
public class Location extends Vertex {

    // This variable will store the full string used in the output
    private String cityLabel;

    // Constructor that accepts an ID and sends it to the parent class
    public Location(int id) {
        super(id); // Initialize vertex with given ID
    }

    // Default constructor
    public Location() {
        super(); // Let parent handle initialization
    }

    // Optional setter if we want to manually set a city label
    public void setCity(String city) {
        this.cityLabel = city;
    }

    // This method controls how we show information about the location in output
    @Override
    public String displyInfo() {
        // Build the string each time dynamically instead of storing it (less error-prone)
        return "Loc." + getLabel() + ": city " + getID() + " ";
    }
}
