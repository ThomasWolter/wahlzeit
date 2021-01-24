package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CartoonCharPhotoFactoryTest {

    private final String name = "Donald Duck", artstyle = "classic";
    private final int debut = 1934;

    private CartoonCharPhotoFactory factory= new CartoonCharPhotoFactory ();

    @Test
    public void testConstructor() {

        CartoonCharPhoto photo = factory.createPhoto(name, debut, artstyle);

        assertEquals(photo.getCartoonChar().getCartoonCharType().getName(), name);
        assertEquals(photo.getCartoonChar().getCartoonCharType().getDebut(), debut);
        assertEquals(photo.getCartoonChar().getArtstyle(), artstyle);

    }

}
