package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartoonCharPhotoTest {

    private final String name = "Donald Duck", company = "Disney";
    private final int debut = 1934;

    @Test
    public void testConstructor() {
        CartoonCharPhoto photo = new CartoonCharPhoto(name, company, debut);
        assertEquals(photo.getName(),name);
        assertEquals(photo.getCompany(), company);
        assertEquals(photo.getDebut(), debut);
    }
}