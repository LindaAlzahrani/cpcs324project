package GraphFramework;

import java.io.*;
import java.util.*;
import AirFreightApp.Location;

// This abstract class represents a general graph structure used across different graph types
public abstract class Graph {

    // === Attributes ===
    protected int verticesNo;      // Number of vertices in the graph
    protected int edgeNo;          // Total edges currently added
    protected boolean isDigraph;   // Whether the graph is directed
    protected int AllEdgesNo;      // Maximum number of edges allowed
    public Vertex[] vertices;      // Array of vertices (indexed by ID)

    // === Constructors ===
    public Graph() {
        // Empty constructor (used when details are not yet known)
    }

    public Graph(int verticesNo, int edgeLimit, boolean isDigraph) {
        this.verticesNo = verticesNo;
        this.AllEdgesNo = edgeLimit;
        this.isDigraph = isDigraph;
        this.vertices = new Vertex[verticesNo];
    }

    // === Abstract methods to be implemented by subclasses ===
    public abstract Vertex creatVertex(int ID);

    public abstract Edge creatEdge(Vertex v, Vertex u, int w);

    public abstract Edge creatEdge(int w);

    public abstract Edge creatEdge();

    // === Method to randomly generate a graph structure ===
    public void makeGraph() {
        Random rand = new Random();

        // Step 1: Ensure connectivity by linking vertices sequentially
        for (int i = 0; i < verticesNo - 1 && edgeNo < AllEdgesNo; i++) {
            int randomWeight = rand.nextInt(50) + 1;
            addEdge(i, i + 1, randomWeight);
        }

        // Step 2: Add remaining edges randomly without duplicates
        int remainingEdges = AllEdgesNo - (verticesNo - 1);
        Set<String> usedEdges = new HashSet<>();

        while (remainingEdges > 0 && edgeNo < AllEdgesNo) {
            int from = rand.nextInt(verticesNo);
            int to = rand.nextInt(verticesNo);

            if (from != to && !usedEdges.contains(from + ":" + to)) {
                int weight = rand.nextInt(50) + 1;
                addEdge(from, to, weight);
                usedEdges.add(from + ":" + to);
                add_Label(from);
                add_Label(to);

                remainingEdges--;
            }
        }
    }

    // === Method to read a graph from a file ===
    public void readGraphFromFile(File fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(fileName);

        // Step 1: Determine if the graph is directed or undirected
        String graphType = scanner.nextLine().trim();
        if (graphType.startsWith("diagraph")) {
            this.isDigraph = Integer.parseInt(graphType.split("\\s+")[1]) == 1;
        } else {
            throw new IllegalArgumentException("Invalid graph format. First line must specify 'diagraph 0' or 'diagraph 1'.");
        }

        // Step 2: Read the number of vertices and edges
        String[] graphInfo = scanner.nextLine().trim().split("\\s+");
        if (graphInfo.length < 2) {
            throw new IllegalArgumentException("Invalid graph format. Second line must specify the number of vertices and edges.");
        }
        this.verticesNo = Integer.parseInt(graphInfo[0]); // Number of vertices
        int edgeCount = Integer.parseInt(graphInfo[1]);   // Number of edges
        vertices = new Vertex[verticesNo];

        // Step 3: Read the edge data
        for (int i = 0; i < edgeCount; i++) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("Insufficient edge data in the input file.");
            }
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                i--; // Skip empty lines
                continue;
            }

            String[] parts = line.split("\\s+");
            if (parts.length < 3) {
                throw new IllegalArgumentException("Invalid edge data format. Each edge must specify source, destination, and weight.");
            }

            int fromIndex = parts[0].charAt(0) - 'A';
            int toIndex = parts[1].charAt(0) - 'A';
            int weight = Integer.parseInt(parts[2]);

            addEdge(fromIndex, toIndex, weight);
        }

        scanner.close();
    }

    // === Method to add an edge between two vertices ===
    public Edge addEdge(int from, int to, int weight) {
        // Create source vertex if not already created
        if (vertices[from] == null) {
            vertices[from] = creatVertex(from);
        }

        // Create target vertex if not already created
        if (vertices[to] == null) {
            vertices[to] = creatVertex(to);
        }

        // Create and link the edge
        Edge edge = creatEdge(vertices[from], vertices[to], weight);
        vertices[from].adjList.add(edge);

        // If the graph is undirected, add the reverse edge as well
        if (!isDigraph) {
            Edge reverseEdge = creatEdge(vertices[to], vertices[from], weight);
            vertices[to].adjList.add(reverseEdge);
        }

        add_Label(from);
        add_Label(to);
        edgeNo++;
        return edge;
    }

    // === Method to display the graph as an adjacency list ===
    public void PrintGraph() {
        System.out.println("Adjacency List:");

        for (int i = 0; i < verticesNo; i++) {
            Vertex vertex = vertices[i];
            int totalWeight = 0;

            System.out.print(vertex.displyInfo() + " -> ");
            for (Edge edge : vertex.adjList) {
                System.out.print(vertices[edge.target.ID].displyInfo() + " ");
                totalWeight += edge.getWeight();
            }

            System.out.println("--- route length: " + totalWeight);
        }
    }

    // === Label Helpers ===

    // Add label using character (from file)
    public void add_Label(char label) {
        vertices[label - 'A'].label = label;
    }

    // Auto-generate label if not already set
    public void add_Label(int index) {
        if (vertices[index].label == 0) {
            vertices[index].label = (char) (index + 'A');
        }
    }

    // === Getters & Setters ===
    public int getVerticesNo() {
        return verticesNo;
    }

    public void setVerticesNo(int verticesNo) {
        this.verticesNo = verticesNo;
    }

    public int getEdgeNo() {
        return edgeNo;
    }

    public void setEdgeNo(int edgeNo) {
        this.edgeNo = edgeNo;
    }

    public boolean isDigraph() {
        return isDigraph;
    }

    public void setDigraph(boolean isDigraph) {
        this.isDigraph = isDigraph;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public int getAllEdgesNo() {
        return AllEdgesNo;
    }

    public void setAllEdgesNo(int allEdgesNo) {
        AllEdgesNo = allEdgesNo;
    }
}