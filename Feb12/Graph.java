package Feb12;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph<E> {
    private MyHashMap<E, MyHashSet<E>> graph;

    public Graph() {
        this.graph = new MyHashMap<>();
    }

    public void add(E data) {
        graph.put(data, new MyHashSet<E>());
    }

    public void makeEdge(E node1, E node2) {
        graph.get(node1).add(node2);
        graph.get(node2).add(node1);
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
            for (E connection : graph.get(node).toDLList()) {
                out += " " + connection.toString();
            }
            out += "\n";
        }
        return out;
    }

    public E BFS(E start, E searchValue) {
        MyHashSet<E> visited = new MyHashSet<>();
        Queue<E> toVisit = new LinkedList<>();
        toVisit.add(start);
        while (!toVisit.isEmpty()) {
            E top = toVisit.poll();
            if (visited.contains(top)) {
                continue;
            }
            if (top.equals(searchValue)) {
                return top;
            }
            visited.add(top);
            for (E nodeToVisit : graph.get(top).toDLList()) {
                toVisit.add(nodeToVisit);
            }
        }
        return null;
    }

    public void DFS(E start, E searchValue) {
        MyHashSet<E> visited = new MyHashSet<>();
        Stack<E> toVisit = new Stack<>();
        toVisit.add(start);
        while (!toVisit.isEmpty()) {
            E top = toVisit.pop();
            if (visited.contains(top)) {
                continue;
            }
            if (top.equals(searchValue)) {
                break;
            }
            visited.add(top);
            for (E nodeToVisit : graph.get(top).toDLList()) {
                toVisit.add(nodeToVisit);
            }
        }
    }

    public void DFSDumb(E start, E searchValue) {
        MyHashSet<E> visited = new MyHashSet<E>();
        DumbFS(start, searchValue, visited);
    }

    private boolean DumbFS(E current, E searchValue, MyHashSet<E> visited) {
        visited.add(current);
        if (current.equals(searchValue)) {
            return true;
        }
        var toVisit = graph.get(current);
        for (E node : toVisit.toDLList()) {
            if (visited.contains(node)) {
                continue;
            }
            if (DumbFS(node, searchValue, visited)) {
                return true;
            }
        }
        return false;
    }

    public E getMatching(E searchMatch) {
        return graph.keySet().get(searchMatch.hashCode());
    }

    public MyHashSet<E> getConnections(E element) {
        return graph.get(element);
    }

    public MyDLList<E> getNodes() {
        return graph.keySet().toDLList();
    }
}
