package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartoonCharPhoto extends Photo{

    private String name = "";
    private String company = "";
    private int debut = 0;

    public CartoonCharPhoto(){
        super();
    }

    public CartoonCharPhoto(PhotoId myId) {
        super(myId);
    }

    public CartoonCharPhoto(ResultSet rSet) throws SQLException {
        readFrom(rSet);
    }

    public CartoonCharPhoto(String name, String company, int debut){
        super();
        this.name = name;
        this.company = company;
        this.debut = debut;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        name = rset.getString("name");
        company = rset.getString("company");
        debut = rset.getInt("debut");
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateString("name", this.name);
        rset.updateString("company", this.company);
        rset.updateInt("debut", this.debut);
    }
}
