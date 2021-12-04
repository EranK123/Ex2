package api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgoTest {



    @Test
    void init() {
    }

    @Test
    void getGraph() {
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
        Graph graph = new Graph();
        GraphAlgo g = new GraphAlgo(graph);
        Location l = new Location(1,2,3);
        Location l1 = new Location(3,5,3);
        Location l2 = new Location(5,4,8);
        Location l3 = new Location(6,1,8);
        Node n1 = new Node(1,l);
        Node n2 = new Node(2,l1);
        Node n3 = new Node(3,l2);
        Node n4 = new Node(4,l3);
        g.getGraph().addNode(n1);
        g.getGraph().addNode(n2);
        g.getGraph().addNode(n3);
        g.getGraph().addNode(n4);
        g.getGraph().connect(1,2,1);
        g.getGraph().connect(4,1,3);
        g.getGraph().connect(1,3,2);
        g.getGraph().connect(2,3,4);
        g.getGraph().connect(3,4,1);
        boolean i = g.isConnected();
        assertTrue(i);
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
        assertEquals(g_algo.center(), g_algo.getGraph().getNode(3));
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