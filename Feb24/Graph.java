package Feb24;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import Feb12.MyDLList;
import Feb12.MyHashMap;
import Feb12.MyHashSet;

public class Graph<E> {
    private record WeightedLink<E>(E connectedNode, int weight) implements Comparable<WeightedLink<E>> {
        @Override
        public int hashCode() {
            return connectedNode.hashCode();
        }

        @Override
        public String toString() {
            return connectedNode.toString() + ":" + weight;
        }

        @Override
        public int compareTo(WeightedLink<E> other) {
            return weight - other.weight;
        }
    }

    private record NodeProperties<E>(int lowestCost, E previousNode) {
    }

    private MyHashMap<E, MyHashSet<WeightedLink<E>>> graph;

    public Graph() {
        this.graph = new MyHashMap<>();
    }

    public void add(E data) {
        graph.put(data, new MyHashSet<WeightedLink<E>>());
    }

    public void makeEdge(E node1, E node2, int weight) {
        graph.get(node1).add(new WeightedLink<E>(node2, weight));
        graph.get(node2).add(new WeightedLink<E>(node1, weight));
    }

    public void remove(E data) {
        for (E node : graph.keySet().toDLList()) {
            graph.get(node).remove(data);
        }
        graph.remove(data);
    }

    @Override
    public String toString() {
        String out = "";
        for (E node : graph.keySet().toDLList()) {
            out += node + " ->";
            for (WeightedLink<E> connection : graph.get(node).toDLList()) {
                out += " " + connection.toString();
            }
            out += "\n";
        }
        return out;
    }

    public int getWeight(E elem1, E elem2) {
        return graph.get(elem1).get(elem2.hashCode()).weight;
    }

    public MyDLList<E> shortestPath(E start, E end) {
        MyHashMap<E, NodeProperties<E>> table = new MyHashMap<>();
        MinHeap<WeightedLink<E>> toVisit = new MinHeap<>();
        toVisit.insert(new WeightedLink<E>(start, 0));
        while (toVisit.size() != 0) {
            E visiting = toVisit.pop().connectedNode;
            if (visiting.equals(end)) {
                MyDLList<E> shortestPath = new MyDLList<>();
                shortestPath.add(visiting);
                NodeProperties<E> currNode = table.get(visiting);
                while (currNode != null) {
                    shortestPath.add(currNode.previousNode);
                    currNode = table.get(currNode.previousNode);
                }
                return shortestPath;
            }
            for (WeightedLink<E> link : graph.get(visiting).toDLList()) {
                int cost = table.get(visiting).lowestCost + link.weight;
                NodeProperties<E> node = table.get(link.connectedNode);
                if (node == null || node.lowestCost > cost) {
                    table.put(link.connectedNode, new NodeProperties<E>(cost, visiting));
                }
            }
        }
        return null;
    }

    // public E BFS(E start, E searchValue) {
    // MyHashSet<E> visited = new MyHashSet<>();
    // Queue<WeightedLink<E>> toVisit = new LinkedList<>();
    // toVisit.add(start);
    // while (!toVisit.isEmpty()) {
    // E top = toVisit.poll();
    // if (visited.contains(top)) {
    // continue;
    // }
    // if (top.equals(searchValue)) {
    // return top;
    // }
    // visited.add(top);
    // for (WeightedLink<E> nodeToVisit : graph.get(top).toDLList()) {
    // toVisit.add(nodeToVisit);
    // }
    // }
    // return null;
    // }

    // public void DFS(E start, E searchValue) {
    // MyHashSet<E> visited = new MyHashSet<>();
    // Stack<E> toVisit = new Stack<>();
    // toVisit.add(start);
    // while (!toVisit.isEmpty()) {
    // E top = toVisit.pop();
    // if (visited.contains(top)) {
    // continue;
    // }
    // if (top.equals(searchValue)) {
    // break;
    // }
    // visited.add(top);
    // for (E nodeToVisit : graph.get(top).toDLList()) {
    // toVisit.add(nodeToVisit);
    // }
    // }
    // }

    // public void DFSDumb(E start, E searchValue) {
    // MyHashSet<E> visited = new MyHashSet<E>();
    // DumbFS(start, searchValue, visited);
    // }

    // private boolean DumbFS(E current, E searchValue, MyHashSet<E> visited) {
    // visited.add(current);
    // if (current.equals(searchValue)) {
    // return true;
    // }
    // var toVisit = graph.get(current);
    // for (E node : toVisit.toDLList()) {
    // if (visited.contains(node)) {
    // continue;
    // }
    // if (DumbFS(node, searchValue, visited)) {
    // return true;
    // }
    // }
    // return false;
    // }

    // public E getMatching(E searchMatch) {
    // for (E element : graph.keySet().toDLList()) {
    // if (element.equals(searchMatch)) {
    // return element;
    // }
    // }
    // return null;
    // }

    // public MyHashSet<E> getConnections(E element) {
    // return graph.get(element);
    // }

    // public MyDLList<E> getNodes() {
    // return graph.keySet().toDLList();
    // }
}
