package api;

import java.util.*;

public class Graph implements DirectedWeightedGraph {

    /**
     * This class implements DirectedWeightedGraph which represent a directed weighted graph.
     */
    private HashMap<Integer, HashMap<Integer,EdgeData>> edges;
    private HashMap<Integer,NodeData> nodes;
    private int mc;
    private int nodeSize;
    private int edgeSize;

    /**
     * Graph init
     */
    public Graph() {
        this.edges = new HashMap<>();
        this.nodes = new HashMap<>();
        this.mc = 0;
        this.nodeSize = 0;
        this.edgeSize = 0;
    }

    /**
     * Copy constructor
     * @param g - other graph
     */
    public Graph(Graph g){
        this.edgeSize = g.edgeSize;
        this.nodeSize = g.nodeSize;
        this.mc = g.mc;
        this.edges = new HashMap<>(g.edges);
        this.nodes = new HashMap<>(g.nodes);

    }

    /**
     * @param key - the node_id
     * @return a given node's key (id)
     */
    @Override
    public NodeData getNode(int key) {
        if (nodes.get(key) == null) {
            return null;
        }
        return nodes.get(key);
    }

    /**
     * @param src - source node's id
     * @param dest - source node's destination
     * @return the edge connecting the two nodes
     */
    @Override
    public EdgeData getEdge(int src, int dest) {
        if (edges.get(src).get(dest) == null) {
            return null;
        }
        return edges.get(src).get(dest);
    }

    /**
     * This function will add a new node to the graph
     * @param n - new node
     */
    @Override
    public void addNode(NodeData n) {
        this.nodes.put(n.getKey(), n); // add to the nodes hashmap
        edges.put(n.getKey(), new HashMap<>()); // add to the edges hashmap
        mc++;
        nodeSize++;
    }

    /**
     * This function will add a new edge or 'connect' an edge between two nodes
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost between src-->dest.
     */
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
//        Vector<EdgeData> vector = new Vector<>();
//        for (int i = 0; i < nodeSize; i++) {
//            for (int j = 0; j < edges.get(i).size(); j++) {
//                vector.add((EdgeData) edges.get(i).values());
//            }
//        }
//        return vector.iterator();
//    }
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
        ArrayList<EdgeData> arrayList = new ArrayList<>();
        for (int node = 0; node < nodeSize; node++){
            Iterator<EdgeData> iterator = edgeIter(node);
            while (iterator.hasNext())
                arrayList.add(iterator.next());
        }
        return arrayList.iterator();
    }


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
