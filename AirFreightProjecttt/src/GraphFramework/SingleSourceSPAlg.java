package GraphFramework;

import java.util.*;

// This class calculates the shortest path from one source to all other vertices using Dijkstra's Algorithm
public class SingleSourceSPAlg extends ShortestPathAlgorithm {

    // === Constructor ===
    public SingleSourceSPAlg(Graph graph) {
        // Call parent class constructor
        super(graph);
    }

    // This method applies Dijkstra’s Algorithm from a single source vertex
    public void computeDijkstraAlg(Vertex source, Boolean isFile) {

        // === Step 1: Initialization ===
        int vertexCount = graph.verticesNo;           // Total number of vertices in the graph
        int[] distances = new int[vertexCount];       // Array to track shortest distances from source
        int[] previous = new int[vertexCount];        // Array to track the previous vertex for path reconstruction
        boolean[] visited = new boolean[vertexCount]; // Array to track visited vertices

        // Initialize arrays
        Arrays.fill(distances, Integer.MAX_VALUE);    // Set all distances to "infinity"
        Arrays.fill(previous, -1);                    // No previous vertex initially
        Arrays.fill(visited, false);                  // No vertex visited initially

        distances[source.ID] = 0;                     // Distance to the source itself is 0

        // === Step 2: Priority Queue for Selecting the Next Shortest Path ===
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(vertexCount, Comparator.comparingInt(v -> distances[v.ID]));
        priorityQueue.add(source); // Add the source vertex to the queue

        // === Step 3: Main Dijkstra Algorithm Loop ===
        while (!priorityQueue.isEmpty()) {
            Vertex current = priorityQueue.poll();    // Get the vertex with the shortest distance
            if (visited[current.ID]) continue;       // Skip if the vertex is already visited
            visited[current.ID] = true;              // Mark the vertex as visited

            // Explore all adjacent edges of the current vertex
            for (Edge edge : current.adjList) {
                Vertex neighbor = edge.target;       // Get the neighboring vertex
                if (visited[neighbor.ID]) continue; // Skip if the neighbor is already visited

                // Calculate the new potential shortest distance
                int newDistance = distances[current.ID] + edge.weight;
                if (newDistance < distances[neighbor.ID]) {
                    distances[neighbor.ID] = newDistance; // Update the shortest distance
                    previous[neighbor.ID] = current.ID;   // Update the previous vertex

                    // Update the priority queue with the new shorter distance
                    priorityQueue.add(neighbor);
                }
            }
        }

        // === Step 4: Output the Result (if reading from a file) ===
        if (isFile) {
            System.out.println("The starting point location is " + source.displyInfo());
            System.out.println("The routes from location " + source.displyInfo() + " to the rest of the locations are:");

            for (int i = 0; i < vertexCount; i++) {
                // Skip if there's no path or if it's the source itself
                if (distances[i] == Integer.MAX_VALUE || i == source.ID) {
                    continue;
                }

                // Print the starting location
                System.out.print(source.displyInfo() + " ");

                // Reconstruct the path using the `previous` array
                LinkedList<Integer> path = new LinkedList<>();
                for (int at = i; at != -1; at = previous[at]) {
                    path.addFirst(at);
                }

                // Print the full path
                for (int j = 0; j < path.size(); j++) {
                    System.out.print(graph.vertices[path.get(j)].displyInfo());
                    if (j < path.size() - 1) {
                        System.out.print(" ");
                    }
                }

                // Print the route length
                System.out.println(" --- route length: " + distances[i]);
            }

            System.out.println("----------------------------------------------------------------------------------");
        }
    }
}