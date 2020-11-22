package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocationTest {

    private Location location;
    // for init test
    private final double x = 1.,y = 2., z = 3.;

    @Before
    public void initLocation() {
        location = new Location(x,y,z);
    }

    @Test
    public void testConstructor() {
        // Check if location was initialized
        assertNotNull(location);

        // Check properties after creation
        assertEquals(x, location.getCoordinate().getX(),0.0001);
        assertEquals(y, location.getCoordinate().getY(),0.0001);
        assertEquals(z, location.getCoordinate().getZ(),0.0001);
    }

    /**
     *
     */
    @Test
    public void testSetNewValues() {
        // Check if  Coordinates are set correctly
        double a = 1., b = 2., c = 3.;
        location.setCoordinate(a,b,c);
        assertEquals(a, location.getCoordinate().getX(),0.0001);
        assertEquals(b, location.getCoordinate().getY(),0.0001);
        assertEquals(c, location.getCoordinate().getZ(),0.0001);
    }

}
