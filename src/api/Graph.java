package api;

import java.util.*;

public class Graph implements DirectedWeightedGraph {

    /**
     * This class implements DirectedWeightedGraph which represent a directed weighted graph.
     */
    private HashMap<Integer, HashMap<Integer, EdgeData>> edges;
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> edgesReverse;
    private int mc;
    private int nodeSize;
    private int edgeSize;

    /**
     * Graph init
     */
    public Graph() {
        this.edges = new HashMap<>();
        this.edgesReverse = new HashMap<>();
        this.nodes = new HashMap<>();
        this.mc = 0;
        this.nodeSize = 0;
        this.edgeSize = 0;
    }

    /**
     * Copy constructor
     *
     * @param g - other graph
     */
    public Graph(Graph g) {
        this.edgeSize = g.edgeSize;
        this.nodeSize = g.nodeSize;
        this.mc = g.mc;
        this.edges = new HashMap<>(g.edges);
        this.nodes = new HashMap<>(g.nodes);
        this.edgesReverse = new HashMap<>(g.edgesReverse);
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
     * @param src  - source node's id
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
     *
     * @param n - new node
     */
    @Override
    public void addNode(NodeData n) {//need to check what to do if i get a key that im already have
        if (this.nodes.containsKey(n.getKey())) {
            return;
        }
        this.nodes.put(n.getKey(), n); // add to the nodes hashmap
        edges.put(n.getKey(), new HashMap<>()); // add to the edges hashmap
        edgesReverse.put(n.getKey(), new HashMap<>());
        mc++;
        nodeSize++;
    }

    /**
     * This function will add a new edge or 'connect' an edge between two nodes
     *
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w    - positive weight representing the cost between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {

        if (!this.nodes.containsKey(src) && !this.nodes.containsKey(dest) && this.edges.get(src).get(dest) != null && this.edges.get(src).get(dest).getWeight() == w &&
                this.edgesReverse.get(dest).get(src) != null && this.edgesReverse.get(dest).get(src).getWeight() == w) {//if one of the nodes don't contain return or if the edge in.
            return;

        }
        if (this.edges.get(src).get(dest) != null && this.edges.get(src).get(dest).getWeight() != w && this.edgesReverse.get(dest).get(src) != null &&
                this.edgesReverse.get(dest).get(src).getWeight() != w) {//if the edge already in but not the same weight change the weight
            Edge newEdge = new Edge(src, dest, w);
            edges.get(src).put(dest, newEdge);
            Edge newEdge_reverse = new Edge(dest, src, w);
            edgesReverse.get(dest).put(src, newEdge_reverse);
            mc++;
        } else {// if the nodes contain but not have the edge between of them
            Edge newEdge = new Edge(src, dest, w);
            edges.get(src).put(dest, newEdge);
            Edge newEdge_reverse = new Edge(dest, src, w);
            edgesReverse.get(dest).put(src, newEdge_reverse);
            mc++;
            edgeSize++;
        }
    }

    /**
     * This function iterates over all the nodes in the graph
     *
     * @return the iterator representing the nodes iterator
     */
    @Override
    public Iterator<NodeData> nodeIter() {
        int temp = mc;
        Iterator<NodeData> iterator = this.nodes.values().iterator(); // get all the nodes in the node HashMap
        if (temp != mc) { //check if no changes made while iterating. if changes were made, throw exception.
            throw new RuntimeException();
        }
        return iterator;
    }

    /**
     * This function iterates over all the edges in the graph.
     *
     * @return the iterator representing the edges iterator
     */
    @Override
    public Iterator<EdgeData> edgeIter() {
        ArrayList<EdgeData> arrayList = new ArrayList<>(); //create a list
        for (int node = 0; node < nodeSize; node++) {
            if (nodes.containsKey(node)) {
                Iterator<EdgeData> iterator = edgeIter(node);//go over all the edges of each node
                while (iterator.hasNext())
                    arrayList.add(iterator.next());// fill the list with all the nodes
            }
        }
        return arrayList.iterator();
    }


    /**
     * This function iterates over all the edges getting out of a certain nodde
     *
     * @param node_id - a node's key
     * @return the iterator representing the edges getting out of a node iterator
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        int temp = mc;
        if (!this.edges.containsKey(node_id)) { //check if the node with the key node_id is null
            return null;
        }
        Iterator<EdgeData> iterator = this.edges.get(node_id).values().iterator(); //get the node's id edges and iterate over them
        if (temp != mc) {
            throw new RuntimeException(); //check if no changes made while iterating. if changes were made, throw exception.
        }
        return iterator;
    }

    /**
     * This function removes a node and all the edges coming in and out of the node
     *
     * @param key - the node's id we wish to remove
     * @return the removed node
     */
    @Override
    public NodeData removeNode(int key) {
        if (nodes.get(key) == null) {
            return null;
        }

        int count = 0; // count how many removed
        Node n = new Node((Node) nodes.get(key));
        this.nodes.remove(key);
        Iterator<EdgeData> edgeDataIterator = edgeIter(key); //iterate over all the edges going out of the node
        while (edgeDataIterator.hasNext()) {
            EdgeData edge = edgeDataIterator.next();
            this.edgesReverse.get(edge.getDest()).remove(edge.getSrc()); //remove from reversed edges
            count++;
        }
        Iterator<EdgeData> edgeDataIterator2 = edgeIterReverse(key); //iterate over edges going in a node
        while (edgeDataIterator2.hasNext()) {
            EdgeData edge = edgeDataIterator2.next();
            this.edges.get(edge.getDest()).remove(edge.getSrc()); // remove from the edges going out
            count++;
        }
        edgeSize -= count;
        mc += count;
        this.edges.remove(key);
        this.edgesReverse.remove(key);
        nodeSize--;
        mc++;
        return n;
    }

    /**
     * This function iterates over all the edges getting in a certain node
     *
     * @param node_id - a node's key
     * @return the iterator representing the edges getting in a node iterator
     */
    public Iterator<EdgeData> edgeIterReverse(int node_id) {
        int temp = mc;
        if (!this.edgesReverse.containsKey(node_id)) {
            return null;
        }
        Iterator<EdgeData> iterator = this.edgesReverse.get(node_id).values().iterator();
        if (temp != mc) {
            throw new RuntimeException();
        }
        return iterator;
    }

    /**
     * This function removes an edge from the graph
     *
     * @param src  - the source node's id
     * @param dest - the destination node's id
     * @return the edge connecting between src and dest
     */
    @Override
    public EdgeData removeEdge(int src, int dest) {

        if (edges.get(src).get(dest) == null && edgesReverse.get(dest).get(src) == null) { //check if there is no edge
            return null;
        }
        Edge e = new Edge((Edge) edges.get(src).get(dest));
        edges.get(src).remove(dest); //remove from edges
        edgesReverse.get(dest).remove(src); // remove from reversed edges
        edgeSize--;
        mc++;
        return e;
    }

    /**
     * @return the node size of the graph
     */
    @Override
    public int nodeSize() {
        return nodeSize;
    }

    /**
     * @return the edge size of the graph
     */
    @Override
    public int edgeSize() {
        return edgeSize;
    }

    /**
     * @return the mode counter of the graph
     */
    @Override
    public int getMC() {
        return mc;
    }
}
