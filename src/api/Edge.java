package api;

public class Edge implements EdgeData {

    private int src;
    private int dest;
    private double weight;


    public Edge(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public Edge(Edge ot){
        this.src = ot.src;
        this.dest = ot.dest;
        this.weight = ot.weight;
    }
    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

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