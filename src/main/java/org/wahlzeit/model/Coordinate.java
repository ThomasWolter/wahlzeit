package org.wahlzeit.model;

import java.util.Objects;

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
     * @throws IllegalArgumentException If null is passed as an argument
     */
    public double getDistance(Coordinate coordinate){
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument");
        }
        // Calculate the value
        // ax = pow(a-x), by = pow(b-y), cz = pow(c-z)
        double ax = Math.pow(coordinate.getX() - this.x, 2);
        double by = Math.pow(coordinate.getY() - this.y, 2);
        double cz = Math.pow(coordinate.getZ() - this.z, 2);
        return Math.sqrt(ax + by + cz);
    }

    /**
     * Determines if local coordinate is equal to the argument
     * @param  coordinate  cartesian coordinates to compare to this objects cartesian coordinates
     * @return result of evaluation
     * @throws IllegalArgumentException If null is passed as an argument
     */
    public boolean isEqual(Coordinate coordinate){
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument");
        }
        // Check if all coordinates are equal
        boolean xCompare = (Math.abs(coordinate.x - this.x) <= 0.0001);
        boolean yCompare = (Math.abs(coordinate.y - this.y) <= 0.0001);
        boolean zCompare = (Math.abs(coordinate.z - this.z) <= 0.0001);
        return xCompare && yCompare && zCompare;
    }

    /**
     * Forward equals() to isEqual() by overriding it
     */
    @Override
    public boolean equals(Object obj){
        // Check if obj is actually a coordinate
        if (!(obj instanceof Coordinate)){
            return false;
        }
        return isEqual((Coordinate) obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
