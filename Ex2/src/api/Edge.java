package api;

public class Edge implements api.EdgeData {

    private int src;
    private int dest;
    private double w;

    public Edge(){
        this.src = 0;
        this.dest = 0;
        this.w = 0;
    }
    public Edge(int s, int d, double w){
        this.src = s;
        this.dest = d;
        this.w = w;
    }
    public Edge(Edge e){
        this.src = e.src;
        this.dest = e.dest;
        this.w = e.w;
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
        return this.w;
    }

    @Override
    public String getInfo() {
        return "src: " + this.src + "dest: " + this.dest + "w: " + this.w;
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

    }
}
