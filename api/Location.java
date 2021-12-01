package api;

public class Location implements api.GeoLocation {
    private double x;
    private double y;
    private double z;

    public Location(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Location(){
    this.x = 0;
    this.y = 0;
    this.z = 0;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(api.GeoLocation g) {
        double calc = Math.pow(this.x - g.x(),2) + Math.pow(this.y - g.y(),2) + Math.pow(this.z - g.z(),2);
        return Math.sqrt(calc);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
