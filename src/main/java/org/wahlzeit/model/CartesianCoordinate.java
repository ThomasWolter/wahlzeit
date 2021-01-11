package org.wahlzeit.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * A coordinate represents the cartesian interpretation of the location
 */
public class CartesianCoordinate extends AbstractCoordinate{

    // HashMap to cache all initiated coordinates
    private static final Map<Integer, CartesianCoordinate> cartesianCache = new HashMap();

    private final double x;
    private final double y;
    private final double z;


    /**
     *
     * @methodtype constructor
     * Add new entry to chache HashMap
     */
    private CartesianCoordinate(double x, double y, double z) {
        // preconditions
        assertDouble(x);
        assertDouble(y);
        assertDouble(z);
        // set values
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
        // Create a new cartesian coordinate and handle caching
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x,y,z);
        int hashCoordinate = cartesianCoordinate.hashCode();
        CartesianCoordinate result = cartesianCache.get(hashCoordinate);
        if (result == null) {
            synchronized (cartesianCache){
                cartesianCache.put(hashCoordinate, cartesianCoordinate);
                result = cartesianCache.get(hashCoordinate);
            }
        }
        return result;
    }


    /**
     *
     * @methodtype constructor
     */
    public static CartesianCoordinate getCartesianCoordinateRset(ResultSet rset) throws SQLException {
                double x_db = rset.getDouble("x_phi");
                double y_db = rset.getDouble("y_theta");
                double z_db = rset.getDouble("z_radius");
                return getCartesianCoordinate(x_db, y_db, z_db);
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
        SphericCoordinate coordinate = SphericCoordinate.getSphericCoordinate(phi, theta, radius);
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
