package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SphericCoordinate extends AbstractCoordinate{

    // HashMap to cache all initiated coordinates
    private static final Map<Integer, SphericCoordinate> sphericCache = new HashMap();

    private double phi = 0.0;
    private double theta = 0.0;
    private double radius = 0.0;

    /**
     *
     * @methodtype constructor
     */
    private SphericCoordinate(double phi, double theta, double radius) {
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
    public static SphericCoordinate getSphericCoordinateRset(ResultSet rset) throws SQLException {
        double phi_db = rset.getDouble("x_phi");
        double theta_db = rset.getDouble("y_theta");
        double radius_db = rset.getDouble("z_radius");
        return getSphericCoordinate(phi_db, theta_db, radius_db);
    }

    public static SphericCoordinate getSphericCoordinate(double phi, double theta, double radius) {
        // Create a new cartesian coordinate and handle caching
        SphericCoordinate sphericCoordinate = new SphericCoordinate(phi,theta,radius);
        int hashCoordinate = sphericCoordinate.hashCode();
        SphericCoordinate result = sphericCache.get(hashCoordinate);
        if (result == null) {
            synchronized (sphericCache){
                sphericCache.put(hashCoordinate, sphericCoordinate);
                result = sphericCache.get(hashCoordinate);
            }
        }
        return result;
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
     * @methodtype get
     */
    public double getTheta() {
        return theta;
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
        CartesianCoordinate coordinate = CartesianCoordinate.getCartesianCoordinate(x, y, z);
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
