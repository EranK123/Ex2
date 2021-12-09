package api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgoTest {



    @Test
    void init() {
        Graph g = new Graph();
        GraphAlgo g_algo = new GraphAlgo(g);
        Graph graph = new Graph();
        Location l1 = new Location(1,2,3);
        Location l2 = new Location(3,5,3);
        Location l3 = new Location(5,4,8);
        Location l4 = new Location(6,1,8);
        Location l5 = new Location(2,7,1);
        Node n1 = new Node(0,l1);
        Node n2 = new Node(1,l2);
        Node n3 = new Node(2,l3);
        Node n4 = new Node(3,l4);
        Node n5 = new Node(4,l5);
        g_algo.getGraph().addNode(n1);
        g_algo.getGraph().addNode(n2);
        g_algo.getGraph().addNode(n3);
        g_algo.getGraph().addNode(n4);
        g_algo.getGraph().addNode(n5);
        g_algo.getGraph().connect(0,1,1);
        g_algo.getGraph().connect(3,0,3);
        g_algo.getGraph().connect(0,2,2);
        g_algo.getGraph().connect(1,3,4);
        g_algo.getGraph().connect(2,3,1);
        g_algo.getGraph().connect(2,3,5);
        g_algo.getGraph().connect(4,1,3);
        g_algo.getGraph().connect(3,4,2);
        g_algo.init(graph);
        assertEquals(g_algo.getGraph(), graph);
    }

    @Test
    void getGraph() {
        Graph g = new Graph();
        GraphAlgo g_algo = new GraphAlgo(g);
        Location l1 = new Location(1,2,3);
        Location l2 = new Location(3,5,3);
        Location l3 = new Location(5,4,8);
        Node n1 = new Node(0,l1);
        Node n2 = new Node(1,l2);
        Node n3 = new Node(2,l3);
        g_algo.getGraph().addNode(n1);
        g_algo.getGraph().addNode(n2);
        g_algo.getGraph().addNode(n3);
        g_algo.getGraph().connect(0,1,1);
        g_algo.getGraph().connect(2,0,3);
        g_algo.getGraph().connect(0,2,2);
        assertEquals(g_algo.getGraph().getNode(0),n1);
        assertEquals(g_algo.getGraph().getNode(1),n2);
        assertEquals(g_algo.getGraph().getEdge(0,1).getWeight(),new Edge(0,1,1).getWeight());
    }

    @Test
    void copy() {
        Graph g = new Graph();
        GraphAlgo g_algo = new GraphAlgo(g);
        Location l1 = new Location(1,2,3);
        Location l2 = new Location(3,5,3);
        Location l3 = new Location(5,4,8);
        Location l4 = new Location(6,1,8);
        Location l5 = new Location(2,7,1);
        Node n1 = new Node(0,l1);
        Node n2 = new Node(1,l2);
        Node n3 = new Node(2,l3);
        Node n4 = new Node(3,l4);
        Node n5 = new Node(4,l5);
        g_algo.getGraph().addNode(n1);
        g_algo.getGraph().addNode(n2);
        g_algo.getGraph().addNode(n3);
        g_algo.getGraph().addNode(n4);
        g_algo.getGraph().addNode(n5);
        g_algo.getGraph().connect(0,1,1);
        g_algo.getGraph().connect(3,0,3);
        g_algo.getGraph().connect(0,2,2);
        g_algo.getGraph().connect(1,3,4);
        g_algo.getGraph().connect(2,3,1);
        g_algo.getGraph().connect(2,3,5);
        g_algo.getGraph().connect(4,1,3);
        g_algo.getGraph().connect(3,4,2);
        Graph graph1 = (Graph) g_algo.copy();
        GraphAlgo copyG = new GraphAlgo(graph1);
        assertEquals(copyG.getGraph().getEdge(0,1).getWeight(),1);
        assertEquals(copyG.getGraph().getNode(3),n4);
    }

    @Test
    void isConnectedTest() {
        Graph g = new Graph();
        GraphAlgo g_algo = new GraphAlgo(g);
        Graph graph2 = new Graph();
        Location l1 = new Location(1,2,3);
        Location l2 = new Location(3,5,3);
        Location l3 = new Location(5,4,8);
        Location l4 = new Location(6,1,8);
        Location l5 = new Location(2,7,1);
        Node n1 = new Node(0,l1);
        Node n2 = new Node(1,l2);
        Node n3 = new Node(2,l3);
        Node n4 = new Node(3,l4);
        Node n5 = new Node(4,l5);
        g_algo.getGraph().addNode(n1);
        g_algo.getGraph().addNode(n2);
        g_algo.getGraph().addNode(n3);
        g_algo.getGraph().addNode(n4);
        g_algo.getGraph().addNode(n5);
        g_algo.getGraph().connect(0,1,1);
        g_algo.getGraph().connect(3,0,3);
        g_algo.getGraph().connect(0,2,2);
        g_algo.getGraph().connect(1,3,4);
        g_algo.getGraph().connect(2,3,1);
        g_algo.getGraph().connect(2,3,5);
        g_algo.getGraph().connect(4,1,3);
        g_algo.getGraph().connect(3,4,2);
        boolean b1 = g_algo.isConnected();
        assertTrue(b1);
        graph2.addNode(n1);
        graph2.addNode(n2);
        graph2.addNode(n3);
        graph2.connect(0, 1, 1.8);
        graph2.connect(0, 2, 2);
        GraphAlgo graphAlgo2 = new GraphAlgo(graph2);
        boolean b2 = graphAlgo2.isConnected();
        assertFalse(b2);
        Graph g3 = new Graph();
        GraphAlgo g_algo3 = new GraphAlgo(g3);
        g_algo3.load("1000Nodes.json");
        assertEquals(g_algo3.isConnected(),true);
    }

    @Test
    void shortestPathDistTest() {
        Graph g = new Graph();
        GraphAlgo g_algo = new GraphAlgo(g);
        Location l1 = new Location(1,2,3);
        Location l2 = new Location(3,5,3);
        Location l3 = new Location(5,4,8);
        Location l4 = new Location(6,1,8);
        Location l5 = new Location(2,7,1);
        Node n1 = new Node(0,l1);
        Node n2 = new Node(1,l2);
        Node n3 = new Node(2,l3);
        Node n4 = new Node(3,l4);
        Node n5 = new Node(4,l5);
        g_algo.getGraph().addNode(n1);
        g_algo.getGraph().addNode(n2);
        g_algo.getGraph().addNode(n3);
        g_algo.getGraph().addNode(n4);
        g_algo.getGraph().addNode(n5);
        g_algo.getGraph().connect(0,1,1);
        g_algo.getGraph().connect(3,0,3);
        g_algo.getGraph().connect(0,2,2);
        g_algo.getGraph().connect(1,3,4);
        g_algo.getGraph().connect(2,3,1);
        g_algo.getGraph().connect(2,3,5);
        g_algo.getGraph().connect(4,1,3);
        g_algo.getGraph().connect(3,4,2);
        assertEquals(g_algo.shortestPathDist(0, 3), 5);
        assertEquals(g_algo.shortestPathDist(0, 2), 2);
        assertEquals(g_algo.shortestPathDist(1, 2), 9);
        assertEquals(g_algo.shortestPathDist(2,3), 5);
        assertEquals(g_algo.shortestPathDist(0,4), 7);

    }

    @Test
    void shortestPathTest() {
        Graph g = new Graph();
        GraphAlgo g_algo = new GraphAlgo(g);
        Location l1 = new Location(1,2,3);
        Location l2 = new Location(3,5,3);
        Location l3 = new Location(5,4,8);
        Location l4 = new Location(6,1,8);
        Location l5 = new Location(2,7,1);
        Node n1 = new Node(0,l1);
        Node n2 = new Node(1,l2);
        Node n3 = new Node(2,l3);
        Node n4 = new Node(3,l4);
        Node n5 = new Node(4,l5);
        g_algo.getGraph().addNode(n1);
        g_algo.getGraph().addNode(n2);
        g_algo.getGraph().addNode(n3);
        g_algo.getGraph().addNode(n4);
        g_algo.getGraph().addNode(n5);
        g_algo.getGraph().connect(0,1,1);
        g_algo.getGraph().connect(3,0,3);
        g_algo.getGraph().connect(0,2,2);
        g_algo.getGraph().connect(1,3,4);
        g_algo.getGraph().connect(2,3,5);
        g_algo.getGraph().connect(4,1,3);
        g_algo.getGraph().connect(3,4,2);
        List<NodeData> list1 = new ArrayList<>();
        list1.add(n1);
        list1.add(n2);
        list1.add(n4);
        list1.add(n5);
        assertEquals(g_algo.shortestPath(0, 4), list1);
    }

    @Test
    void center() {
        Graph g2 = new Graph();
        Graph g = new Graph();
        GraphAlgo g_algo = new GraphAlgo(g2);
        GraphAlgo g_algo1 = new GraphAlgo(g);
        Location l1 = new Location(1,2,3);
        Location l2 = new Location(3,5,3);
        Location l3 = new Location(5,4,8);
        Location l4 = new Location(6,1,8);
        Location l5 = new Location(2,7,1);
        Node n1 = new Node(0,l1);
        Node n2 = new Node(1,l2);
        Node n3 = new Node(2,l3);
        Node n4 = new Node(3,l4);
        Node n5 = new Node(4,l5);
        g_algo.getGraph().addNode(n1);
        g_algo.getGraph().addNode(n2);
        g_algo.getGraph().addNode(n3);
        g_algo.getGraph().addNode(n4);
        g_algo.getGraph().addNode(n5);
        g_algo.getGraph().connect(0,1,1);
        g_algo.getGraph().connect(3,0,3);
        g_algo.getGraph().connect(0,2,2);
        g_algo.getGraph().connect(1,3,4);
        g_algo.getGraph().connect(2,3,5);
        g_algo.getGraph().connect(4,1,3);
        g_algo.getGraph().connect(3,4,2);
        assertEquals(g_algo.center(), g_algo.getGraph().getNode(3));
        g_algo1.load("G2.json");
        assertEquals(g_algo1.center().getKey(), 0);
    }

    @Test
    void tsp() {
        Graph g2 = new Graph();
        Graph g = new Graph();
        GraphAlgo g_algo = new GraphAlgo(g2);
        GraphAlgo g_algo1 = new GraphAlgo(g);
        Location l1 = new Location(1,2,3);
        Location l2 = new Location(3,5,3);
        Location l3 = new Location(5,4,8);
        Location l4 = new Location(6,1,8);
        Location l5 = new Location(2,7,1);
        Location l6 = new Location(100,7,1);
        Node n1 = new Node(0,l1);
        Node n2 = new Node(1,l2);
        Node n3 = new Node(2,l3);
        Node n4 = new Node(3,l4);
        Node n5 = new Node(4,l5);
        Node n6 = new Node(5,l6);
        Node n7 = new Node(6,l6);
        g_algo.getGraph().addNode(n1);
        g_algo.getGraph().addNode(n2);
        g_algo.getGraph().addNode(n3);
        g_algo.getGraph().addNode(n4);
        g_algo.getGraph().addNode(n5);
        g_algo.getGraph().addNode(n6);
        g_algo.getGraph().addNode(n7);
        g_algo.getGraph().connect(0,1,1);
        g_algo.getGraph().connect(3,0,3);
        g_algo.getGraph().connect(0,2,2);
        g_algo.getGraph().connect(1,3,4);
        g_algo.getGraph().connect(2,3,5);
        g_algo.getGraph().connect(4,1,3);
        g_algo.getGraph().connect(3,4,2);
        g_algo.getGraph().connect(5,6,2);
        List<NodeData> list = new ArrayList<>();
        list.add(n6);
        list.add(n5);
        List<NodeData> list2 = new ArrayList<>();
        assertEquals(g_algo.tsp(list), list2);

    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}