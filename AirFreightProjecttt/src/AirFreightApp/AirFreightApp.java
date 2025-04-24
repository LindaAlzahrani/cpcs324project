package AirFreightApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import GraphFramework.*;

public class AirFreightApp {

    public static void main(String[] args) throws FileNotFoundException {

        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Variables for tracking execution time
        long startTime, endTime;

        // Create an empty graph instance
        Graph graph = new AFRouteMap();

        // Welcome message
        displayIntroMessage();

        // Prompt user for input type
        int option = promptGraphInputType(scanner);

        boolean readFromFile = false;

        // Handle graph input based on user choice
        if (option == 1) { // Load graph from file
            graph = new AFRouteMap();
            File inputFile = new File("Graph1.txt");
            graph.readGraphFromFile(inputFile);
            readFromFile = true;
        } else if (option == 2) { // Generate random graph
            graph = handleRandomGraphInput(scanner);
        } else { // Invalid input
            System.out.println("Invalid selection. Please restart the application.");
            System.exit(1);
        }

        // Run Dijkstra-based shortest path algorithm
        System.out.println("\n================ Running Dijkstra Algorithm ===================\n");
        DBAllSourceSPAlg dijkstraAll = new DBAllSourceSPAlg(graph);

        startTime = System.currentTimeMillis();
        dijkstraAll.computeDijkstraBasedSPAlg(readFromFile);
        endTime = System.currentTimeMillis();

        // Display execution time
        System.out.printf("\nExecution Time: %.1f ms\n", (endTime - startTime) * 1.0);
        System.out.println("===============================================================\n");

        // Thank the user
        System.out.println("             Thank you for using the system ♥                  ");
        System.out.println("\n===============================================================\n");

        // Close scanner
        scanner.close();
    }

    // Method to display introduction message
    private static void displayIntroMessage() {
        System.out.println("===============================================================");
        System.out.println("        Air Freight Optimization - Shortest Path System        ");
        System.out.println("===============================================================");
        System.out.println("\nThis system calculates the shortest routes using:");
        System.out.println("1) Dijkstra for a specific source");
        System.out.println("2) Dijkstra-based all-source path algorithm");
        System.out.println("---------------------------------------------------------------");
    }

    // Method to prompt user for graph input type
    private static int promptGraphInputType(Scanner scanner) {
        System.out.println("\nSelect Graph Input Type:");
        System.out.println("1 - Load Graph from File");
        System.out.println("2 - Generate Random Graph");
        System.out.print("> Your choice: ");
        return scanner.nextInt();
    }

    // Method to handle random graph input
    private static Graph handleRandomGraphInput(Scanner scanner) {
        System.out.println("\nAvailable Test Cases:");
        System.out.println("1: n = 2000 | m = 10000");
        System.out.println("2: n = 3000 | m = 15000");
        System.out.println("3: n = 4000 | m = 20000");
        System.out.println("4: n = 5000 | m = 25000");
        System.out.println("5: n = 6000 | m = 30000");
        System.out.print("> Select test case: ");
        int test = scanner.nextInt();

        System.out.print("Do you want a Directed Graph? (Y/N): ");
        boolean directed = scanner.next().equalsIgnoreCase("Y");

        int nodes = 0, edges = 0;


        // Map selected test case to values
        if (test == 1) {
            nodes = 2000; edges = 10000;
        } else if (test == 2) {
            nodes = 3000; edges = 15000;
        } else if (test == 3) {
            nodes = 4000; edges = 20000;
        } else if (test == 4) {
            nodes = 5000; edges = 25000;
        } else if (test == 5) {
            nodes = 6000; edges = 30000;
        } else {
            // Invalid case entered
            System.out.println("Invalid test case selected.");
            System.exit(1); // exit program
        }

        Graph graph = new AFRouteMap(nodes, edges, directed);
        graph.makeGraph();
        return graph;
    }
}