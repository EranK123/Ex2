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
     * The method uses the bfs iterative algorithm to check if the graph is connected or not. it checks using the isStronglyConnected method each and every vertex if it is connected to all other
     * vertexes and updates it as visited. we set it as visited by setting the tag to 1. if at the end we have a boolean array filled with true values, we can say the graph is connected.
     *
     * @return true if it is connected. false if not
     */
    @Override
    public boolean isConnected() {
        return isStronglyConnected();
    }

    /**
     * This function uses bfs algorithm to check if the graph is connected or not.
     * @param node - starting node
     * @return true if we visited all the nodes i.e the node count is equal to the nodes size
     */
    private boolean BFS(int node) {
        this.graph.getNode(node).setTag(1);   // Mark the current node as visited and enqueue it
        LinkedList<Integer> list = new LinkedList<>();// Create a queue for BFS
        list.add(node);
        int nodeCount = 1; // we counted 1 node as visited
        while (list.size() != 0) {
            int n = list.poll(); // Dequeue a vertex from queue and print it
            // Get all adjacent vertices of the dequeued vertex s
            // If an adjacent node has not been visited, then mark it as
            // visited and enqueue it
            Iterator<EdgeData> edges = this.graph.edgeIter(n);
            while (edges.hasNext()) {
                EdgeData edgeData = edges.next();
                int nodeData_dest = edgeData.getDest();
                if (this.graph.getNode(nodeData_dest).getTag() != 1) {
                    this.graph.getNode(nodeData_dest).setTag(1);
                    list.add(this.graph.getNode(nodeData_dest).getKey());
                    nodeCount++;
                }
            }
        }
        return nodeCount == this.graph.nodeSize();
    }

    /**
     * This method applies the bfs algorithm on each and every one of the nodes in the graph. if we find even one node that returns false from the bfs check,
     *  which means it is not connected, we return false.
     * @return true or false if the graph is connected or not
     */
    private boolean isStronglyConnected() {
        Iterator<NodeData> nodeDataIterator = this.graph.nodeIter();
        while (nodeDataIterator.hasNext()) {
            NodeData nodeData = nodeDataIterator.next();
            boolean b = BFS(nodeData.getKey());
            set_Tag();
            if (!b) {
                return false;
            }
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
     * set to max value. We also set a PriorityQueue which contains the weights of the nodes. The weight of the node is the weight of the edge going in the node plus the path to the node.
     * We iterate over the edges, check if a node hasn't been visited yet and compute the path between the current node to it. Then compare to the latest and update.
     *
     * @param g   - the graph
     * @param src - node source
     * @return array with distances values
     */

    private void dijkstra(Graph g, NodeData src) {
        set_Tag(); //reset all the tags to -1. we are using the tags as markers for the parents in the next function shortestPath
        set_weight(); // set weights to max value
        Set<NodeData> visited = new HashSet<>();
        PriorityQueue<NodeData> p = new PriorityQueue<>(g.nodeSize(), new Comparator<NodeData>() {
            @Override
            public int compare(NodeData n1, NodeData n2) {
                return Double.compare(n1.getWeight(), n2.getWeight());
            }
        });

        p.add(src);
        src.setWeight(0); //

        if (!g.edgeIter(src.getKey()).hasNext()){ //check if the node is connected to other nodes
            return;
        }

        while (visited.size() != g.nodeSize() && p.size() > 0) {
            NodeData node = p.remove(); //remove the lowest weight node
            if (!visited.contains(node)) { //check if we haven't visited it yet
                visited.add(node);
                adj(node, p, visited); //check paths to adjacent nodes
            }
        }
    }

    /**
     * This function computes the path from a node to all of it's neighbors
     * @param node - source node
     * @param p - the PriorityQueue
     * @param visited - the visited set
     */
    private void adj(NodeData node, PriorityQueue<NodeData> p, Set<NodeData> visited) {
        double current_dis = -1;
        double new_dis = -1;
        for (Iterator<EdgeData> it = this.graph.edgeIter(node.getKey()); it.hasNext();) { //iterate over all adjacent nodes
            EdgeData e = it.next();
            NodeData node_dest = this.graph.getNode(e.getDest()); //neighbor node

            if (!visited.contains(node_dest)) {
                current_dis = e.getWeight();
                new_dis = current_dis + node.getWeight(); //calculate new dis

                if (new_dis < node_dest.getWeight()) { // if it's lower update it as the new lowest cost path
                    node_dest.setWeight(new_dis);
                    node_dest.setTag(node.getKey()); // set the tag to its father. used in shortestPath
                }
                p.add(node_dest); // add to pq
            }
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
     * nodes to each node that computes the shortest path using the tags.
     * It will add the parents to a list and return it.
     * @param src  - start node
     * @param dest - end node
     * @return list of the shortest path nodes
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> list1 = new ArrayList<>(); //shortest path array list from end node to start node
        List<NodeData> list2 = new ArrayList<>(); // shortest path array list from start node to end node
        shortestPathDist(src, dest); // compute all the tags
        int parent = dest;
        while (parent != src) { // we go back from dest to src and add each parent to the list
            list1.add(graph.getNode(parent));
            parent = graph.getNode(dest).getTag();
            dest = graph.getNode(parent).getKey();
        }
        list1.add(graph.getNode(src));
        for (int i = 0; i < list1.size(); i++) { //reverse the list
            list2.add(list1.get(list1.size() - i - 1));
        }

        return list2;
    }

    /**
     * This function finds the NodeData which minimizes the max distance to all the other nodes. The algorithm to finding the
     * center is to compute all the minimal distances from each node to any other node using dijkstra. Then we will take
     * the maximum distance of all the minimals. The node which refers to this distance is the center distance.
     * @return the node representing the center node
     */
    @Override
    public NodeData center() {
        if (!isConnected()) { //if the graph is not connected there is not a valid center
            return null;
        }
        NodeData node_center = null;
        double maxDist = Integer.MAX_VALUE;
        Iterator<NodeData> nodeDataIterator = graph.nodeIter(); //iterate over all nodes
        while (nodeDataIterator.hasNext()) {
            int node = nodeDataIterator.next().getKey(); //next node's key
            dijkstra(graph, graph.getNode(node)); //apply dijkstra to find all the minimal distances
            double temp = max(); //get the maximum distance
            if (temp < maxDist) { // check if it's the minimal and update it.
                maxDist = temp;
                node_center = this.graph.getNode(node);// finally we get the minimal of the maximum distances
            }
        }
        return node_center;
    }

    /**
     * This function takes the maximum weight from a list of weights representing the distances to each node.
     * @return
     */
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
    public List<NodeData> tsp(List<NodeData> cities) {
        for (int i = 0; i < cities.size(); i++) {
            if (this.graph.getNode(cities.get(i).getKey()) == null)
                return null;
        }
        if (!isConnected()){
            return null;
        }

        if (cities.size() > this.graph.nodeSize() || cities.size() == 0){
            return null;
        }
        List<NodeData> list_cities = new ArrayList<>();
        double min_list = Integer.MAX_VALUE;
        List<NodeData> list_cities_temp = new ArrayList<>();
        List<NodeData> list = new ArrayList<>();
        double min_list_temp = 0;
        for (int i = 0; i < cities.size(); i++) {//O(V)
            boolean first_time = false;
            list_cities_temp.clear();//O(V)
            List<NodeData> cities_temp = new ArrayList<>();
            cities_temp.addAll(cities);//O(V)
            min_list_temp = 0;
            int curr_node = i;
            int loc = i;
            while (cities_temp.size() > 1) {//O(V)
                int key = cities_temp.get(loc).getKey();//curr_node
                cities_temp.remove(cities_temp.get(loc));//curr_node
                dijkstra(this.graph, this.graph.getNode(key));//(O(V+ElogV)
                double min = Integer.MAX_VALUE;
                for (int j = 0; j < cities_temp.size(); j++) {//O(V)
                    if (min > this.graph.getNode(cities_temp.get(j).getKey()).getWeight()) {
                        min = this.graph.getNode(cities_temp.get(j).getKey()).getWeight();
                        curr_node = cities_temp.get(j).getKey();
                        loc = j;
                    }
                }
                if(min == Integer.MAX_VALUE){
                    break;
                }
                min_list_temp += min;

                //save the list of the minimum path
                list = shortestPath_tsp(key, curr_node);//O(V)
                if (first_time){
                    list.remove(0);
                }
                first_time = true;
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


    /**
     * This function saves a graph to a json file.
     * @param file - the file name.
     * @returntrue if succeeds. false if not
     */
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

    /**
     * This function loads a json file to a graph
     * @param file - file name of JSON file
     * @return true is succeeds. false if not
     */
    @Override
    public boolean load(String file) {
        JsonParser jsonParser = new JsonParser();
        JsonParser jsonParser2 = new JsonParser();
        try {
            FileReader reader = new FileReader(file);
            FileReader reader2 = new FileReader(file);
            JsonReader jsonReader = new JsonReader(reader);
            JsonReader jsonReader2 = new JsonReader(reader2);

            JsonArray graph2 = (JsonArray) jsonParser2.parse(jsonReader2).getAsJsonObject().get("Nodes");
            graph2.forEach(node -> parseNodeObject((JsonObject) node));
            JsonArray graph = (JsonArray) jsonParser.parse(jsonReader).getAsJsonObject().get("Edges");
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
