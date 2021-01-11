package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A location associated with the photo
 */
public class Location {


    private Coordinate coordinate;

    /**
     *
     * @methodtype constructor
     * I set the values to 0, as it was stated that we don't work on the logic
     */
    public Location(double x, double y, double z, String system) {
        if (system.equals("cartesian")){
            this.coordinate = CartesianCoordinate.getCartesianCoordinate(x, y, z);
        } else {
            this.coordinate = SphericCoordinate.getSphericCoordinate(x, y, z);
        }
    }

    public Location(ResultSet rset) throws SQLException {
        String system = rset.getString("system");
        if (system.equals("cartesian")){
            this.coordinate = CartesianCoordinate.getCartesianCoordinateRset(rset);
        } else {
            this.coordinate = SphericCoordinate.getSphericCoordinateRset(rset);
        }
    }

    /**
     *
     * @methodtype set
     */
    public void setCoordinate(double x, double y, double z, String system) {
        if (system.equals("cartesian")){
            this.coordinate = CartesianCoordinate.getCartesianCoordinate(x, y, z);
        } else {
            this.coordinate = SphericCoordinate.getSphericCoordinate(x, y, z);
        }
    }

    /**
     *
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

}
