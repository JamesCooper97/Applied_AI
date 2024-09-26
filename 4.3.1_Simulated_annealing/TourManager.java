import java.util.ArrayList;

public class TourManager {
    // Holds our cities
    private static ArrayList cityRoute = new ArrayList<City>();

    // Adds a destination city
    public static void addCity(City city) {
        cityRoute.add(city);
    }

    // Get a city
    public static City getCity(int index) {
        return (City) cityRoute.get(index);
    }

    // Get the number of destination cities
    public static int numberOfCities() {
        return cityRoute.size();
    }
}
