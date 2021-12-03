package api;

public class Location implements api.GeoLocation {

    private double x;
    private double y;
    private double z;

    public Location(){
        this.x = 0;
        this.y = 0;
        this.z = 0;

    }
    public Location(double _x, double _y, double _z){
        this.x = _x;
        this.y = _y;
        this.z = _z;
    }

    public Location(Location g){
        this.x = g.x;
        this.y = g.y;
        this.z = g.z;
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public double distance(api.GeoLocation g) {
       double dis_x = Math.pow(this.x - g.x(), 2);
       double dis_y = Math.pow(this.y - g.y(), 2);
       double dis_z = Math.pow(this.z - g.z(), 2);
       double dist = Math.sqrt((dis_x + dis_y + dis_z));
        return dist;
    }
}
