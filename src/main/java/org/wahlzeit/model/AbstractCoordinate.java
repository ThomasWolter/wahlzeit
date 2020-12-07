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
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument");
        }

        // Make sure the local and argument coordinate are in cartesian form
        CartesianCoordinate local = this.asCartesianCoordinate();
        CartesianCoordinate argument = coordinate.asCartesianCoordinate();
        // Calculate the value
        // ax = pow(a-x), by = pow(b-y), cz = pow(c-z)
        double ax = Math.pow(argument.getX() - local.getX(), 2);
        double by = Math.pow(argument.getY() - local.getY(), 2);
        double cz = Math.pow(argument.getZ() - local.getZ(), 2);
        return Math.sqrt(ax + by + cz);
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
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument");
        }

        // Make sure both the local and the argument are in spheric form
        SphericCoordinate local = this.asSphericCoordinate();
        SphericCoordinate argument = coordinate.asSphericCoordinate();

        // Calculate the value
        double deltaTheta = Math.abs(local.getTheta() - argument.getTheta());
        double sinPhi = Math.sin(local.getPhi()) * Math.sin(argument.getPhi());
        double cosPhi = Math.cos(local.getPhi()) * Math.cos(argument.getPhi()) * deltaTheta;
        return Math.acos(sinPhi + cosPhi);
    }


    /**
     * Checks if the given argument represents the same location, regardless of coordinate system.
     * @param coordinate
     * @return True if argument represents the same location as this objects
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        // Check if coordinate is a valid argument
        if (coordinate == null) {
            throw new IllegalArgumentException("Null is not a valid argument for isEqual()");
        }
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



}
