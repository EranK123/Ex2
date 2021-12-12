# Ex2 Readme 212727283 - 315907113

In this assignment we are implementing a Directed Weighted Graph. Given a set of interfaces which include: 

**Location** - This class implements the **GeoLocation** interface a 3D point i.e x,y,z values.

**Node** - This class implements the **NodeData** interface, a vertex in a graph. The node will be represented as a key and a GeoLocation.

**Edge** - This class implements the **EdgeData** interface, an edge in a graph. The edge will be represented as an index of the source node, index of the destination node and weight of the edge.

**Graph** - This class implements the **DirectedWeightedGraph** interface. We will represent each graph as two HashMaps. 
The first HashMap will represent the nodes. The HashMap will consist of an Integer key and a NodeData for the  value. The key will represent the node’s id. The signature will look like this: HashMap<Integer, NodeData>.
In this way we will have easy access to all the nodes in the graph. Getting a node is executed in O(1) time complexity. Removing a node is also O(1) in this HashMap but in the second HashMap we will need to remove all the edges connected to this node. assuming we have k edges connected, time complexity will be O(k). Adding nodes will also be done in O(1) complexity using HashMap attributes. To iterate over the nodes we can simply use the HashMap iterator. 
As seen above, we can implement a database to our nodes and use it in the best time complexity possible.
The second HashMap will represent the edges in the graph. As we know an edge is composed of a source node’s id, destination node’s id and weight. To represent each edge we will construct a HashMap inside a HashMap. The first HashMap will consist of an Integer for the key which will represent a source node’s id, and a HashMap for the value. The latter will consist of an Integer for the key as well which this time will represent a node’s destination id. The value will be an EdgeData. The signature will look like this HashMap<Integer, HashMap<Integer,EdgeData>>. In this way we will have easy access to all the edges of a given source node’s id. Getting and removing an edge will be executed in O(1) complexity assuming we have the node’s source id and node’s destination id. Connecting a new edge will also be done in O(1). Assuming we have both nodes as before and a weight, we can simply access the source node’s edges in the HashMap and then add in the destination position the new edge. To iterate over the edges we can use the HashMap iterator. 
In addition, we have another HashMap as a field in the graph only used for remvoving a node in O(k) time complexity. This HashMap will represent the reverse edges. The edges that go into the node. The signature will look like this HashMap<Integer, HashMap<Integer,EdgeData>> where at this time, the first Integer value is the destination node and the second is the source node.

**GraphAlgo** -  This class implements the **DirectedWeightedGraphAlgorithms** interface. This class represents a directed weighted graph and implements Algorithms including: init, getGraph, copy, isConnected, shortestPathDist, shortestPath, center, tsp(Travelling Salesman Problem)  and save load with JSON file.
The only field in this class will be a Graph which implements DirectedWeightedGraph.

*init* - Initializing the Graph with another given Graph.

*getGraph* - Returns the graph.

*copy* - Deep copies a graph. Returns it.

*isConnected* - Checks if the directed graph is connected, in other words, there is an existing route from each node to any other node in the graph. Another explanation is that the graph is one strongly connected component. Returns true or false if connected or not.
We will use the Breadth-First Search algorithm. Breadth-first search is an algorithm for traversing or searching tree or graph data structures. The algorithm should start traversing from a selected node (source or starting node) and traverse the graph layerwise thus exploring the neighbour nodes (nodes which are directly connected to source node). You must then move towards the next-level neighbour nodes.

*shortestPathDist* - Computes the lengh of shortest path between a source node to a destination node and returns it. This will be determined by the weights of the edges.
We will use the Dijkstra Algorithm. This algorithm finds the shortest path between node a and node b. It picks the source node, calculates the distance through it to each unvisited neighbor, and updates the neighbor's distance if smaller. Mark visited when done with neighbors. 

*shortestPath* - Computes the shortest path between a source node to a destination node. It returns a list of Nodes representing the path. The list is ordered. This method uses the shortestPathDist function. In short, we save the parent of each node from the shortestPath in a tag variable which is defined in the Node class.

*center* - Returns the Node center of the graph. The center of a graph is the set of all vertices of minimum eccentricity, that is, the set of all vertices u where the greatest distance (weight) d(u,v) to other vertices v is minimal. Thus vertices in the center minimize the maximal distance from other points in the graph.
We will use the dijkstra method of each node to compute the distances to all nodes in the graph. 

*tsp* - given a list of nodes called cities, a sub list of nodes in the graph, the method computes a list of consecutive nodes which go over all the nodes in cities. The sum of the weights of all the consecutive (pairs) of nodes is the "cost" of the solution. //TBA

*save* - saves the weighted graph to a json file given by the function. Returning true if successful, false if not.

*load*-  reads the json file given by the function and initializes the data to a graph. Returning true if successful, false if not.

For each function defined in the main classes Graph and GraphAlgo we will make a test in Junit. We will create a graph and test all the functions. The testers are extremely useful to check the functions on the go, so we can check each method before using it in other functions.


Here are the results for running the algorithm on 1000, 10000 and 100000 nodes:


| 1000 Nodes | 10000 Nodes  | 100000 Nodes |
| --------------- | --------------- | --------------- |
| isConnected - 777 ms | isConnected - 50 sec 230 ms | isConnected - over 10 minutes |
| shortestPathDist - 281 ms| shortestPathDist - 805 ms| shortestPathDist -  9 sec 619 ms|
| shortestPath - 232 ms| shortestPath - 743 ms| shortestPath - 10 sec 250 ms|
| center - 2 sec| center - 3 min 23 sec| center - timeout|
| tsp - | tsp - | tsp - |


In addition to the graph implementation, we have implemented a graphical user interface using java swing library. Using the classes Draw, Menu, DisplayMessage and GUI there is a functional user interface that displays a graph. The interface allows the user to load a graph from a json file and display it. Save a graph to a json file. Edit the graph and run algorithms on it. This all can be done with the help of the classes we created.  
**Draw**: draws the graph in a 2d manor, displaying the nodes of the graph and the edges.

**Menu**: adds a menu. The user can do actions on the graph via the menu like the actions specified above.

**DisplayMessage**: a help class that allows pop ups messages. Only used in Menu.

**GUI**: the class that takes charge of combining all the class functionalities to one function in the graph called run. In this function we create a new Draw and a new Menu and add it to the main frame. Finally when we run this method the full frame with the graph and the menu should appear.




   


  


