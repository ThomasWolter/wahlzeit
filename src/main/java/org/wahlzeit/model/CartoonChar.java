package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartoonChar {

    private CartoonCharType cartoonCharType;
    private String artstyle = "Default";

    public CartoonChar(CartoonCharType cartoonCharType, String artstyle) {
        if (cartoonCharType == null || artstyle == null) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        this.cartoonCharType = cartoonCharType;
        this.artstyle = artstyle;
    }


    public CartoonCharType getCartoonCharType() {
        return cartoonCharType;
    }

    public void setCartoonCharType(CartoonCharType cartoonCharType) {
        this.cartoonCharType = cartoonCharType;
    }

    public String getArtstyle() {
        return artstyle;
    }

    public void setArtstyle(String artstyle) {
        this.artstyle = artstyle;
    }

}
