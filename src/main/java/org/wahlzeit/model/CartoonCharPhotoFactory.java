package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartoonCharPhotoFactory extends PhotoFactory{

    @Override
    public CartoonCharPhoto createPhoto() {
        return new CartoonCharPhoto();
    }

    @Override
    public CartoonCharPhoto createPhoto(PhotoId id) {
        return new CartoonCharPhoto(id);
    }

    @Override
    public CartoonCharPhoto createPhoto(ResultSet rSet) throws SQLException {
        return new CartoonCharPhoto(rSet);
    }

    public CartoonCharPhoto createPhoto(String name, int debut, String artstyle) {
        return new CartoonCharPhoto(name, debut, artstyle);
    }

}
