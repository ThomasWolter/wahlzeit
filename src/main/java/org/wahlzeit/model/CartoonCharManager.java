package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartoonCharManager extends ObjectManager {

    // Singleton
    private static final CartoonCharManager manager = new CartoonCharManager();

    // Map for caching the types
    private final Map<Integer, CartoonCharType> cartoonChars = new HashMap<>();
    // Map for storing instances
    private final Map<Integer, CartoonChar> cartoonCharsInstances = new HashMap<>();

    public static CartoonCharManager getInstance() {
        return manager;
    }

    public Map<Integer, CartoonCharType> getCartoonChars() {
        return cartoonChars;
    }

    public Map<Integer, CartoonChar> getCartoonCharsInstances() {
        return cartoonCharsInstances;
    }

    // Creates a new cartoonCharType or return the already existing one
    public CartoonCharType createCartoonCharType(String name, int debut) {
        CartoonCharType type = new CartoonCharType(name, debut);
        //Check if the type already exists
        if (cartoonChars.containsValue(type)) {
            return cartoonChars.get(type.hashCode());
        }
        // Add the new type
        cartoonChars.put(type.hashCode(), type);
        return type;
    }

    // Creates a new cartoonChar or returns the already created one
    public CartoonChar createCartoonChar(CartoonCharType typeName, String artstyle) {
        CartoonChar character = new CartoonChar(typeName, artstyle);
        // Check if already exists
        if (cartoonCharsInstances.containsValue(character)) {
            return cartoonCharsInstances.get(character.hashCode());
        }
        // Add the new type
        cartoonCharsInstances.put(character.hashCode(), character);
        return character;
    }


    // Create new cartoonChar
    public CartoonChar createCartoonChar(String name, int debut, String artstyle){
        CartoonCharType type = new CartoonCharType(name, debut);
        // Check if already exists
        return createCartoonChar(type, artstyle);
    }

    public CartoonChar createCartoonChar(ResultSet rset) throws SQLException{
        String name = rset.getString("name");
        int debut = rset.getInt("debut");
        String artstyle = rset.getString("artstyle");
        return createCartoonChar(name, debut, artstyle);
    }

    @Override
    protected Persistent createObject(ResultSet rset) throws SQLException {
        return null;
    }

    private void assertNull (Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Argument can't be null");
        }
    }

}
