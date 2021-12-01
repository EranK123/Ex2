package api;

import org.w3c.dom.traversal.NodeFilter;

import java.util.*;

public class Graph implements DirectedWeightedGraph {

    private HashMap<Integer, HashMap<Integer,EdgeData>> graph;
    private HashMap<Integer,NodeData> nodes;
    private int mc;
    private int nodeSize;
    private int edgeSize;

    public Graph() {
        this.graph = new HashMap<>();
        this.nodes = new HashMap<>();
        this.mc = 0;
        this.nodeSize = 0;
        this.edgeSize = 0;
    }

    public Graph(Graph g){
        this.edgeSize = g.edgeSize;
        this.nodeSize = g.nodeSize;
        this.mc = g.mc;
        this.graph = new HashMap<>(g.graph);
        this.nodes = new HashMap<>(g.nodes);

    }
    @Override
    public NodeData getNode(int key) {
        if (nodes.get(key) == null) {
            return null;
        }
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (graph.get(src).get(dest) == null) {
            return null;
        }
        return graph.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        this.nodes.put(n.getKey(), n);
        graph.put(n.getKey(), new HashMap<>());
        mc++;
        nodeSize++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        Edge newEdge = new Edge(src,dest,w);
        graph.get(src).put(dest,newEdge);
        mc++;
        edgeSize++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        int temp = mc;
        Iterator<NodeData> iterator = this.nodes.values().iterator();
        if (temp != mc){
            throw new RuntimeException();
        }
       return iterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        int temp = mc;
        Iterator<EdgeData> iterator =  this.graph.get(0).values().iterator();
        if (temp != mc){
            throw new RuntimeException();
        }
        return iterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        int temp = mc;
        Iterator<EdgeData> iterator = this.graph.get(node_id).values().iterator();
        if (temp != mc){
            throw new RuntimeException();
        }
        return iterator;
    }

    @Override
    public NodeData removeNode(int key) {
        if (nodes.get(key) == null) {
            return null;
        }
        Node n = new Node((Node) nodes.get(key));
        int removedSize = graph.get(key).size();
        this.nodes.remove(key);
        graph.remove(key);
        nodeSize--;
        edgeSize -= removedSize;
        mc++;
        return n;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Edge e = new Edge((Edge) graph.get(src).get(dest));
        if (graph.get(src).get(dest) == null){
            return null;
        }
        graph.get(src).remove(dest);
        edgeSize--;
        mc++;
        return e;
    }

    @Override
    public int nodeSize() {
        return nodeSize;
    }

    @Override
    public int edgeSize() {
        return edgeSize;
    }

    @Override
    public int getMC() {
        return mc;
    }
}
