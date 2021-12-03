package api;

import java.util.*;

public class Graph implements DirectedWeightedGraph{

    private HashMap<Integer, HashMap<Integer, EdgeData>> Edges;
    private HashMap<Integer, NodeData> Nodes;
    private int node_count;
    private int edge_count;
    private int mc;

    public Graph(){
       this.Edges = new HashMap<>();
       this.Nodes = new HashMap<>();
       this.node_count = 0;
       this.edge_count = 0;
       this.mc = 0;
    }
    public Graph(HashMap Edges, HashMap Nodes, int node_count, int edge_count, int mc) {
        this.Edges = Edges;
        this.Nodes = Nodes;
        this.edge_count = edge_count;
        this.node_count = node_count;
        this.mc = mc;
    }

    public Graph(Graph g){
        this.edge_count = g.edge_count;
        this.node_count = g.node_count;
        this.mc = g.mc;
        this.Edges = new HashMap<>(g.Edges);
        this.Nodes = new HashMap<>(g.Nodes);

    }

//    public HashMap<Integer, HashMap<Integer, EdgeData>> getEdges() {
//        HashMap<Integer, HashMap<Integer, EdgeData>> edges = (HashMap<Integer, HashMap<Integer, EdgeData>>) this.Edges.clone();
//        return edges;
//    }

//    public HashMap<Integer, NodeData> getNodes() {
//        return Nodes;
//    }

    @Override
    public NodeData getNode(int key) {
        if (this.Nodes.get(key) == null){
            return null;
        }
        return this.Nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (this.Edges.get(src).get(dest) == null) {
            return null;
        }
        return this.Edges.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        if (Nodes.containsKey(n.getKey()))
            return;

        this.Nodes.put(n.getKey(), n);
        this.Edges.put(n.getKey(), new HashMap<>());
        node_count++;
        mc++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        EdgeData edge = new Edge(src, dest, w);
        if (this.getEdge(src, dest) == null && this.Nodes.containsKey(src) && this.Nodes.containsKey(dest)) {
            this.Edges.get(src).putIfAbsent(dest, edge);
            edge_count++;
            mc++;
        }else if (this.getEdge(src, dest) != null && this.getEdge(src, dest).getWeight() != w){
            this.Edges.get(src).putIfAbsent(dest, edge);
            mc++;
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        int temp = this.mc;
        Iterator<NodeData> iter = this.Nodes.values().iterator();
        if (temp != mc){
            throw new RuntimeException();
        }
        return iter;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        int temp = this.mc;
        Iterator<EdgeData> iter;
        if (temp != mc){
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        int temp = this.mc;
        Iterator<EdgeData> iter = this.Edges.get(node_id).values().iterator();
        if (temp != mc){
            throw new RuntimeException();
        }
        return iter;
    }

    @Override
    public NodeData removeNode(int key) {
        NodeData node;
        int edges_size = Edges.get(key).size();
        if (Nodes.get(key) == null) {
            return null;
        }else{
            node = new Node(Nodes.get(key));//need to check this
            Nodes.remove(key);
            Edges.remove(key);//need to check if this .clear() or remove?????????????????????????????????????????????????????????????????
            node_count--;
            edge_count = edge_count - edges_size;
            mc++;
        }
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData edge = new Edge((Edge) Edges.get(src).get(dest));
        if (Edges.get(src).get(dest) == null) {
            return null;
        }else
            Edges.remove(src).remove(dest);
            edge_count--;
            mc++;
            return edge;
    }

    @Override
    public int nodeSize() {
        return node_count;
    }

    @Override
    public int edgeSize() {
        return edge_count;
    }

    @Override
    public int getMC() {
        return mc;
    }
}
