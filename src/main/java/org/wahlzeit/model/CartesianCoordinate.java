package org.wahlzeit.model;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A coordinate represents the cartesian interpretation of the location
 */
public class CartesianCoordinate extends AbstractCoordinate{

    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;

    /**
     *
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
        // preconditions
        assertDouble(x);
        assertDouble(y);
        assertDouble(z);
        this.x = x;
        this.y = y;
        this.z = z;
        this.assertClassInvariant();
    }

    /**
     *
     * @methodtype constructor
     */
    public CartesianCoordinate(ResultSet rset) throws SQLException {
                this.x = rset.getDouble("x_phi");
                this.y = rset.getDouble("y_theta");
                this.z = rset.getDouble("z_radius");
                this.assertClassInvariant();
    }

    /**
     *
     * @methodtype set
     */
    public void setX(double x) {
        assertDouble(x);
        this.x = x;
    }

    /**
     *
     * @methodtype set
     */
    public void setY(double y) {
        assertDouble(y);
        this.y = y;
    }

    /**
     *
     * @methodtype set
     */
    public void setZ(double z) {
        assertDouble(z);
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
        assertClassInvariant();
        return this;
    }

    /**
     * Converts the local coordinates into spheric coordinates and returns a new object.
     * Formula: r = sqrt(x^2 + y^2 + z^2), phi = arctan(y/x), theta = arctan(sqrt(x^2 + y^2)/z)
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariant();
        // Convert cartesian coordinates
        double radius = Math.sqrt(Math.pow(this.x, 2) +  Math.pow(this.y, 2) +  Math.pow(z, 2));
        double phi = Math.atan(this.y/this.x);
        double theta = Math.atan(Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2))/this.z);
        // Create new object
        // Create new object
        SphericCoordinate coordinate = new SphericCoordinate(phi, theta, radius);
        coordinate.assertClassInvariant();
        return coordinate;
    }

    /**
     * Checks if this objects variables are valid
     */
    public void assertClassInvariant() {
        assertDouble(getX());
        assertDouble(getY());
        assertDouble(getZ());
    }


}
