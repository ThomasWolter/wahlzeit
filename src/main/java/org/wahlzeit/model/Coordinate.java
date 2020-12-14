package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * A coordinate represents the cartesian interpretation of the location
 */
public interface Coordinate {

    // Returns the name of the coordinate system
    public String getSystem();

    // Handles database update
    public void writeOn(ResultSet rset) throws SQLException;

    public CartesianCoordinate asCartesianCoordinate();
    public SphericCoordinate asSphericCoordinate();

    public double getCartesianDistance(Coordinate coordinate);
    public double getCentralAngle(Coordinate coordinate);

    public boolean isEqual(Coordinate coordinate);

    // Checks
    public void assertDouble(double toCheck);
    public void assertNull(Coordinate toCheck);
    public void assertClassInvariant();

    }