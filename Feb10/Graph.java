package Feb10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph<E> {
    private HashMap<E, HashSet<E>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void add(E data) {
        graph.put(data, new HashSet<E>());
    }

    public void makeEdge(E node1, E node2) {
        graph.get(node1).add(node2);
        graph.get(node2).add(node1);
    }

    public void remove(E data) {
        for (E node : graph.keySet()) {
            graph.get(node).remove(data);
        }
        graph.remove(data);
    }

    @Override
    public String toString() {
        String out = "";
        for (E node : graph.keySet()) {
            out += node + " ->";
            for (E connection : graph.get(node)) {
                out += " " + connection.toString();
            }
            out += "\n";
        }
        return out;
    }

    public void BFS(E start, E searchValue) {
        HashSet<E> visited = new HashSet<>();
        Queue<E> toVisit = new LinkedList<>();
        toVisit.add(start);
        while (!toVisit.isEmpty()) {
            E top = toVisit.poll();
            if (visited.contains(top)) {
                continue;
            }
            System.out.println(top);
            if (top.equals(searchValue)) {
                break;
            }
            visited.add(top);
            for (E nodeToVisit : graph.get(top)) {
                toVisit.add(nodeToVisit);
            }
        }
    }

    public void DFS(E start, E searchValue) {
        HashSet<E> visited = new HashSet<>();
        Stack<E> toVisit = new Stack<>();
        toVisit.add(start);
        while (!toVisit.isEmpty()) {
            E top = toVisit.pop();
            if (visited.contains(top)) {
                continue;
            }
            System.out.println(top);
            if (top.equals(searchValue)) {
                break;
            }
            visited.add(top);
            for (E nodeToVisit : graph.get(top)) {
                toVisit.add(nodeToVisit);
            }
        }
    }

    public void DFSDumb(E start, E searchValue) {
        DumbFS(start, searchValue);
    }

    public boolean DumbFS(E current, E searchValue) {
        if (current.equals(searchValue)) {
            return true;
        }
        var toVisit = graph.get(current);
        for (E node : toVisit) {
            if (DumbFS(node, searchValue)) {
                continue;
            }
        }
        return false;
    }
}
