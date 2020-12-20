package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SphericCoordinate extends AbstractCoordinate{

    private double phi = 0.0;
    private double theta = 0.0;
    private double radius = 0.0;

    /**
     *
     * @methodtype constructor
     */
    public SphericCoordinate(double phi, double theta, double radius) {
        // preconditions
        assertDouble(phi);
        assertDouble(theta);
        assertDouble(radius);
        assertRadius(radius);
        // set values
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
        this.assertClassInvariant();
    }

    /**
     *
     * @methodtype constructor
     */
    public SphericCoordinate(ResultSet rset) throws SQLException {
        this.phi = rset.getDouble("x_phi");
        this.theta = rset.getDouble("y_theta");
        double radius_tmp = rset.getDouble("z_radius");
        assertRadius(radius_tmp);
        this.radius = radius_tmp;
        this.assertClassInvariant();
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
        assertDouble(phi);
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
        assertDouble(theta);
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
        assertDouble(radius);
        assertRadius(radius);
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
        this.assertClassInvariant();
        // Convert spheric coordinates
        double x = this.radius * Math.sin(this.theta) * Math.cos(this.phi);
        double y = this.radius * Math.sin(this.theta) * Math.sin(this.phi);
        double z = this.radius * Math.cos(this.theta);
        // Create new object
        CartesianCoordinate coordinate = new CartesianCoordinate(x, y, z);
        coordinate.assertClassInvariant();
        return coordinate;
    }

    /**
     * Does nothing in this case.
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariant();
        return this;
    }


    // Functions for assetion purposes

    /**
     * Checks if this objects variables are valid
     */
    public void assertClassInvariant() {
        assertDouble(getPhi());
        assertDouble(getTheta());
        assertDouble(getRadius());
        assertRadius(getRadius());
    }

    /**
     * Checks if the radius has a valid value
     * @param toCheck value to test
     */
    public void assertRadius(double toCheck){
        if (toCheck < 0) {
            throw new IllegalArgumentException("Radius can't be less than zero");
        }
    }

}
