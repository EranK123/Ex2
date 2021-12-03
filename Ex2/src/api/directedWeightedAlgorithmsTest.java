package api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class directedWeightedAlgorithmsTest {



    @Test
    void init() {
        Graph graph = new Graph();
        int key1 = 1;
//        Location l1 = new Location(3,4,5);
//        NodeData node1 = new Node(key1, l1);
//        int key2 = 3;
//        Location l2 = new Location(0,1,2);
//        NodeData node2 = new Node(key2, l2);
//        int key3 = 4;
//        Location l3 = new Location(0,1,2);
//        NodeData node3 = new Node(key3, l3);
//        graph.addNode(node1);
//        graph.addNode(node2);
//        graph.addNode(node3);
        graph.connect(1, 3, 1.8);
        graph.connect(1, 4, 2);
    }

    @Test
    void getGraph() {
    }

    @Test
    void copy() {
    }

    @Test
    void isConnected() {
        int key1 = 1;
        Location l1 = new Location(3,4,5);
//        NodeData node1 = new Node(key1, l1);
//        int key2 = 3;
//        Location l2 = new Location(0,1,2);
//        NodeData node2 = new Node(key2, l2);
//        int key3 = 4;
//        Location l3 = new Location(0,1,2);
//        NodeData node3 = new Node(key3, l3);
//        int key4 = 2;
//        NodeData node4 = new Node(key4, l3);
        Graph graph1 = new Graph();
        Graph graph2 = new Graph();
//        graph1.addNode(node1);
//        graph1.addNode(node2);
//        graph1.addNode(node3);
//        graph1.connect(1, 3, 1.8);
//        graph1.connect(1, 4, 2);
//        directedWeightedAlgorithms graphAlgo1 = new directedWeightedAlgorithms(graph1);
//        boolean b1 = graphAlgo1.isConnected();
//        graph2.addNode(node1);
//        graph2.addNode(node2);
//        graph2.addNode(node4);
        graph2.connect(1, 3, 1.8);
//        graph2.connect(1, 2, 2);
        directedWeightedAlgorithms graphAlgo2 = new directedWeightedAlgorithms(graph2);
        boolean b2 = graphAlgo2.isConnected();
//        assertTrue(b1);
        assertFalse(b2);
    }

    @Test
    void shortestPathDist() {
        Graph g = new Graph();
        directedWeightedAlgorithms g_algo = new directedWeightedAlgorithms(g);
        Location l = new Location(1,2,3);
        Location l1 = new Location(3,5,3);
        Location l2 = new Location(5,4,8);
        Location l3 = new Location(6,1,8);
        Node n1 = new Node(0,7,l);
        Node n2 = new Node(1,1,l1);
        Node n3 = new Node(2,2,l2);
        Node n4 = new Node(3,32,l3);
        g_algo.getGraph().addNode(n1);
        g_algo.getGraph().addNode(n2);
        g_algo.getGraph().addNode(n3);
        g_algo.getGraph().addNode(n4);
        g_algo.getGraph().connect(0,1,1);
        g_algo.getGraph().connect(3,0,3);
        g_algo.getGraph().connect(0,2,2);
        g_algo.getGraph().connect(1,2,4);
        g_algo.getGraph().connect(2,3,1);
        assertEquals(g_algo.shortestPathDist(n1.getKey(), n2.getKey()), 1);
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
    }

    @Test
    void tsp() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}