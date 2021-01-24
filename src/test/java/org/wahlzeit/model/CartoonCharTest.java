package org.wahlzeit.model;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CartoonCharTest {

    private final String name = "Donald Duck", artstyle = "classic";
    private final int debut = 1934;
    private final CartoonCharType type = new CartoonCharType(name, debut);



    /**
     * Test the constructor
     */
    @Test
    public void testConstructor() {
        CartoonChar char1 = new CartoonChar(type, artstyle);
        assertEquals(char1.getCartoonCharType().getName(), name);
        assertEquals(char1.getCartoonCharType().getDebut(), debut);

    }

    /**
     * Test type null argument
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTypeNull() throws SQLException {
        CartoonChar type1 = new CartoonChar(null, artstyle);
    }

    /**
     * Test null argument
     */
    @Test(expected = IllegalArgumentException.class)
    public void testArtNull() throws SQLException {
        CartoonChar type1 = new CartoonChar(type, null);
    }
}
