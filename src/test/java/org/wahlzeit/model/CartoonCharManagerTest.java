package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CartoonCharManagerTest {

    private final String name = "Donald Duck", name1 = "Daffy Duck", artstyle = "classic", artstyle1 = "modern";
    private final int debut= 1934, debut1= 1937;

    /**
     * Test creating objects in/not in maps
     */
    @Test
    public void testType() {
        // Add first
        CartoonCharType type = CartoonCharManager.getInstance().createCartoonCharType(name, debut);
        CartoonChar character = CartoonCharManager.getInstance().createCartoonChar(type, artstyle);
        // Add second
        CartoonCharType type1 = CartoonCharManager.getInstance().createCartoonCharType(name, debut);
        CartoonChar character1 = CartoonCharManager.getInstance().createCartoonChar(type1, artstyle);

        // Add different one
        CartoonCharType type2 = CartoonCharManager.getInstance().createCartoonCharType(name1, debut1);
        CartoonChar character2 = CartoonCharManager.getInstance().createCartoonChar(type2, artstyle);


    }


}
