package Feb24;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Graph<Character> graph = new Graph<>();
        Scanner sc = new Scanner(System.in);

        Character a = 'A';
        Character b = 'B';
        Character c = 'C';
        Character d = 'D';
        Character e = 'E';

        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);

        graph.makeEdge(a, b, 5);
        graph.makeEdge(b, c, 1);
        graph.makeEdge(a, c, 3);
        graph.makeEdge(b, d, 2);
        graph.makeEdge(b, e, 11);
        graph.makeEdge(c, e, 9);
        graph.makeEdge(d, e, 1);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. View Graph");
            System.out.println("2. Add New Node");
            System.out.println("3. Add Edge given 2 Nodes and a Weight");
            System.out.println("4. Get Weight given 2 adjacent Nodes");
            System.out.println("5. Quit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(graph);
                    break;

                case 2:
                    System.out.print("Enter node name: ");
                    Character node = sc.next().charAt(0);
                    graph.add(node);
                    break;

                case 3:
                    System.out.print("Enter first node: ");
                    Character node1 = sc.next().charAt(0);
                    System.out.print("Enter second node: ");
                    Character node2 = sc.next().charAt(0);
                    System.out.print("Enter weight: ");
                    int weight = sc.nextInt();
                    sc.nextLine();
                    graph.makeEdge(node1, node2, weight);
                    break;

                case 4:
                    System.out.print("Enter first node: ");
                    Character adjNode1 = sc.next().charAt(0);
                    System.out.print("Enter second node: ");
                    Character adjNode2 = sc.next().charAt(0);

                    System.out.println("Weight: " + graph.getWeight(adjNode1, adjNode2));
                    break;

                case 5:
                    return;
            }
        }
    }
}