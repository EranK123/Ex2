package api;

/**
 * This interface implements GeoLocation which represents the location of a node. <x,y,z>, (aka Point3D data).
 */
public class Location implements api.GeoLocation {
    private double x;
    private double y;
    private double z;

    /**
     * Location init
     * @param x
     * @param y
     * @param z
     */
    public Location(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
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

    /**
     * calculates the distance between two nodes
     * @param g
     * @return the distance
     */
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
