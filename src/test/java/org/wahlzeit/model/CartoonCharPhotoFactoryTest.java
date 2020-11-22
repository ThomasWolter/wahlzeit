package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CartoonCharPhotoFactoryTest {

    private final String name = "Donald Duck", company = "Disney";
    private final int debut = 1934;

    private CartoonCharPhotoFactory factory= new CartoonCharPhotoFactory ();

    @Test
    public void testConstructor() {

        CartoonCharPhoto photo = factory.createPhoto(name, company, debut);

        assertEquals(photo.getName(),name);
        assertEquals(photo.getCompany(), company);
        assertEquals(photo.getDebut(), debut);

    }

}
