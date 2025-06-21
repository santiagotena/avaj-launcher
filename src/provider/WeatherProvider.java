package provider;

import coordinates.Coordinates;
import java.util.Random;

public class WeatherProvider {
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private static WeatherProvider weatherProvider;

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int seed = p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight();
        Random random = new Random(seed);
        int index = random.nextInt(weather.length);
        return weather[index];
    }
}