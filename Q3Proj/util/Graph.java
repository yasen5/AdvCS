package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Graph<E extends LocationInterface> {
    public record WeightedLink<E>(E connectedNode, double weight) implements Comparable<WeightedLink<E>> {
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
            return (int) (weight - other.weight);
        }
    }

    private record NodeProperties<E>(double lowestCost, E previousNode) {
    }

    private MyHashMap<E, MyHashSet<WeightedLink<E>>> graph;
    private E startNode = null;

    public Graph() {
        this.graph = new MyHashMap<>();
    }

    public void add(E data) {
        if (startNode == null) {
            startNode = data;
        }
        graph.put(data, new MyHashSet<WeightedLink<E>>());
    }

    public void makeEdge(E node1, E node2, int weight) {
        graph.get(node1).add(new WeightedLink<E>(node2, weight));
        graph.get(node2).add(new WeightedLink<E>(node1, weight));
    }

    public void makeEdge(int hash1, int hash2) {
        E location1 = graph.keySet().getFullEntry(hash1);
        E location2 = graph.keySet().getFullEntry(hash2);
        double weight = location1.GetDistance(location2);
        graph.get(location1).add(new WeightedLink<E>(location2, weight));
        graph.get(location2).add(new WeightedLink<E>(location1, weight));
    }

    public void remove(E data) {
        for (E node : graph.keySet().toDLList()) {
            if (startNode.equals(data)) {
                startNode = node;
            }
            graph.get(node).remove(data);
        }
        if (startNode.equals(data)) {
            startNode = null;
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

    public double getWeight(E elem1, E elem2) {
        return graph.get(elem1).getFullEntry(elem2.hashCode()).weight;
    }

    public MyDLList<E> shortestPath(E start, E end) {
        MyHashMap<E, NodeProperties<E>> table = new MyHashMap<>();
        MinHeap<WeightedLink<E>> toVisit = new MinHeap<>();
        table.put(start, new NodeProperties<E>(0, null));
        toVisit.insert(new WeightedLink<E>(start, 0));
        while (toVisit.size() != 0) {
            E visiting = toVisit.pop().connectedNode;
            if (visiting.equals(end)) {
                MyDLList<E> shortestPath = new MyDLList<>();
                shortestPath.add(visiting);
                NodeProperties<E> currNode = table.get(visiting);
                System.out.println("Total distance: " + currNode.lowestCost);
                while (currNode.previousNode != null) {
                    shortestPath.add(currNode.previousNode);
                    currNode = table.get(currNode.previousNode);
                }
                return shortestPath;
            }
            for (WeightedLink<E> link : graph.get(visiting).toDLList()) {
                double cost = table.get(visiting).lowestCost + link.weight;
                NodeProperties<E> node = table.get(link.connectedNode);
                if (node == null || node.lowestCost > cost) {
                    table.put(link.connectedNode, new NodeProperties<E>(cost, visiting));
                    toVisit.insert(link);
                }
            }
        }
        return null;
    }

    public void drawGraph(Graphics g) {
        if (startNode == null) {
            return;
        }
        MyHashSet<E> visited = new MyHashSet<>();
        MinHeap<WeightedLink<E>> toVisit = new MinHeap<>();
        toVisit.insert(new WeightedLink<E>(startNode, 0));
        g.setColor(Color.RED);
        while (toVisit.size() != 0) {
            E top = toVisit.pop().connectedNode;
            visited.add(top);
            for (WeightedLink<E> nodeToVisit : graph.get(top).toDLList()) {
                if (visited.contains(nodeToVisit.connectedNode)) {
                    continue;
                }
                g.drawLine(top.x(), top.y(), nodeToVisit.connectedNode.x(),
                        nodeToVisit.connectedNode.y());
                g.setFont(new Font("SansSerif", Font.PLAIN, 15));
                g.drawString(String.valueOf((int) nodeToVisit.weight()),
                        (top.x() + nodeToVisit.connectedNode().x()) / 2,
                        (top.y() + nodeToVisit.connectedNode().y()) / 2);
                toVisit.insert(nodeToVisit);
            }
        }
    }

    public E getFullEntry(int hashCode) {
        return graph.keySet().getFullEntry(hashCode);
    }

    public MyHashSet<WeightedLink<E>> getConnectedNodes(E node) {
        return graph.get(node);
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
