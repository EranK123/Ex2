package api;
import api.GeoLocation;

public class Node implements api.NodeData{

    private Location node_location;
    private int key;
    private int tag;

    public Node(){
        this.node_location = null;
        this.key = 0;
        this.tag = 0;
    }

    public Node(int key, int tag,  Location g){
        this.node_location = new Location(g);
        this.key = key;
        this.tag = tag;
    }

    public Node(NodeData nodeData) {
        this.node_location = (Location) nodeData.getLocation();
        this.key = nodeData.getKey();
        this.tag = nodeData.getTag();
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public GeoLocation getLocation() {
        if (node_location == null)
            return null;
        return node_location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.node_location.setX(p.x());
        this.node_location.setY(p.y());
        this.node_location.setZ(p.z());
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public void setWeight(double w) {

    }

    @Override
    public String getInfo() {
        return "pos: " + this.node_location.x() + "," + this.node_location.y()
                + "," + this.node_location.z() + "\n" +
                "id: " + this.key;
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
