package org.wahlzeit.model;

/**
 * A coordinate represents the cartesian interpretation of the location
 */
public class Coordinate {

    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;

    /**
     *
     * @methodtype constructor
     */
    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     * @methodtype set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @methodtype set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     *
     * @methodtype set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     *
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     * Determines the distance between local coordinate (x,y,z) and argument (a,b,c)
     * Formula used: sqrt(pow(a-x) + pow(b-y) + pow(c-z))
     * @param coordinate cartesian coordinates to measure the distance from
     * @return the distance between the two coordinates
     */
    public double getDistance(Coordinate coordinate){
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument");
        }
        // Calculate the value
        double distance = Math.sqrt(Math.pow(coordinate.getX() - this.x, 2) +
                Math.pow(coordinate.getY() - this.y, 2) +
                Math.pow(coordinate.getZ() - this.z, 2));
        return distance;
    }

    /**
     * Determines if local coordinate is equal to the argument
     * @param  coordinate  cartesian coordinates to compare to this objects cartesian coordinates
     * @return result of evaluation
     */
    public boolean isEqual(Coordinate coordinate){
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument");
        }
        // Check if all coordinates are equal
        return coordinate.x == this.x && coordinate.y == this.y && coordinate.z == this.z;
    }
}
