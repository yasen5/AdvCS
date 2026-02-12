package Feb9;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        Scanner scanner = new Scanner(System.in);

        // Initialize the graph with the given data
        graph.add("Jose");
        graph.add("John");
        graph.add("Jane");
        graph.makeEdge("Jose", "John");
        graph.makeEdge("Jose", "Jane");
        graph.makeEdge("John", "Jane");

        boolean running = true;

        while (running) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a new Node (Vertex)");
            System.out.println("2. Add a new Edge");
            System.out.println("3. Remove a Node (Vertex)");
            System.out.println("4. View Graph");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the new node: ");
                    String newNode = scanner.nextLine();
                    graph.add(newNode);
                    System.out.println("Node added: " + newNode);
                    break;

                case 2:
                    System.out.print("Enter the first node: ");
                    String node1 = scanner.nextLine();
                    System.out.print("Enter the second node: ");
                    String node2 = scanner.nextLine();
                    if (graph != null) {
                        graph.makeEdge(node1, node2);
                        System.out.println("Edge added between " + node1 + " and " + node2);
                    } else {
                        System.out.println("One or both nodes do not exist.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the name of the node to remove: ");
                    String removeNode = scanner.nextLine();
                    graph.remove(removeNode);
                    System.out.println("Node removed: " + removeNode);
                    break;

                case 4:
                    System.out.println("Graph:");
                    System.out.println(graph);
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}