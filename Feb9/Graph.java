package Feb9;

import java.util.HashMap;
import java.util.HashSet;

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
                out += " " + connection;
            }
            out += "\n";
        }
        return out;
    }
}
