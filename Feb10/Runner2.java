package Feb10;

import java.util.Scanner;

public class Runner2 {

    private static record Friend(char id, String name) {
        @Override
        public int hashCode() {
            return id - '0';
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Friend)) return false;
            Friend otherFriend = (Friend) other;
            return this.name.equals(otherFriend.name) || this.id == otherFriend.id;
        }

        @Override
        public String toString() {
            return "(" + id + ", " + name + ")";
        }
    }

    public static void main(String[] args) {

        Graph<Friend> graph = new Graph<>();
        Scanner scanner = new Scanner(System.in);

        // Create friends
        Friend A = new Friend('A', "John");
        Friend B = new Friend('B', "Jen");
        Friend C = new Friend('C', "Jose");
        Friend D = new Friend('D', "Jay");
        Friend E = new Friend('E', "Jason");
        Friend F = new Friend('F', "Jackson");
        Friend G = new Friend('G', "Jonah");
        Friend H = new Friend('H', "Jennifer");
        Friend I = new Friend('I', "Jane");
        Friend J = new Friend('J', "Jude");

        // Add nodes
        graph.add(A);
        graph.add(B);
        graph.add(C);
        graph.add(D);
        graph.add(E);
        graph.add(F);
        graph.add(G);
        graph.add(H);
        graph.add(I);
        graph.add(J);

        // Create edges
        graph.makeEdge(A, B);
        graph.makeEdge(A, C);
        graph.makeEdge(A, J);

        graph.makeEdge(B, D);
        graph.makeEdge(B, E);

        graph.makeEdge(D, F);
        graph.makeEdge(E, F);

        graph.makeEdge(F, G);

        graph.makeEdge(C, H);
        graph.makeEdge(H, I);
        graph.makeEdge(C, I);

        boolean running = true;

        while (running) {

            System.out.println("\n==== MENU ====");
            System.out.println("1. Remove Friend by ID");
            System.out.println("2. Remove Friend by Name");
            System.out.println("3. Search Friend (DFS) by ID");
            System.out.println("4. Search Friend (DFS) by Name");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter ID to remove: ");
                    char id = scanner.nextLine().toUpperCase().charAt(0);
                    Friend temp = new Friend(id, "");
                    graph.remove(temp);
                    System.out.println("Attempted removal by ID.");
                }

                case 2 -> {
                    System.out.print("Enter Name to remove: ");
                    String name = scanner.nextLine();
                    Friend temp = new Friend('x', name);
                    graph.remove(temp);
                    System.out.println("Attempted removal by Name.");
                }

                case 3 -> {
                    System.out.print("Enter ID to search: ");
                    char id = scanner.nextLine().toUpperCase().charAt(0);
                    Friend temp = new Friend(id, "");
                    System.out.println("Searching via DFS...");
                    graph.DFS(A, temp);
                }

                case 4 -> {
                    System.out.print("Enter Name to search: ");
                    String name = scanner.nextLine();
                    Friend temp = new Friend('x', name);
                    System.out.println("Searching via DFS...");
                    graph.DFS(A, temp);
                }

                case 5 -> {
                    running = false;
                    System.out.println("Exiting program.");
                }

                default -> System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
