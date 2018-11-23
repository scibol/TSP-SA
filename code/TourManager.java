import java.util.ArrayList;

public class TourManager {

    private static ArrayList<City> destinationCities = new ArrayList<City>();

    public static void addCity(City city) {
        destinationCities.add(city);
            }

    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }

    public static int numberOfCities(){
        return destinationCities.size();
    }

}
