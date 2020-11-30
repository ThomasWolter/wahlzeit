package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {
    // Variables for comparison
    private final double x = 1., y = 2., z = 3.;
    double radius = 3.7416573867739, theta = 0.64052231267943, phi = 1.107148717794;
    private final double a = 2., b = 3., c = 4.;
    double radius1 = 5.3851648071345, theta1 = 0.73358132364008, phi1 = 0.98279372324733 ;
    // Coordinate for init and set test
    private CartesianCoordinate coordinate;
    // Coordinates for getDistanceTest (cartesian)
    private CartesianCoordinate coordinate1c = new CartesianCoordinate(x,y,z);
    private CartesianCoordinate coordinate2c = new CartesianCoordinate(a,b,c);
    // Coordinates for isEqualTest
    private CartesianCoordinate coordinate3c = new CartesianCoordinate(a,b,c);
    // Coordinates for getDistanceTest (spheric)
    private SphericCoordinate coordinate1s = new SphericCoordinate(phi,theta,radius);
    private Coordinate coordinate2s = new SphericCoordinate(phi1,theta1,radius1);
    // Coordinates for isEqualTest
    private Coordinate coordinate3s = new SphericCoordinate(a,b,c);

    @Before
    public void initLocation() {
        // base coordinate
        coordinate = new CartesianCoordinate(x,y,z);
        coordinate1c  = new CartesianCoordinate(x,y,z);
        coordinate1s = new SphericCoordinate(phi,theta,radius);
    }

    /**
     *
     */
    @Test
    public void testConstructor() {
        // Check if location was initialized
        assertNotNull(coordinate);
        assertNotNull(coordinate1c);
        assertNotNull(coordinate1s);
    }

    /**
     *
     */
    @Test
    public void testSetNewValues() {
        // Check if x,y,z values are set correctly
        coordinate.setX(x);
        assertEquals(x, coordinate.getX(),0.0001);
        coordinate.setY(y);
        assertEquals(y, coordinate.getY(),0.0001);
        coordinate.setZ(z);
        assertEquals(z, coordinate.getZ(),0.0001);
    }


    /**
     * Test getDistance()
     */
    @Test
    public void testCoordinateConverter() {
        boolean isEqual = false;
        // Test if value is calculated correctly
        CartesianCoordinate test = coordinate1s.asCartesianCoordinate();
        assertTrue(test.isEqual(coordinate1c));
        assertTrue(test.isEqual(coordinate1s));
        SphericCoordinate testy = coordinate1s.asSphericCoordinate();
        assertTrue(testy.isEqual(coordinate1s));
        assertTrue(testy.isEqual(coordinate1c));
    }

    /**
     * Test getDistance()
     */
    @Test
    public void testGetDistance() {
        // Test if exception is thrown when null is passed as argument
        try {
            coordinate1c.getCartesianDistance(null);
            coordinate1s.getCartesianDistance(null);
            fail("Null is not caught as an argument in getDistance()");
        } catch (IllegalArgumentException e) {
            // Everything works fine
        }

        // Test if value is calculated correctly
        double getDistance = coordinate1c.getCartesianDistance(coordinate2c);
        // Use formula directly
        double ax = Math.pow(coordinate1c.getX() - coordinate2c.getX(), 2);
        double by = Math.pow(coordinate1c.getY() - coordinate2c.getY(), 2);
        double cz = Math.pow(coordinate1c.getZ() - coordinate2c.getZ(), 2);
        double formula = Math.sqrt(ax + by + cz);
        assertEquals(getDistance, formula, 0.0001);

        // Test distance with spherical coordinates
        getDistance = coordinate1s.getCartesianDistance(coordinate2s);
        assertEquals(getDistance, formula, 0.0001);


    }

    /**
     * Test isEqual()
     */
    @Test
    public void testIsEqual(){
        // Test if two different coordinates return false
        boolean isEqual = coordinate1c.isEqual(coordinate2c);
        assertFalse(isEqual);
        // Test if two equal coordinates return true
        isEqual = coordinate2c.isEqual(coordinate3c);
        assertTrue(isEqual);
        // Test if two equal coordinates in different systems return true
        isEqual = coordinate1c.isEqual(coordinate1s);
        assertTrue(isEqual);
    }

    /**
     * Test equal() override
     */
    @Test
    public void testEqualOverride(){
        // Test if equal() and isEqual() return the same values for two different coordinates
        boolean isEqual = coordinate1c.isEqual(coordinate2c);
        boolean equals = coordinate1c.equals(coordinate2c);
        assertEquals(isEqual, equals);
        // Test if hashCode() return the same values for two different coordinates
        int hash1 = coordinate1c.hashCode();
        int hash2 = coordinate2c.hashCode();
        assertNotEquals(hash1, hash2);
        // Test if equal() and isEqual() return the same values for two equal coordinates
        isEqual = coordinate2c.isEqual(coordinate3c);
        equals = coordinate2c.equals(coordinate3c);
        assertEquals(isEqual, equals);
        // Test if hashCode() return the same values for two equal coordinates
        hash1 = coordinate3c.hashCode();
        hash2 = coordinate2c.hashCode();
        assertEquals(hash1, hash2);
    }

}
