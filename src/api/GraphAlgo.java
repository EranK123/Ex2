package api;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {

    private Graph graph;

    public GraphAlgo(Graph g) {
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

//    public void BFS(Graph g, int n, boolean visited[]) {
//
//        // Mark the current node as visited and enqueue it
//        visited[n] = true;
////        boolean visited[] = new boolean[this.graph.nodeSize()];
//
//        // Create a queue for BFS
//        LinkedList<Integer> list = new LinkedList<Integer>();
//
//        list.add(n);
//
//        while (list.size() != 0) {
//            // Dequeue a vertex from queue and print it
//            n = list.poll();
////            System.out.print(s+" ");
//
//            // Get all adjacent vertices of the dequeued vertex s
//            // If a adjacent has not been visited, then mark it
//            // visited and enqueue it
//            while (g.nodeIter().hasNext()) {
//                NodeData node = this.graph.nodeIter().next();
//                if (!visited[node.getKey()]) {
//                    visited[node.getKey()] = true;
//                    list.add(node.getKey());
//                }
//            }
//        }
//    }

    private void dfs(Graph g, int n, boolean[] visited) {
        Stack<Integer> s = new Stack<>();
        s.push(n);

        while (!s.empty()){

            n = s.pop();

            if (!visited[n]){
                visited[n] = true;
            }
            List<Integer> list_nie = new ArrayList<>();
            for (Iterator<EdgeData> it = g.edgeIter(n); it.hasNext();) {
                list_nie.add(it.next().getDest());
            }
            for (int i = list_nie.size()-1; i >= 0 ; i--) {
                int next = list_nie.get(i);
                if (!visited[next]){
                    s.push(next);
                }
            }
            }
    }

    private boolean isStronglyConnected(Graph g, int n) {
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            dfs(g, i, visited);
            for (boolean b : visited) {
                if (!b) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        double[] d = dijkstra(this.graph, this.graph.getNode(src));
        return d[dest];
    }

    private double[] dijkstra(Graph g, NodeData src) {
        set_Tag();
        set_weight();
        System.out.println(src.getKey());
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
        src.setWeight(0);
        dist[src.getKey()] = 0;

        if (!g.edgeIter(src.getKey()).hasNext()){
            return dist;
        }

        while (visited.size() != g.nodeSize() && p.size() > 0) {
            NodeData node = p.remove();
            visited.add(node);
            adj(node, p, dist, visited);
        }
        return dist;
    }

    private void adj(NodeData node, PriorityQueue<NodeData> p, double[] dist, Set<NodeData> visited) {
        double current_dis = -1;
        double new_dis = -1;

        for (Iterator<EdgeData> it = this.graph.edgeIter(node.getKey()); it.hasNext(); ) {
            EdgeData e = it.next();
            NodeData node_dest = this.graph.getNode(e.getDest());

            if (!visited.contains(node_dest)) {
                current_dis = this.graph.getEdge(node.getKey(), node_dest.getKey()).getWeight();
                new_dis = current_dis + node.getWeight();

                if (new_dis < node_dest.getWeight()) {
                    dist[node_dest.getKey()] = new_dis;
                    node_dest.setWeight(new_dis);
                    node_dest.setTag(node.getKey());
                }
                p.add(node_dest);
            }
//            if (visited.contains(node_dest)) {
//                dist[node_dest.getKey()] = 0;
//            }
        }
    }

    private void set_Tag() {
        for (int i = 0; i < graph.nodeSize(); i++) {
            graph.getNode(i).setTag(-1);
        }
    }

    private void set_weight() {
        for (int i = 0; i < graph.nodeSize(); i++) {
            graph.getNode(i).setWeight(Integer.MAX_VALUE);
        }
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> list1 = new ArrayList<>();
        List<NodeData> list2 = new ArrayList<>();
        shortestPathDist(src, dest);
        int parent = dest;
        while (parent != src) {
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
        if (!isConnected()) {
            return null;
        }
        double[] maxDist = new double[graph.nodeSize()];
        for (int i = 0; i < graph.nodeSize(); i++) {
            double[] dist = dijkstra(graph, graph.getNode(i));
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

    private double max(double[] a) {
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    private double min(double[] a) {
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        return min;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {//O(V)*O(V)*O(V+E*logV) = O(V^3 + V^2*E*logV)
        if (cities.size() > this.graph.nodeSize()){
            return null;
        }
        List<NodeData> list_cities = new ArrayList<>();
        double min_list = Integer.MAX_VALUE;
        List<NodeData> list_cities_temp = new ArrayList<>();
        double min_list_temp = 0;
        for (int i = 0; i < cities.size(); i++) {//O(V)
            list_cities_temp.clear();//O(V)
            List<NodeData> cities_temp = new ArrayList<>();
            cities_temp.addAll(cities);//O(V)
            min_list_temp = 0;
            int curr_node = i;
            while (cities_temp.size() > 1) {//O(V)
                int key = cities_temp.get(curr_node).getKey();
                cities_temp.remove(cities_temp.get(curr_node));
                double[] d = dijkstra(this.graph, this.graph.getNode(key));//(O(V+E*logV)
                double min = Integer.MAX_VALUE;
                for (int j = 0; j < cities_temp.size(); j++) {//O(V)
                    if (min > d[cities_temp.get(j).getKey()]) {
                        min = d[cities_temp.get(j).getKey()];
                        curr_node = cities_temp.get(j).getKey();
                    }
                }
                if(min == Integer.MAX_VALUE){
                    break;
                }
                min_list_temp += min;
                //save the list of the minimum path
                List<NodeData> list = shortestPath_tsp(key, curr_node);//O(V)
                list_cities_temp.addAll(list);//O(V)
            }
            if(min_list > min_list_temp){
                list_cities.clear();//O(V)
                list_cities.addAll(list_cities_temp);//O(V)
                min_list = min_list_temp;
            }
        }
        return list_cities;
    }

    public List<NodeData> shortestPath_tsp(int src, int dest) {//O(V)
        List<NodeData> list1 = new ArrayList<>();
        List<NodeData> list2 = new ArrayList<>();
        int parent = dest;
        while (parent != src) {
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
    public boolean save(String file) {
        JsonObject jsonObject = new JsonObject();
        JsonArray edges = new JsonArray();
        jsonObject.add("Edges", edges);
        JsonArray nodes = new JsonArray();
        jsonObject.add("Nodes", nodes);

        for (Iterator<EdgeData> iterator = graph.edgeIter(); iterator.hasNext(); ) {
            EdgeData edge = iterator.next();
            String[] property = {"src", "w", "dest"};
            String[] value = {"" + edge.getSrc(), "" + edge.getWeight(), "" + edge.getDest()};
            edges.add(create_json(2, property, value));
//            JsonObject object = new JsonObject();
//            object.addProperty("src", "" + edge.getSrc());
//            object.addProperty("w", "" + edge.getWeight());
//            object.addProperty("dest", "" + edge.getDest());
//            edges.add(object);
        }

        for (Iterator<NodeData> iterator = graph.nodeIter(); iterator.hasNext();) {
            NodeData node = iterator.next();
            String[] property = {"pos", "id"};
            String[] value = {"" + node.getLocation().x() + "," + node.getLocation().y() + "," + node.getLocation().z(), "" + node.getKey()};
            nodes.add(create_json(1, property, value));
//            JsonObject object = new JsonObject();
//            object.addProperty("pos", "" + node.getLocation().x() + "," + node.getLocation().y() + "," +
//                    node.getLocation().z());
//            object.addProperty("id", "" + node.getKey());
//            nodes.add(object);
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

    private JsonObject create_json(int i, String[] property, String[] value){
        JsonObject object = new JsonObject();
        for (int j = 0; j <= i; j++) {
            object.addProperty(property[j], value[j]);
        }
        return object;
    }

    @Override
    public boolean load(String file) {
        JsonParser jsonParser = new JsonParser();
        JsonParser jsonParser2 = new JsonParser();
        try  {
            //Read JSON file
            FileReader reader = new FileReader(file);
            FileReader reader2 = new FileReader(file);
            //        JsonObject obj = (JsonObject) jsonParser.parse(reader);
            JsonReader jsonReader = new JsonReader(reader);
            JsonReader jsonReader2 = new JsonReader(reader2);

            JsonArray graph2 = (JsonArray) jsonParser2.parse(jsonReader2).getAsJsonObject().get("Nodes");
//            System.out.println(graph2);
            graph2.forEach(node -> parseNodeObject((JsonObject) node));

            JsonArray graph = (JsonArray) jsonParser.parse(jsonReader).getAsJsonObject().get("Edges");

            //        JsonArray graph = obj.getAsJsonArray();
//            System.out.println(graph);

            //Iterate over Edges array
            graph.forEach( edge -> parseEdgeObject( (JsonObject) edge ) );
            //Iterate over Nodes array


        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    private void parseEdgeObject(JsonObject edges)
    {
        //Get employee object within list
//        JsonObject edgeObject = edges;


        //Get src
//        JsonElement src =  edgeObject.get("src");
        int src = edges.get("src").getAsInt();

        //Get w
//        JsonElement w =  edgeObject.get("w");
        double w = edges.get("w").getAsDouble();
        //Get employee website name
//        JsonElement dest =  edgeObject.get("dest");
        int dest = edges.get("dest").getAsInt();

        graph.connect(src, dest, w);
    }
    private void parseNodeObject(JsonObject nodes)
    {
        //Get employee object within list
//        JsonObject edgeObject = (JsonObject) nodes.get("Nodes");
//        JsonObject edgeObject = nodes;
        //Get src
//        JsonElement pos =  edgeObject.get("pos");
        //Get id
//        JsonElement id =  edgeObject.get("id");
        int key = nodes.get("id").getAsInt();
        JsonElement location = nodes.get("pos");
        String s = location.getAsString();
        String arr[] = s.split(",");
        double x = Double.parseDouble(arr[0]);
        double y = Double.parseDouble(arr[1]);
        double z = Double.parseDouble(arr[2]);
        GeoLocation pos = new Location(x, y, z);
        NodeData n = new Node(key, (Location) pos);
        graph.addNode(n);
    }
}
