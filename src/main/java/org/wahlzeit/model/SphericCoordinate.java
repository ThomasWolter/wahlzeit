package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SphericCoordinate implements Coordinate{

    private double phi = 0.0;
    private double theta = 0.0;
    private double radius = 0.0;

    /**
     *
     * @methodtype constructor
     */
    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    /**
     *
     * @methodtype constructor
     */
    public SphericCoordinate(ResultSet rset) throws SQLException {
        this.phi = rset.getDouble("x_phi");
        this.theta = rset.getDouble("y_theta");
        this.radius = rset.getDouble("z_radius");
    }

    /**
     *
     * @methodtype get
     */
    public double getPhi() {
        return phi;
    }

    /**
     *
     * @methodtype set
     */
    public void setPhi(double phi) {
        this.phi = phi;
    }

    /**
     *
     * @methodtype get
     */
    public double getTheta() {
        return theta;
    }

    /**
     *
     * @methodtype set
     */
    public void setTheta(double theta) {
        this.theta = theta;
    }

    /**
     *
     * @methodtype get
     */
    public double getRadius() {
        return radius;
    }

    /**
     *
     * @methodtype set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     *
     * @methodtype get
     */
    public String getSystem() {
        return "spheric";
    }


    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateString("system",  getSystem());
        rset.updateDouble("x_phi",  phi);
        rset.updateDouble("y_theta",  theta);
        rset.updateDouble("z_radius",  radius);
    }

    /**
     * Converts the local coordinates into cartesian coordinates and returns a new object.
     * Formula: x = r*sin(theta)*cos(phi), x = r*sin(theta)*sin(phi), z = r*cos(theta)
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        // Convert spheric coordinates
        double x = this.radius * Math.sin(this.theta) * Math.cos(this.phi);
        double y = this.radius * Math.sin(this.theta) * Math.sin(this.phi);
        double z = this.radius * Math.cos(this.theta);
        // Create new object
        return new CartesianCoordinate(x, y, z);
    }

    /**
     * Does nothing in this case.
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     * Converts coordinates to cartesian and determines the distance between local coordinate (x,y,z) and argument (a,b,c)
     * Formula used: sqrt(pow(a-x) + pow(b-y) + pow(c-z))
     * @param coordinate spheric coordinates to measure the distance from
     * @return the distance between the two coordinates
     * @throws IllegalArgumentException If null is passed as an argument
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument");
        }
        // Convert the spheric coordinates to cartesian coordinates
        CartesianCoordinate cCoordinate1 = this.asCartesianCoordinate();
        // Call the cartesian coordinate function
        return cCoordinate1.getCartesianDistance(coordinate);
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
        SphericCoordinate sCoordinate = coordinate.asSphericCoordinate();
        // Calculate the value
        // TODO
        return 0.;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument for isEqual()");
        }
        // Make sure the argument is in spheric form
        SphericCoordinate sCoordinate = coordinate.asSphericCoordinate();

        // Check if all coordinates are equal
        boolean xCompare = (Math.abs(sCoordinate.phi - this.phi) <= 0.0001);
        boolean yCompare = (Math.abs(sCoordinate.theta - this.theta) <= 0.0001);
        boolean zCompare = (Math.abs(sCoordinate.radius - this.radius) <= 0.0001);
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
        return isEqual((SphericCoordinate) obj);
    }

    /**
     * Make sure the contract between equals() and hashCode() is kept
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.phi, this.theta, this.radius);
    }
}
