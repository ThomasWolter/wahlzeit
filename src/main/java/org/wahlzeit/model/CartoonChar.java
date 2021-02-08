package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * CW12: See pdf
 */


/*
 * Object creation:
 * 1.) Start in CartoonCharManager with a call to createCartoonChar()
 * 2.) If type is not given:
 *     (-> Extract data from ResultSet)
 *     -> Create a new CartoonCharType
 * 3.) Create a new CartoonCharby using constructor
 * 4.) Add the new CartoonChar to the cache if necessary or return the already existing one
 */


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
