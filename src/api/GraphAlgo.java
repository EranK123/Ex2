package api;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {

    private Graph graph;

    public GraphAlgo(Graph g) {
        this.graph = new Graph(g);
    }

    /**
     * Inits the graph on which this set of algorithms operates on.
     *
     * @param g - a Graph
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = (Graph) g;
    }

    /**
     * @return the graph this class works on
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    /**
     * Computes a deep copy of this weighted graph.
     */
    @Override
    public DirectedWeightedGraph copy() {
        return new Graph(this.graph);
    }

    /**
     * This function checks if a graph is strongly connected. i,e a graph that has a path from each vertex to every other vertex.
     * The method uses the dfs iterative algorithm to check if the graph is connected or not. it checks using the isStronglyConnected method each and every vertex if it is connected to all other
     * vertexes and updates it as visited. if at the end we have a boolean array filled with true values, we can say the graph is connected.
     *
     * @return true if it is connected. false if not
     */
    @Override
    public boolean isConnected() {
        return isStronglyConnected();
    }

    private boolean BFS(int node) {
//        set_Tag();
        // Mark the current node as visited and enqueue it
        this.graph.getNode(node).setTag(1);
//        boolean visited[] = new boolean[this.graph.nodeSize()];
        // Create a queue for BFS
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.add(node);
        int count_of_nodes = 1;
        while (list.size() != 0) {
            // Dequeue a vertex from queue and print it
            int n = list.poll();
//            System.out.print(s+" ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<EdgeData> edges = this.graph.edgeIter(n);
            while (edges.hasNext()) {
                EdgeData edgeData = edges.next();
                int nodeData_dest = edgeData.getDest();
                if (this.graph.getNode(nodeData_dest).getTag() != 1) {
                    this.graph.getNode(nodeData_dest).setTag(1);
                    list.add(this.graph.getNode(nodeData_dest).getKey());
                    count_of_nodes++;
//                    visited[node.getKey()] = true;
//                    list.add(node.getKey());
                }
            }
        }
        if (count_of_nodes != this.graph.nodeSize()) {
            return false;
        }
        return true;
    }

    private boolean isStronglyConnected() {
//        for (int i = 0; i < n; i++) {
//            boolean[] visited = new boolean[n];
        Iterator<NodeData> nodeDataIterator = this.graph.nodeIter();
        while (nodeDataIterator.hasNext()) {
            NodeData nodeData = nodeDataIterator.next();
            boolean b = BFS(nodeData.getKey());
            set_Tag();
//            for (boolean b : true) {
            if (!b) {
                return false;
            }
//            }
        }
        return true;
    }


    /**
     * This function computes the length of the shortest path between source node to destination node based on edges weights. It uses dijkstra algorithm to find the shortest
     * path between source node and each other node in the graph. It computes the path in an array with each index representing other node index in the graph.
     * Finally we return the array in the destination index which holds the shortest path.
     *
     * @param src  - start node
     * @param dest - end node
     * @return the shortest path between src and dest
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        set_Tag();   //set all tags to -1. we use it in shortestPath method
        dijkstra(graph, this.graph.getNode(src));
        return this.graph.getNode(dest).getWeight();
    }

    /**
     * The dijkstra algorithm computes the shortest path between a node to every other node in the graph. It sets an array of distances where each distance first
     * set to max value. We also set a PriorityQueue which contains the weights of the nodes. The weight of the node is the weight of the edge going in the node.
     * We iterate over the edges, check if a node hasn't been visited yet and compute the path between the current node to it. Then compare to the latest and update.
     *
     * @param g   - the graph
     * @param src - node source
     * @return array with distances values
     */

//    /**
//     * we use the algorithm in this function to check over all the neighbors of the current node
//     *
//     * @param node    - the node we removed from the pq
//     * @param p       - the pq
//     * @param dist    - the array of distances
//     * @param visited - set that marks if nodes are visited or not
//     */
//    private void adj(NodeData node, PriorityQueue<NodeData> p, double[] dist, Set<NodeData> visited) {
//        double current_dis = -1;
//        double new_dis = -1;
//
//        for (Iterator<EdgeData> it = this.graph.edgeIter(node.getKey()); it.hasNext(); ) { //iterate over all edges
//            EdgeData e = it.next(); // get the next edge
//            NodeData node_dest = this.graph.getNode(e.getDest()); // get the neighbor
//
//            if (!visited.contains(node_dest)) {
//                current_dis = this.graph.getEdge(node.getKey(), node_dest.getKey()).getWeight() + node.getWeight(); // update current dis to latest dist plus current node's weight
//                new_dis = current_dis; //update new dis
//                // compare the distances and update
//                if (new_dis < node_dest.getWeight()) {
//                    dist[node_dest.getKey()] = new_dis;
//                    node_dest.setWeight(new_dis);
//                    node_dest.setTag(node.getKey());
//                }
//                p.add(node_dest);
//            }
////            if (visited.contains(node_dest)) {
////                dist[node_dest.getKey()] = 0;
////            }
//
//        }
//    }
    private void dijkstra(Graph g, NodeData src) {
        set_Tag();
        set_weight();

        Set<NodeData> visited = new HashSet<>();
        PriorityQueue<NodeData> p = new PriorityQueue<>(g.nodeSize(), new Comparator<NodeData>() {
            @Override
            public int compare(NodeData n1, NodeData n2) {
                return Double.compare(n1.getWeight(), n2.getWeight());
            }
        });

        p.add(src);
        src.setWeight(0);

        if (!g.edgeIter(src.getKey()).hasNext()) {
            return;
        }

        while (visited.size() != g.nodeSize() && p.size() > 0) {
            NodeData node = p.remove();
            visited.add(node);
            adj(node, p, visited);
        }
    }

    private void adj(NodeData node, PriorityQueue<NodeData> p, Set<NodeData> visited) {
        double current_dis = -1;
        double new_dis = -1;

        for (Iterator<EdgeData> it = this.graph.edgeIter(node.getKey()); it.hasNext(); ) {
            EdgeData e = it.next();
            NodeData node_dest = this.graph.getNode(e.getDest());

            if (!visited.contains(node_dest)) {
                current_dis = this.graph.getEdge(node.getKey(), node_dest.getKey()).getWeight();
                new_dis = current_dis + node.getWeight();

                if (new_dis < node_dest.getWeight()) {
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

    /**
     * Sets all the tags of the nodes to -1. The tags represent the parent of the node
     */
    private void set_Tag() {
        for (int i = 0; i < graph.nodeSize(); i++) {
            graph.getNode(i).setTag(-1);
        }
    }

    /**
     * Sets all the weights to max value
     */
    private void set_weight() {
        for (int i = 0; i < graph.nodeSize(); i++) {
            graph.getNode(i).setWeight(Integer.MAX_VALUE);
        }
    }

    /**
     * Computes the shortest path between source node to destination node - as an ordered list of nodes. This function will use the shortestPathDist method to get all the parent
     * nodes to each node thet compute the shortest path. It will the parents to a list and return it.
     * and will compute the
     *
     * @param src  - start node
     * @param dest - end node
     * @return list of the
     */
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
        NodeData node_center = null;
        double maxDist = Integer.MAX_VALUE;
        Iterator<NodeData> nodeDataIterator = graph.nodeIter();
        while (nodeDataIterator.hasNext()) {
            int node = nodeDataIterator.next().getKey();
            dijkstra(graph, graph.getNode(node));
            double temp = max();
            if (temp < maxDist) {
                maxDist = temp;
                node_center = this.graph.getNode(node);
            }
        }
        return node_center;
    }

    private double max() {
        double maxV = Integer.MIN_VALUE;
        for (int i = 0; i < this.graph.nodeSize(); i++) {
            if (this.graph.getNode(i).getWeight() > maxV) {
                maxV = this.graph.getNode(i).getWeight();
            }
        }
        return maxV;
    }


    @Override
    public List<NodeData> tsp(List<NodeData> cities) {//O(V)O(V)O(V+ElogV) = O(V^3 + V^2ElogV)
        if (cities.size() > this.graph.nodeSize()) {
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
            int loc = i;
            while (cities_temp.size() > 1) {//O(V)
                int key = cities_temp.get(loc).getKey();
                cities_temp.remove(cities_temp.get(loc));
                dijkstra(this.graph, this.graph.getNode(key));//(O(V+ElogV)
                double min = Integer.MAX_VALUE;
                for (int j = 0; j < cities_temp.size(); j++) {//O(V)
                    if (min > this.graph.getNode(j).getWeight()) {
                        min = this.graph.getNode(j).getWeight();
                        curr_node = cities_temp.get(j).getKey();
                        loc = j;
                    }
                }
                if (min == Integer.MAX_VALUE) {
                    break;
                }
                min_list_temp += min;
                //save the list of the minimum path
                List<NodeData> list = shortestPathTsp(key, curr_node);//O(V)
                list_cities_temp.addAll(list);//O(V)
            }
            if (min_list > min_list_temp) {
                list_cities.clear();//O(V)
                list_cities.addAll(list_cities_temp);//O(V)
                min_list = min_list_temp;
            }
        }
        return list_cities;
//    return null;
    }

    public List<NodeData> shortestPathTsp(int src, int dest) {//O(V)
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

        for (Iterator<EdgeData> iterator = graph.edgeIter(); iterator.hasNext();) {
            EdgeData edge = iterator.next();
            String[] property = {"src", "w", "dest"};
            double[] value = {edge.getSrc() , edge.getWeight() , edge.getDest()};
            edges.add(create_json_edges(2, property, value));
        }

        for (Iterator<NodeData> iterator = graph.nodeIter(); iterator.hasNext();) {
            NodeData node = iterator.next();
            String[] property = {"pos", "id"};
            String[] value = {node.getLocation().x() + "," + node.getLocation().y() + "," + node.getLocation().z() , "" + node.getKey()};
            nodes.add(create_json_nodes(1, property, value));
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

    private JsonObject create_json_edges(int i, String[] property, double[] value){
        JsonObject object = new JsonObject();
        for (int j = 0; j <= i; j++) {
            if (j != 1) {
                object.addProperty(property[j], (int) (value[j]));
            } else {
                object.addProperty(property[j], value[j]);
            }
        }
        return object;
    }
    private JsonObject create_json_nodes(int i, String[] property, String[] value){
        JsonObject object = new JsonObject();
        for (int j = 0; j <= i; j++) {
            if (j == 1) {
                int val = Integer.parseInt(value[j]);
                object.addProperty(property[j], val);
            } else {
                object.addProperty(property[j], value[j]);
            }
        }
        return object;
    }

    @Override
    public boolean load(String file) {
        JsonParser jsonParser = new JsonParser();
        JsonParser jsonParser2 = new JsonParser();
        try {
            //Read JSON file
            FileReader reader = new FileReader(file);
            FileReader reader2 = new FileReader(file);
            //        JsonObject obj = (JsonObject) jsonParser.parse(reader);
            JsonReader jsonReader = new JsonReader(reader);
            JsonReader jsonReader2 = new JsonReader(reader2);

            JsonArray graph2 = (JsonArray) jsonParser2.parse(jsonReader2).getAsJsonObject().get("Nodes");
            graph2.forEach(node -> parseNodeObject((JsonObject) node));
            JsonArray graph = (JsonArray) jsonParser.parse(jsonReader).getAsJsonObject().get("Edges");

            //        JsonArray graph = obj.getAsJsonArray();

            //Iterate over Edges array
            graph.forEach(edge -> parseEdgeObject((JsonObject) edge));
            //Iterate over Nodes array


        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * This functions parses the edges from json file to Edge objects
     *
     * @param edges
     */
    private void parseEdgeObject(JsonObject edges) {
        int src = edges.get("src").getAsInt();
        double w = edges.get("w").getAsDouble();
        int dest = edges.get("dest").getAsInt();

        graph.connect(src, dest, w);
    }

    /**
     * This functions parses the nodes from json file to Node objects
     *
     * @param nodes - the nodes in json file
     */
    private void parseNodeObject(JsonObject nodes) {
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
