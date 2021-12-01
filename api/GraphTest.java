package api;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph g = new Graph();
    HashMap<Integer, NodeData> nodes = new HashMap<>();


    @Test
    void getNode() {
        Location l = new Location(1,2,3);
        Location l2 = new Location(1,2,3);
        Node n = new Node(7,1,l);
        Node n2 = new Node(4,1,l2);
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
        Node n1 = new Node(1,2,l);
        Node n2 = new Node(2,4,l2);
        g.addNode(n1);
        g.addNode(n2);
        g.connect(1,2,3);
        assertEquals(g.getEdge(1,2),e);

    }

    @Test
    void addNode() {
        Location l = new Location(1,2,3);
        Node n = new Node(5,3,l);
        g.addNode(n);
        assertEquals(g.getNode(n.getKey()), n);
    }

    @Test
    void connect() {
        Edge e = new Edge(1,2,3);
        Location l = new Location(1,2,3);
        Location l2 = new Location(2,3,9);
        Node n1 = new Node(1,2,l);
        Node n2 = new Node(2,4,l2);
        g.addNode(n1);
        g.addNode(n2);
        g.connect(1,2,3);
        assertEquals(g.getEdge(1,2),e);
    }

    @Test
    void nodeIter() {
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
        Location l = new Location(1,2,3);
        Node n = new Node(1,2,l);
        Location l1 = new Location(3,5,3);
        Node n1 = new Node(2,7,l);
        Location l2 = new Location(5,4,8);
        Node n2 = new Node(3,6,l);
        g.addNode(n);
        g.addNode(n1);
        g.addNode(n2);
        g.connect(1,2,3);
        g.connect(1,3,4);
        g.removeNode(1);
       // assertNull(g.getNode(1));
    }

    @Test
    void removeEdge() {
        Location l = new Location(1,2,3);
        Node n = new Node(1,2,l);
        Location l1 = new Location(3,5,3);
        Node n1 = new Node(2,7,l);
        Location l2 = new Location(5,4,8);
        Node n2 = new Node(3,6,l);
        g.addNode(n);
        g.addNode(n1);
        g.addNode(n2);
        g.connect(1,2,3);
        g.connect(1,3,1);
        g.removeEdge(1,2);
        assertNull(g.getEdge(1, 2));
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
}