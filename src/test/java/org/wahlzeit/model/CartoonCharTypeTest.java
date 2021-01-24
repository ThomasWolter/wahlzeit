package org.wahlzeit.model;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartoonCharTypeTest {

    private final String name = "Donald Duck", name1 = "Phantomias", artstyle = "classic";
    private final int debut_valid = 1934, debut_valid1 = 1970;
    private final int debut_invalid = -1;


    /**
     * Test the constructor
     */
    @Test
    public void testConstructor() {
        CartoonCharType type = new CartoonCharType(name, debut_valid);
        assertEquals(type.getName(), name);
        assertEquals(type.getDebut(), debut_valid);

    }

    /**
     * Test adding an invalid date
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReadFrom() {
        CartoonCharType type = new CartoonCharType(name, debut_invalid);
    }

    /**
     * Test hierarchy
     */
    @Test
    public void testCheckHierarchy() {
        CartoonCharType type = new CartoonCharType(name, debut_valid);
        CartoonCharType subtype = new CartoonCharType(name1, debut_valid1);
        type.addSubType(subtype);
        assertTrue(type.isSubType(subtype));
        assertEquals(type, subtype.getSuperType());
    }

    @Test
    public void testHasInstance() {
        CartoonCharType type = new CartoonCharType(name, debut_valid);
        CartoonChar character = new CartoonChar(type, artstyle);
        assertTrue(type.hasInstance(character));
    }
}
