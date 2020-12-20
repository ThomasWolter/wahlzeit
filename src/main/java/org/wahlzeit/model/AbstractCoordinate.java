package org.wahlzeit.model;

import java.util.Objects;

public abstract class AbstractCoordinate implements Coordinate{


    /**
     * Determines the distance between local coordinate (x,y,z) and argument (a,b,c)
     * Formula used: sqrt(pow(a-x) + pow(b-y) + pow(c-z))
     * @param coordinate coordinates to measure the distance from
     * @return the distance between the two coordinates
     * @throws IllegalArgumentException If null is passed as an argument
     */
    public double getCartesianDistance(Coordinate coordinate){
        // Check preconditions
        assertNull(coordinate);
        this.assertClassInvariant();

        // Make sure the local and argument coordinate are in cartesian form
        CartesianCoordinate local = this.asCartesianCoordinate();
        CartesianCoordinate argument = coordinate.asCartesianCoordinate();

        // Calculate the value
        // ax = pow(a-x), by = pow(b-y), cz = pow(c-z)
        double ax = Math.pow(argument.getX() - local.getX(), 2);
        double by = Math.pow(argument.getY() - local.getY(), 2);
        double cz = Math.pow(argument.getZ() - local.getZ(), 2);
        double distance = Math.sqrt(ax + by + cz);
        assertDistance(distance);

        return distance;
    }

    /**
     * Converts coordinates to spheric and determines the distance between local coordinate (x,y,z) and argument (a,b,c)
     * Formula used: TODO
     * @param coordinate coordinates to measure the central angle from
     * @return the central angle between the two coordinates
     * @throws IllegalArgumentException If null is passed as an argument
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        // Check preconditions
        assertNull(coordinate);
        this.assertClassInvariant();

        // Make sure both the local and the argument are in spheric form
        SphericCoordinate local = this.asSphericCoordinate();
        SphericCoordinate argument = coordinate.asSphericCoordinate();

        // Calculate the value
        double deltaTheta = Math.abs(local.getTheta() - argument.getTheta());
        double sinPhi = Math.sin(local.getPhi()) * Math.sin(argument.getPhi());
        double cosPhi = Math.cos(local.getPhi()) * Math.cos(argument.getPhi()) * deltaTheta;
        double angle = Math.acos(sinPhi + cosPhi);
        assertAngle(angle);

        return Math.acos(sinPhi + cosPhi);
    }


    /**
     * Checks if the given argument represents the same location, regardless of coordinate system.
     * @param coordinate
     * @return True if argument represents the same location as this objects
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        // Check preconditions
        // Check if coordinate is a valid argument
        assertNull(coordinate);
        this.assertClassInvariant();

        // Make sure both the local and the argument are in cartesian form
        CartesianCoordinate local = this.asCartesianCoordinate();
        CartesianCoordinate argument = coordinate.asCartesianCoordinate();

        // Check if all coordinates are equal
        boolean xCompare = (Math.abs(local.getX() - argument.getX()) <= 0.0001);
        boolean yCompare = (Math.abs(local.getY() - argument.getY()) <= 0.0001);
        boolean zCompare = (Math.abs(local.getZ() - argument.getZ()) <= 0.0001);
        return xCompare && yCompare && zCompare;
    }

    /**
     * Forward equals() to isEqual() by overriding it
     */
    @Override
    public boolean equals(Object obj){
        // Check if obj is actually a coordinate
        if (!(obj instanceof Coordinate)){
            return false;
        }
        return isEqual((Coordinate) obj);
    }

    /**
     * Make sure the contract between equals() and hashCode() is kept
     */
    @Override
    public int hashCode() {
        CartesianCoordinate coordinate = this.asCartesianCoordinate();
        return Objects.hash(coordinate.getX(), coordinate.getY(), coordinate.getZ());
    }

    /**
     * Checks if the double value given to this function is valid
     * @param toCheck double value to test
     */
    @Override
    public void assertDouble(double toCheck){
        if (!Double.isFinite(toCheck)) {
            throw new IllegalArgumentException("Double value can't be infintie");
        }
        if (Double.isNaN(toCheck)){
            throw new IllegalArgumentException("Double value can't be NaN");
        }
    }

    /**
     * Checks if the coordinate value given is null
     * @param toCheck coordinate to test
     */
    @Override
    public void assertNull(Coordinate toCheck){
        if (toCheck == null) {
            throw new IllegalArgumentException("Coordinate can't be null");
        }
    }

    /**
     * Checks if the angle has a valid value
     * @param toCheck value to test
     */
    @Override
    public void assertAngle(double toCheck){
        boolean minimum = toCheck >= 0;
        boolean maximum = toCheck <= 360;
        if (!(minimum && maximum)) {
            throw new ArithmeticException("Radius can't be less than zero");
        }
    }

    /**
     * Checks if the distance has a valid value
     * @param toCheck value to test
     */
    @Override
    public void assertDistance(double toCheck){
        if (toCheck <= 0) {
            throw new ArithmeticException("Distance is negative");
        }
    }

}
