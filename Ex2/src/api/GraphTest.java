package api;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void getEdges() {
    }

    @Test
    void getNodes() {
    }

    @Test
    void getNode_count() {
    }

    @Test
    void getEdge_count() {
    }

    @Test
    void getMc() {
    }

    @org.junit.jupiter.api.Test
    void getNode() {
        Graph graph = new Graph();
        Location l1 = new Location(3,4,5);
        NodeData node1 = new Node(1,1, l1);
        Location l2 = new Location(0,1,2);
        NodeData node2 = new Node(3,2, l2);
        graph.addNode(node1);
        assertEquals(graph.getNode(node1.getKey()), node1);
        assertNotEquals(graph.getNode(1).getKey(), 2);
    }

    @org.junit.jupiter.api.Test
    void getEdge() {
        Graph graph = new Graph();
        EdgeData edge = new Edge(1, 3,1.8);
        int key1 = 1;
        Location l1 = new Location(3,4,5);
        NodeData node1 = new Node(key1,1,  l1);
        int key2 = 3;
        Location l2 = new Location(0,1,2);
        NodeData node2 = new Node(key2,1, l2);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.connect(1, 3, 1.8);
        assertEquals(graph.getEdge(edge.getSrc(), edge.getDest()).getInfo(), edge.getInfo());//need to check if we need to have the same address or only the same value
        assertNotEquals(graph.getEdge(edge.getSrc(), edge.getDest()).getWeight(), 1.81);
    }

    @org.junit.jupiter.api.Test
    void addNode() {
        Graph graph = new Graph();
        int key1 = 1;
        Location l1 = new Location(3,4,5);
        NodeData node1 = new Node(key1,1, l1);
        int key2 = 3;
        Location l2 = new Location(0,1,2);
        NodeData node2 = new Node(key2,2, l2);
        graph.addNode(node1);
        assertEquals(graph.getNode(node1.getKey()), node1);
        assertEquals(graph.getNode(node2.getKey()), null);
        assertNotEquals(graph.getNode(node2.getKey()), node2);
    }

    @org.junit.jupiter.api.Test
    void connect() {
        Graph graph = new Graph();
        EdgeData edge = new Edge(1, 3,1.8);
        int key1 = 1;
        Location l1 = new Location(3,4,5);
        NodeData node1 = new Node(1,1, l1);
        int key2 = 3;
        Location l2 = new Location(0,1,2);
        NodeData node2 = new Node(3,2,  l2);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.connect(1, 3, 1.8);
//        assertNotEquals(graph.getEdge(edge.getSrc(), edge.getDest()).getInfo(), edge.getInfo());//need to check if we need to have the same address or only the same value
        assertEquals(graph.getEdge(edge.getSrc(), edge.getDest()).getInfo(), edge.getInfo());
    }

    @org.junit.jupiter.api.Test
    void nodeIter() {
    }

    @org.junit.jupiter.api.Test
    void edgeIter() {
    }

    @org.junit.jupiter.api.Test
    void testEdgeIter() {
    }

    @org.junit.jupiter.api.Test
    void removeNode() {
        Graph graph = new Graph();
        for(int j=1;j<5;j++) {
            graph.connect(0, j, 0);
        }
        graph.removeNode(0);
        assertEquals(0, graph.edgeSize());
        assertEquals(4, graph.nodeSize());
        int key1 = 1;
        Location l1 = new Location(3,4,5);
        NodeData node1 = new Node(key1,1, l1);
        int key2 = 3;
        Location l2 = new Location(0,1,2);
        NodeData node2 = new Node(key2,2, l2);
        int key3 = 4;
        Location l3 = new Location(0,1,2);
        NodeData node3 = new Node(key3,3, l3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.connect(1, 3, 1.8);
        graph.connect(1, 4, 2);
//        EdgeData edge2 = new Edge(1, 3,1.8);
//        EdgeData edge3 = new Edge(1, 3,1.8);
//        graph.removeNode(node1.getKey());
//        assertEquals(graph.edgeSize(), 0);
//        assertTrue(graph.getEdge(1, 3) == null);
//        assertNull(graph.getEdge(1,3));
    }

    @org.junit.jupiter.api.Test
    void removeEdge() {
        Graph graph = new Graph();
        int key1 = 1;
        Location l1 = new Location(3,4,5);
        NodeData node1 = new Node(key1,1, l1);
        int key2 = 3;
        Location l2 = new Location(0,1,2);
        NodeData node2 = new Node(key2,2, l2);
        int key3 = 4;
        Location l3 = new Location(0,1,2);
        NodeData node3 = new Node(key3,3, l3);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
//        EdgeData edge1 = new Edge(1, 3,1.8);
//        EdgeData edge2 = new Edge(1, 4,1.8);
        graph.connect(1, 3, 1.8);
        graph.connect(1, 4, 1.7);
        graph.removeEdge(1, 4);
        assertNotEquals(graph.getEdge(1,3), null);
//        assertEquals(graph.getEdge(1,4), null);
    }

    @org.junit.jupiter.api.Test
    void nodeSize() {
    }

    @org.junit.jupiter.api.Test
    void edgeSize() {
    }

    @org.junit.jupiter.api.Test
    void getMC() {
    }
}