/*
 * City.java
 * City Locations
 */

public class City {
    private double x;
    private double y;

    public City(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double distanceTo(City city) {
        double deltaX = Math.abs(getX() - city.getX());
        double deltaY = Math.abs(getY() - city.getY());
        double distance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        return distance;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }

}