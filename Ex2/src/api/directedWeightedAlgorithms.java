package api;

import java.util.*;

public class directedWeightedAlgorithms implements DirectedWeightedGraphAlgorithms{

    private Graph graph;

//    public directedWeightedAlgorithms(){
//        this.graph = null;
//    }
    public directedWeightedAlgorithms(DirectedWeightedGraph g){
        this.graph = new Graph((Graph) g);
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = (Graph) g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph g = new Graph( this.graph);
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
        return dijkstra(this.graph, this.graph.getNode(src), this.graph.getNode(dest));
    }
    private double dijkstra(Graph g, NodeData src, NodeData dest){

        double dist[] = new double[g.nodeSize()];
        Set<NodeData> visited = new HashSet<NodeData>();
        PriorityQueue<NodeData> p = new PriorityQueue<>(g.nodeSize(), new Comparator<NodeData>() {
            @Override
            public int compare(NodeData n1, NodeData n2) {
                EdgeData e1 = g.getEdge(src.getKey(), n1.getKey());
                EdgeData e2 = g.getEdge(src.getKey(), n2.getKey());
                return Double.compare(e1.getWeight(), e2.getWeight());
            }
            });

        for (int i=0; i<dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        p.add(src);
        dist[src.getKey()] = 0;

        while (visited.size() != g.nodeSize()) {

            NodeData node = p.remove();
            visited.add(node);
            adj(node, p, dist, visited);
        }
        return dist[dest.getKey()];
}

    private void adj(NodeData node, PriorityQueue<NodeData> p, double[] dist, Set<NodeData> visited) {
        double current_dis = -1;
        double new_dis = -1;

        for (Iterator<EdgeData> it = this.graph.edgeIter(node.getKey()); it.hasNext();) {
            EdgeData e = it.next();
            NodeData node_dest = this.graph.getNode(e.getDest());

            if (!visited.contains(node_dest)){
                current_dis = this.graph.getEdge(node.getKey(), node_dest.getKey()).getWeight();
                new_dis = dist[node.getKey()] + current_dis;

                if (new_dis < dist[node_dest.getKey()])
                    dist[node_dest.getKey()] = new_dis;

                p.add(new Node(node_dest));
        }
    }
//        p.add(src);
//        while (!p.isEmpty()) {
//            NodeData node_src = p.poll();
//            double weight = Integer.MAX_VALUE;
//            for (Iterator<EdgeData> it = g.edgeIter(node_src.getKey()); it.hasNext();) {
//                EdgeData e = it.next();
//                NodeData node_dest = g.getNode(e.getDest());
//                if (node_dest.getTag() == 0) {
//                    if (e.getWeight() < weight)
//                        node_dest.setTag(1);
//                    p.add(node_dest);
//                }
//                return -1;
//            }
//        }
//        int dist[] = new int[g.nodeSize()];
//        boolean visited[] = new boolean[g.nodeSize()];
//
//        for (int i=0; i<g.nodeSize(); i++){
//            visited[i] = false;
//            path[i] = -1;
//            dist[i] = Integer.MAX_VALUE;
//        }
//        dist[src] = 0;
//        path[src] = -1;
//        int current = src;
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
