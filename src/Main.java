import api.*;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        GraphAlgo g_algo = new GraphAlgo(g);
        Location l1 = new Location(1, 2, 3);
        Location l2 = new Location(3, 5, 3);
        Location l3 = new Location(5, 4, 8);
        Location l4 = new Location(6, 1, 8);
        Location l5 = new Location(2, 7, 1);
        Node n1 = new Node(0, l1);
        Node n2 = new Node(1, l2);
        Node n3 = new Node(2, l3);
        Node n4 = new Node(3, l4);
        Node n5 = new Node(4, l5);
        g_algo.getGraph().addNode(n1);
        g_algo.getGraph().addNode(n2);
        g_algo.getGraph().addNode(n3);
        g_algo.getGraph().addNode(n4);
        g_algo.getGraph().addNode(n5);
        g_algo.getGraph().connect(0, 1, 1);
        g_algo.getGraph().connect(3, 0, 3);
        g_algo.getGraph().connect(0, 2, 2);
        g_algo.getGraph().connect(1, 3, 4);
        g_algo.getGraph().connect(2, 3, 5);
        g_algo.getGraph().connect(4, 1, 3);
        g_algo.getGraph().connect(3, 4, 2);
        g_algo.save("SaveJsonFile.txt");
        g_algo.load("G1.json");
        NodeData t = g_algo.getGraph().getNode(0);
        System.out.println(t.getLocation().x());
    }
}
