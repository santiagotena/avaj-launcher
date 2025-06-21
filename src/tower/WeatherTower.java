package tower;

import coordinates.Coordinates;
import provider.WeatherProvider;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        super.conditionChanged();
    }
}
