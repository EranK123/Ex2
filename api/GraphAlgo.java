package api;

import java.util.Iterator;
import java.util.List;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms{

    private Graph graph;


    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = new Graph();
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
            Graph g = new Graph(graph);
            return g;

    }

    @Override
    public boolean isConnected() {
        return isStronglyConnected(graph, graph.nodeSize());
    }


    private void dfs(Graph g, int n, boolean[] visited){
        visited[n] = true;
        Iterator<EdgeData> i = g.edgeIter(n);
        if (!visited[i.next().getDest()]){
            dfs(g,i.next().getDest(),visited);
        }
    }
    private boolean isStronglyConnected(Graph g, int n){
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            dfs(g,i,visited);
            for (boolean b: visited) {
                if (!b){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
