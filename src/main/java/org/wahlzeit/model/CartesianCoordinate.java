package org.wahlzeit.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * A coordinate represents the cartesian interpretation of the location
 */
public class CartesianCoordinate implements Coordinate{

    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;

    /**
     *
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     * @methodtype constructor
     */
    public CartesianCoordinate(ResultSet rset) throws SQLException {
                this.x = rset.getDouble("x_phi");
                this.y = rset.getDouble("y_theta");
                this.z = rset.getDouble("z_radius");
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
     *
     * @methodtype get
     */
    public String getSystem() {
        return "cartesian";
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateString("system",  getSystem());
        rset.updateDouble("x_phi",  x);
        rset.updateDouble("y_theta",  y);
        rset.updateDouble("z_radius",  z);
    }

    /**
     * Does nothing in this case.
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     * Converts the local coordinates into spheric coordinates and returns a new object.
     * Formula: r = sqrt(x^2 + y^2 + z^2), phi = arctan(y/x), theta = arctan(sqrt(x^2 + y^2)/z)
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        // Convert cartesian coordinates
        double radius = Math.sqrt(Math.pow(this.x, 2) +  Math.pow(this.y, 2) +  Math.pow(z, 2));
        double phi = Math.atan(this.y/this.x);
        double theta = Math.atan(Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2))/this.z);
        // Create new object
        return new SphericCoordinate(phi, theta, radius);
    }

    /**
     * Determines the distance between local coordinate (x,y,z) and argument (a,b,c)
     * Formula used: sqrt(pow(a-x) + pow(b-y) + pow(c-z))
     * @param coordinate coordinates to measure the distance from
     * @return the distance between the two coordinates
     * @throws IllegalArgumentException If null is passed as an argument
     */
    public double getCartesianDistance(Coordinate coordinate){
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument");
        }
        // Make sure the argument is in cartesian form
        CartesianCoordinate sCoordinate = coordinate.asCartesianCoordinate();
        // Calculate the value
        // ax = pow(a-x), by = pow(b-y), cz = pow(c-z)
        double ax = Math.pow(sCoordinate.getX() - this.x, 2);
        double by = Math.pow(sCoordinate.getY() - this.y, 2);
        double cz = Math.pow(sCoordinate.getZ() - this.z, 2);
        return Math.sqrt(ax + by + cz);
    }

    /**
     * Converts coordinates to spheric and determines the distance between local coordinate (x,y,z) and argument (a,b,c)
     * Formula used: TODO
     * @param coordinate coordinates to measure the central angle from
     * @return the central angle between the two coordinates
     * @throws IllegalArgumentException If null is passed as an argument
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument");
        }
        // Convert the spheric coordinates to cartesian coordinates
        SphericCoordinate sCoordinate = this.asSphericCoordinate();
        // Calculate the value
        return sCoordinate.getCentralAngle(coordinate);
    }

    /**
     * Determines if local coordinate is equal to the argument
     * @param  coordinate  coordinate to compare to this objects coordinate
     * @return result of evaluation
     * @throws IllegalArgumentException If null is passed as an argument
     */
    public boolean isEqual(Coordinate coordinate){
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument for isEqual()");
        }
        // Make sure the argument is in cartesian form
        CartesianCoordinate cCoordinate = coordinate.asCartesianCoordinate();

        // Check if all coordinates are equal
        boolean xCompare = (Math.abs(cCoordinate.x - this.x) <= 0.0001);
        boolean yCompare = (Math.abs(cCoordinate.y - this.y) <= 0.0001);
        boolean zCompare = (Math.abs(cCoordinate.z - this.z) <= 0.0001);
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
        return isEqual((CartesianCoordinate) obj);
    }

    /**
     * Make sure the contract between equals() and hashCode() is kept
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y, this.z);
    }

}
