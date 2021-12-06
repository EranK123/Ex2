package api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph g = new Graph();
    HashMap<Integer, NodeData> nodes = new HashMap<>();


    @Test
    void getNode() {
        Location l = new Location(1,2,3);
        Location l2 = new Location(1,2,3);
        Node n = new Node(7,l);
        Node n2 = new Node(4,l2);
        g.addNode(n);
        assertEquals(g.getNode(n.getKey()),n);
        g.addNode(n2);
        assertEquals(g.getNode(n2.getKey()),n2);
    }

    @Test
    void getEdge() {
        Edge e = new Edge(1,2,3);
        Location l = new Location(1,2,3);
        Location l2 = new Location(2,3,9);
        Node n1 = new Node(1,l);
        Node n2 = new Node(2,l2);
        g.addNode(n1);
        g.addNode(n2);
        g.connect(1,2,3);
        assertEquals(g.getEdge(1,2).getWeight(),e.getWeight()); //Not works for same address

    }

    @Test
    void addNode() {
        Location l = new Location(1,2,3);
        Node n = new Node(5,l);
        g.addNode(n);
        assertEquals(g.getNode(n.getKey()), n);
        assertEquals(g.nodeSize(),1);
    }

    @Test
    void connect() {
        Edge e = new Edge(1,2,3);
        Location l = new Location(1,2,3);
        Location l2 = new Location(2,3,9);
        Node n1 = new Node(1,l);
        Node n2 = new Node(2,l2);
        g.addNode(n1);
        g.addNode(n2);
        g.connect(1,2,3);
        assertEquals(g.getEdge(1,2).getWeight(),e.getWeight());
    }

    @Test
    void nodeIter() {
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
        List<NodeData> n = new ArrayList<>();
        for (int i = 0; i < g_algo.getGraph().nodeSize(); i++) {
            n.add(g_algo.getGraph().getNode(i));
        }
        Iterator<NodeData> it = g_algo.getGraph().nodeIter();
        int i = 0;
        while (it.hasNext()){
            assertEquals(it.next().getKey(), n.get(i).getKey());
            i++;
        }
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
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
        Iterator<EdgeData> it = g_algo.getGraph().edgeIter(n1.getKey());
        assertEquals(it.next(), g_algo.getGraph().getEdge(0,1));
    }

    @Test
    void removeNode() {
        Location l = new Location(1,2,3);
        Node n = new Node(1,l);
        Location l1 = new Location(3,5,3);
        Node n1 = new Node(2,l);
        Location l2 = new Location(5,4,8);
        Node n2 = new Node(3,l);
        g.addNode(n);
        g.addNode(n1);
        g.addNode(n2);
        g.connect(1,2,3);
        g.connect(1,3,4);
        g.removeNode(1);
        assertNull(g.getNode(1));
        assertEquals(g.edgeSize(),0);
        g.connect(3,2,1);
        assertEquals(g.edgeSize(),1);
    }

    @Test
    void removeEdge() {
        Location l = new Location(1,2,3);
        Node n = new Node(1,l);
        Location l1 = new Location(3,5,3);
        Node n1 = new Node(2,l);
        Location l2 = new Location(5,4,8);
        Node n2 = new Node(3,l);
        g.addNode(n);
        g.addNode(n1);
        g.addNode(n2);
        g.connect(1,2,3);
        g.connect(1,3,1);
        g.removeEdge(1,2);
        assertNull(g.getEdge(1, 2));
        assertEquals(g.edgeSize(),1);
    }

    @Test
    void nodeSize() {
        Location l = new Location(1,2,3);
        Node n = new Node(1,l);
        Location l1 = new Location(3,5,3);
        Node n1 = new Node(2,l);
        Location l2 = new Location(5,4,8);
        Node n2 = new Node(3,l);
        g.addNode(n);
        g.addNode(n1);
        g.addNode(n2);
        assertEquals(g.nodeSize(),3);
    }

    @Test
    void edgeSize() {
        Location l = new Location(1,2,3);
        Node n = new Node(1,l);
        Location l1 = new Location(3,5,3);
        Node n1 = new Node(2,l);
        Location l2 = new Location(5,4,8);
        Node n2 = new Node(3,l);
        g.addNode(n);
        g.addNode(n1);
        g.addNode(n2);
        g.connect(1,2,3);
        g.connect(1,3,1);
        assertEquals(g.edgeSize(),2);
    }

    @Test
    void getMC() {
        Location l = new Location(1,2,3);
        Node n = new Node(1,l);
        Location l1 = new Location(3,5,3);
        Node n1 = new Node(2,l);
        Location l2 = new Location(5,4,8);
        Node n2 = new Node(3,l);
        g.addNode(n);
        g.addNode(n1);
        g.addNode(n2);
        g.connect(1,2,3);
        g.connect(1,3,1);
        assertEquals(g.getMC(),5);
    }

}