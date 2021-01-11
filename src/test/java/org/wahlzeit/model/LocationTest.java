package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocationTest {

    private Location location, location1;
    // for init test
    private final double x = 1.,y = 2., z = 3.;
    private final double radius = 3.7416573867739, theta = 0.64052231267943, phi = 1.107148717794;

    @Before
    public void initLocation() {
        location = new Location(x,y,z, "cartesian");
        location1 = new Location(phi, theta, radius, "spheric");
    }

    @Test
    public void testConstructor() {
        // Check if location was initialized
        assertNotNull(location);
        assertNotNull(location1);
    }


}
