package Feb10;

import java.util.Scanner;

public class Runner {
    private static record Friend(char id, String name) {
        @Override
        public int hashCode() {
            return id - '0';
        }

        @Override
        public boolean equals(Object other) {
            Friend otherFriend = (Friend) other;
            return this.name.equals(otherFriend.name);
        }
    }

    public static void main(String[] args) {
        Graph<Friend> graph = new Graph<>();
        Scanner scanner = new Scanner(System.in);

        // Initialize the graph with the given data
        Friend john = new Friend('A', "John");
        Friend jen = new Friend('B', "Jen");
        Friend jose = new Friend('C', "Jose");
        Friend jay = new Friend('D', "Jay");
        Friend jason = new Friend('E', "Jason");
        Friend jackson = new Friend('F', "Jackson");
        Friend jonah = new Friend('G', "Jonah");
        graph.add(john);
        graph.add(jen);
        graph.add(jose);
        graph.add(jay);
        graph.add(jason);
        graph.add(jackson);
        graph.add(jonah);
        graph.makeEdge(john, jen);
        graph.makeEdge(john, jose);
        graph.makeEdge(jen, jose);
        graph.makeEdge(jen, jay);
        graph.makeEdge(jen, jason);
        graph.makeEdge(jose, jackson);
        graph.makeEdge(jose, jonah);

        System.out.print("Enter your search node: ");

        String name = scanner.next();

        graph.BFS(john, new Friend('x', name));

        scanner.close();
    }
}