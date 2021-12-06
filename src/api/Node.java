package api;


import java.util.ArrayList;

public class Node implements NodeData {
    private int tag;
    private int key;
    private Location nodeLocation;
    private double weight;

    public Node(int key, Location nodeLocation){
        this.key = key;
        this.nodeLocation = new Location(nodeLocation.x(), nodeLocation.y(), nodeLocation.z());
    }


    public Node(Node ot){
        this.nodeLocation = ot.nodeLocation;
        this.key = ot.key;
    }

    @Override
    public int getKey() {
        return this.key;
    }

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
