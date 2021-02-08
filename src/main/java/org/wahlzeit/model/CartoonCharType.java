package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * CW12: See pdf
 */

public class CartoonCharType {

    // Attributes
    private String name = "default";
    private int debut = 0;

    // CartoonCharType Hierarchy
    protected CartoonCharType superType = null;
    protected Set<CartoonCharType> subTypes = new HashSet<CartoonCharType>();


    public CartoonCharType(String name, int debut) {
        assertNull(name);
        assertDate(debut);
        this.name = name;
        this.debut = debut;
    }

    // Returns the supertype
    public CartoonCharType getSuperType() {
        return superType;
    }

    // Sets the supertype
    public void setSuperType(CartoonCharType superType) {
        assertNull(superType);
        this.superType = superType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        assertNull(name);
        this.name = name;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        assertDate(debut);
        this.debut = debut;
    }

    // Iterator for subtype
    public Iterator<CartoonCharType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    // Function to add subtypes
    public void addSubType(CartoonCharType cartoonCharType) {
        assertNull(cartoonCharType);
        cartoonCharType.setSuperType(this);
        subTypes.add(cartoonCharType);
    }

    // Check if the given object is of this type
    public boolean hasInstance(CartoonChar cartoonChar) {
        assertNull(cartoonChar);
        if (cartoonChar.getCartoonCharType() == this) {
            return true;
        }
        for (CartoonCharType type : subTypes) {
            if (type.hasInstance(cartoonChar)) {
                return true;
            }
        }
        return false;
    }

    // Check if the argument is a subtype of the current type
    public boolean isSubType(CartoonCharType cartoonCharType) {
        assertNull(cartoonCharType);
        // Check direct children
        if (subTypes.contains(cartoonCharType)) {
            return true;
        }
        // Check the childrens subtypes
        for (CartoonCharType children : subTypes) {
            if (children.isSubType(cartoonCharType)) {
                return true;
            }
        }
        // cartoonCharType not found
        return false;
    }


    private void assertNull (Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Argument can't be null");
        }
    }

    private void assertDate(int date) {
        if (date < 0) {
            throw new IllegalArgumentException("Date not in valid range");
        }
    }
}
