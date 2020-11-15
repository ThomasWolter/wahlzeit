package org.wahlzeit.model;

/**
 * A location associated with the photo
 */
public class Location {


    private Coordinate coordinate;

    /**
     *
     * @methodtype constructor
     * I set the values to 0, as it was stated that we don't work on the logic
     */
    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     *
     * @methodtype set
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     *
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

}
