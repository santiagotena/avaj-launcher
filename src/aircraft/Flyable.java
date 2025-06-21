package aircraft;

import tower.WeatherTower;
import coordinates.Coordinates;

public abstract class Flyable {

    protected WeatherTower weatherTower;

    public abstract void updateConditions();

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
    }

    public abstract String getAircraftType();
    public abstract String getName();
    public abstract long getId();
    public abstract Coordinates getCoordinates();
}