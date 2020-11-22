package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    // Variables for comparison
    private final double x = 1., y = 2., z = 3.;
    private final double a = 2., b = 3., c = 4.;
    // Coordinate for init and set test
    private Coordinate coordinate;
    // Coordinates for getDistanceTest
    private Coordinate coordinate1 = new Coordinate(x,y,z);
    private Coordinate coordinate2 = new Coordinate(a,b,c);
    // Coordinates for isEqualTest
    private Coordinate coordinate3 = new Coordinate(a,b,c);

    @Before
    public void initLocation() {
        // base coordinate
        coordinate = new Coordinate(x,y,z);
        coordinate1  = new Coordinate(x,y,z);
        coordinate2 = new Coordinate(a,b,c);
    }

    /**
     *
     */
    @Test
    public void testConstructor() {
        // Check if location was initialized
        assertNotNull(coordinate);

        // Check properties after creation
        assertEquals(x, coordinate.getX(),0.0001);
        assertEquals(y, coordinate.getY(),0.0001);
        assertEquals(z, coordinate.getZ(),0.0001);
    }

    /**
     *
     */
    @Test
    public void testSetNewValues() {
        // Check if x,y,z values are set correctly
        coordinate.setX(a);
        assertEquals(a, coordinate.getX(),0.0001);
        coordinate.setY(b);
        assertEquals(b, coordinate.getY(),0.0001);
        coordinate.setZ(c);
        assertEquals(c, coordinate.getZ(),0.0001);
    }

    /**
     * Test getDistance()
     */
    @Test
    public void testGetDistance(){
        // Test if exception is thrown when null is passed as argument
        try {
            coordinate.getDistance(null);
            fail("Null is not caught as an argument in getDistance()");
        } catch(IllegalArgumentException e) {
            // Everything works fine
        }

        // Test if value is calculated correctly
        double getDistance = coordinate1.getDistance(coordinate2);
        // Use formula directly
        double ax = Math.pow(coordinate1.getX() - coordinate2.getX(), 2);
        double by = Math.pow(coordinate1.getY() - coordinate2.getY(), 2);
        double cz = Math.pow(coordinate1.getZ() - coordinate2.getZ(), 2);
        double formula = Math.sqrt(ax + by + cz);
        assertEquals(getDistance, formula, 0.0001);
    }

    /**
     * Test isEqual()
     */
    @Test
    public void testIsEqual(){
        // Test if two different coordinates return false
        boolean isEqual = coordinate1.isEqual(coordinate2);
        assertFalse(isEqual);
        // Test if two equal coordinates return true
        isEqual = coordinate2.isEqual(coordinate3);
        assertTrue(isEqual);
    }

    /**
     * Test equal() override
     */
    @Test
    public void testEqualOverride(){
        // Test if equal() and isEqual() return the same values for two different coordinates
        boolean isEqual = coordinate1.isEqual(coordinate2);
        boolean equals = coordinate1.equals(coordinate2);
        assertEquals(isEqual, equals);
        // Test if hashCode() return the same values for two different coordinates
        int hash1 = coordinate1.hashCode();
        int hash2 = coordinate2.hashCode();
        assertNotEquals(hash1, hash2);
        // Test if equal() and isEqual() return the same values for two equal coordinates
        isEqual = coordinate2.isEqual(coordinate3);
        equals = coordinate2.equals(coordinate3);
        assertEquals(isEqual, equals);
        // Test if hashCode() return the same values for two equal coordinates
        hash1 = coordinate3.hashCode();
        hash2 = coordinate2.hashCode();
        assertEquals(hash1, hash2);
    }

}
