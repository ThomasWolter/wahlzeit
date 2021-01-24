package org.wahlzeit.model;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CartoonCharPhotoTest {

    private final String name = "Donald Duck", artstyle = "classic";
    private final int debut = 1934;

    @Test
    public void testConstructor() {
        CartoonCharPhoto photo = new CartoonCharPhoto(name, debut, artstyle);
        assertEquals(photo.getCartoonChar().getCartoonCharType().getName(), name);
        assertEquals(photo.getCartoonChar().getCartoonCharType().getDebut(), debut);
        assertEquals(photo.getCartoonChar().getArtstyle(), artstyle);
    }

    /**
     * Test null as argument
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor() throws SQLException {
        // pass null as an argument
        CartoonCharPhoto photo = new CartoonCharPhoto((PhotoId) null);
        CartoonCharPhoto photo2 = new CartoonCharPhoto((ResultSet) null);

    }

    /**
     * Test readFrom with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReadFrom() throws SQLException {
        // pass null as an argument
        CartoonCharPhoto photo = new CartoonCharPhoto(name, debut, artstyle);
        photo.readFrom(null);
    }

    /**
     * Test writeOn with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWriteOn() throws SQLException {
        // pass null as an argument
        CartoonCharPhoto photo = new CartoonCharPhoto(name, debut, artstyle);
        photo.writeOn(null);
    }

}