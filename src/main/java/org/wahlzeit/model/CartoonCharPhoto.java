package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Object creation:
 * 1.) Start in PhotoManager.createPhoto()
 *     -> Call to PhotoUtil.createPhoto()
 * 2.) Generate new ID
 * 3.) Call to  PhotoFactory.createPhoto(id)
 * 4.) Return new photo object PhotoUtil.createPhoto() by using the Photo constructor
 * 5.) Handle the width and height of the new image and return to PhotoManager.createPhoto()
 * 6.) Call PhotoManager.addPhoto() to handle the caching
 */

public class CartoonCharPhoto extends Photo{

    private CartoonChar cartoonChar;

    public CartoonCharPhoto(){
        super();
    }

    public CartoonCharPhoto(PhotoId myId) {
        super(myId);
    }

    public CartoonCharPhoto(ResultSet rSet) throws SQLException {
        readFrom(rSet);
    }

    public CartoonChar getCartoonChar() {
        return cartoonChar;
    }

    public void setCartoonChar(CartoonChar cartoonChar) {
        this.cartoonChar = cartoonChar;
    }

    public CartoonCharPhoto(CartoonCharType cartoonCharType, String artstyle){
        super();
        this.cartoonChar = new CartoonChar(cartoonCharType, artstyle);
    }

    public CartoonCharPhoto(String name, int debut, String artstyle){
        super();
        this.cartoonChar = CartoonCharManager.getInstance().createCartoonChar(name, debut, artstyle);
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        this.cartoonChar = CartoonCharManager.getInstance().createCartoonChar(rset);
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateString("name", cartoonChar.getCartoonCharType().getName());
        rset.updateInt("debut", cartoonChar.getCartoonCharType().getDebut());
        rset.updateString("artstyle", cartoonChar.getArtstyle());
    }
}
