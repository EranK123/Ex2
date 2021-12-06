package api;

import java.util.*;

public class Graph implements DirectedWeightedGraph {

    private HashMap<Integer, HashMap<Integer,EdgeData>> edges;
    private HashMap<Integer,NodeData> nodes;
    private int mc;
    private int nodeSize;
    private int edgeSize;

    public Graph() {
        this.edges = new HashMap<>();
        this.nodes = new HashMap<>();
        this.mc = 0;
        this.nodeSize = 0;
        this.edgeSize = 0;
    }

    public Graph(Graph g){
        this.edgeSize = g.edgeSize;
        this.nodeSize = g.nodeSize;
        this.mc = g.mc;
        this.edges = new HashMap<>(g.edges);
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
        if (edges.get(src).get(dest) == null) {
            return null;
        }
        return edges.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        this.nodes.put(n.getKey(), n);
        edges.put(n.getKey(), new HashMap<>());
        mc++;
        nodeSize++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        Edge newEdge = new Edge(src,dest,w);
        edges.get(src).put(dest,newEdge);
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
        Vector<EdgeData> vector = new Vector<>();
        for (int i = 0; i < nodeSize; i++) {
            for (int j = 0; j < edges.get(i).size(); j++) {
                vector.add((EdgeData) edges.get(i).values());
            }
        }
        return vector.iterator();
    }
//            return new Iterator<EdgeData>() {
//                private Iterator<HashMap<Integer,EdgeData>> iteratorMain = graph.values().iterator();
//                private Iterator<EdgeData> iteratorE = iteratorMain.next().values().iterator();
//                private int modeC = mc;
//
//
//                @Override
//                public boolean hasNext() {
//                    if (modeC != mc){
//                        throw new RuntimeException();
//                    }
//                    if (!iteratorE.hasNext()) {
//                        if (!iteratorMain.hasNext()) {
//                            return false;
//                        }
//                        iteratorE = iteratorMain.next().values().iterator();
//
//                    }
//                    return true;


//                @Override
//                public EdgeData next() {
//
//                }
//
//                @Override
//                public void remove(){
//
//                }
//            };



    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        int temp = mc;
        Iterator<EdgeData> iterator = this.edges.get(node_id).values().iterator();
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
        int removedSize = edges.get(key).size();
        this.nodes.remove(key);
        edges.remove(key);
        nodeSize--;
        edgeSize -= removedSize;
        mc++;
        return n;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Edge e = new Edge((Edge) edges.get(src).get(dest));
        if (edges.get(src).get(dest) == null){
            return null;
        }
        edges.get(src).remove(dest);
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
