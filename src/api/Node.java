package api;


import java.util.ArrayList;

/**
 * This class implements NodeData which represent a node in a graph.
 */
public class Node implements NodeData {
    private int tag;
    private int key;
    private Location nodeLocation;
    private double weight;

    /**
     * Node init
     * @param key - node's id
     * @param nodeLocation - node's GeoLocation
     */
    public Node(int key, Location nodeLocation){
        this.key = key;
        this.nodeLocation = new Location(nodeLocation.x(), nodeLocation.y(), nodeLocation.z());
    }

    /**
     * Copy constructor
     * @param ot - other node
     */
    public Node(Node ot){
        this.nodeLocation = ot.nodeLocation;
        this.key = ot.key;
        this.tag = ot.tag;
        this.weight = ot.weight;
    }

    /**
     * @return the id of the node
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * @return the node's location in space.
     */
    @Override
    public GeoLocation getLocation() {
        if (nodeLocation == null){
            return null;
        }
        return this.nodeLocation;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.nodeLocation.setX(p.x());
        this.nodeLocation.setY(p.y());
        this.nodeLocation.setZ(p.y());
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
    this.tag = t;
    }

}
