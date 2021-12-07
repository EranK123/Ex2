package api;

/**
 * This class implements EdgeData which represent an edge in a directed weighted graph.
 */
public class Edge implements EdgeData {

    private int src;
    private int dest;
    private double weight;

    /**
     * Edge init
     * @param src
     * @param dest
     * @param weight
     */
    public Edge(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * Copy constructor
     * @param ot - other edge
     */
    public Edge(Edge ot){
        this.src = ot.src;
        this.dest = ot.dest;
        this.weight = ot.weight;
    }

    /**
     * @return the source node's id
     */
    @Override
    public int getSrc() {
        return this.src;
    }
    /**
     * @return the destination node's id
     */
    @Override
    public int getDest() {
        return this.dest;
    }
    /**
     * @return the weight of the edge
     */
    @Override
    public double getWeight() {
        if (weight <= 0) {
            return -1;
        } else {
            return this.weight;
        }
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
        return 0;
    }

    @Override
    public void setTag(int t) {
       return;
    }

}
