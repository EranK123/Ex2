package api;

import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms{

    private Graph graph;

    public GraphAlgo(Graph g){
        this.graph = new Graph(g);
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
        return new Graph(this.graph);
    }

    @Override
    public boolean isConnected() {
        return isStronglyConnected(graph, graph.nodeSize());
    }


    private void dfs(Graph g, int n, boolean[] visited) {
        visited[n] = true;
        for (Iterator<EdgeData> it = g.edgeIter(n); it.hasNext(); ) {
            int nextNode = it.next().getDest();
            if (!visited[nextNode]) {
                dfs(g, nextNode, visited);
            }
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
        set_Tag();
        double[] d = dijkstra(this.graph, this.graph.getNode(src));
        return d[dest];
    }
    private double[] dijkstra(Graph g, NodeData src){
        double[] dist = new double[g.nodeSize()];
        Set<NodeData> visited = new HashSet<>();
        PriorityQueue<NodeData> p = new PriorityQueue<>(g.nodeSize(), new Comparator<NodeData>() {
            @Override
            public int compare(NodeData n1, NodeData n2) {
                return Double.compare(n1.getWeight(), n2.getWeight());
            }
        });

        Arrays.fill(dist, Integer.MAX_VALUE);
        p.add(src);
        dist[src.getKey()] = 0;
        while (visited.size() != g.nodeSize()) {

            NodeData node = p.remove();
            visited.add(node);
            adj(node, p, dist, visited);
        }
        return dist;
    }

    private void adj(NodeData node, PriorityQueue<NodeData> p, double[] dist, Set<NodeData> visited) {
        double current_dis = -1;
        double new_dis = -1;

        for (Iterator<EdgeData> it = this.graph.edgeIter(node.getKey()); it.hasNext();) {
            EdgeData e = it.next();
            NodeData node_dest = this.graph.getNode(e.getDest());

            if (!visited.contains(node_dest)) {
                current_dis = this.graph.getEdge(node.getKey(), node_dest.getKey()).getWeight();
                new_dis = dist[node.getKey()] + current_dis;

                if (new_dis < dist[node_dest.getKey()]) {
                    dist[node_dest.getKey()] = new_dis;
                    node_dest.setTag(node.getKey());
                }
            }
                if (visited.contains(node_dest)){
                    dist[node_dest.getKey()] = 0;
                }
                p.add(new Node((Node) node_dest));
            }
    }

    private void set_Tag(){
        for (int i = 0; i < graph.nodeSize(); i++) {
            graph.getNode(i).setTag(-1);
        }
    }
    private void set_weight(){
        for (int i = 0; i < graph.nodeSize(); i++) {
            graph.getNode(i).setWeight(Integer.MAX_VALUE);
        }
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> list1 = new ArrayList<>();
        List<NodeData> list2 = new ArrayList<>();
        shortestPathDist(src,dest);
        int parent = dest;
        while (parent != src){
            list1.add(graph.getNode(parent));
            parent = graph.getNode(dest).getTag();
            dest = graph.getNode(parent).getKey();
        }
        list1.add(graph.getNode(src));
        for (int i = 0; i < list1.size(); i++) {
            list2.add(list1.get(list1.size() - i - 1));
        }

        return list2;
    }

    @Override
    public NodeData center() {
        if (!isConnected()){
            return null;
        }
        double[] maxDist = new double[graph.nodeSize()];
        for (int i = 0; i < graph.nodeSize(); i++) {
            double[] dist = dijkstra(graph,graph.getNode(i));
            maxDist[i] = max(dist);
        }
        double minDist = min(maxDist);
        int key = -1;
        for (int i = 0; i < graph.nodeSize(); i++) {
            if (minDist == maxDist[i]) {
                key = i;
            }
        }
        return graph.getNode(key);
    }

    private double max(double []a){
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max){
                max = a[i];
            }
        }
        return max;
    }
    private double min(double []a){
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min){
                min = a[i];
            }
        }
        return min;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        JsonObject jsonObject = new JsonObject();
        JsonArray edges = new JsonArray();
        jsonObject.add("Edges", edges);
        JsonArray nodes = new JsonArray();
        jsonObject.add("Nodes", nodes);

        for(Iterator<EdgeData> iterator = graph.edgeIter(); iterator.hasNext();){
            EdgeData edge = iterator.next();
            JsonObject object = new JsonObject();
            object.addProperty("src", "" + edge.getSrc());
            object.addProperty("w", "" + edge.getWeight());
            object.addProperty("dest", "" + edge.getDest());
            edges.add(object);
        }

        for (Iterator<NodeData> iterator = graph.nodeIter(); iterator.hasNext();){
            NodeData node =  iterator.next();
            JsonObject object = new JsonObject();
            object.addProperty("pos", "" + node.getLocation().x() + "," + node.getLocation().y() + "," +
                    node.getLocation().z());
            object.addProperty("id", "" + node.getKey());
            nodes.add(object);
        }
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(jsonObject));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean load(String file) {
        JsonParser jsonParser = new JsonParser();
        try (FileReader reader = new FileReader(file))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JsonArray graph = (JsonArray) obj;
            System.out.println(graph);

            //Iterate over Edges array
            graph.forEach( edge -> parseEdgeObject( (JsonObject) edge ) );
            //Iterate over Nodes array
            graph.forEach(node -> parseNodeObject((JsonObject) node));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    private static void parseEdgeObject(JsonObject edges)
    {
        //Get employee object within list
        JsonObject edgeObject = (JsonObject) edges.get("Edges");

        //Get src
        JsonElement src =  edgeObject.get("src");

        //Get w
        JsonElement w =  edgeObject.get("w");

        //Get employee website name
        JsonElement dest =  edgeObject.get("dest");
    }

    private static void parseNodeObject(JsonObject nodes)
    {
        //Get employee object within list
        JsonObject edgeObject = (JsonObject) nodes.get("Nodes");

        //Get src
        JsonElement pos =  edgeObject.get("pos");
        //Get id
        JsonElement id =  edgeObject.get("id");
    }
}
