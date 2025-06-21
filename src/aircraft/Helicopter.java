package aircraft;

import coordinates.Coordinates;
import simulation.Simulator;

public class Helicopter extends Aircraft {
    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public String getAircraftType() {
        return "Helicopter";
    }

    @Override
    public void updateConditions() {
        String currentWeather = weatherTower.getWeather(coordinates);
        String message = "";

        switch (currentWeather) {
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                message = "The temperature is not the only thing going up.";
                break;
            case "RAIN":
                coordinates.setLongitude(coordinates.getLongitude() + 5);
                message = "Curse you! Rain!.";
                break;
            case "FOG":
                coordinates.setLongitude(coordinates.getLongitude() + 1);
                message = "Can barely see anything in this fog.";
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 12);
                message = "My rotor is going to freeze!";
                break;
        }

        if (coordinates.getHeight() <= 0) {
            message = "Landing.";
            weatherTower.unregister(this);
        }

        Simulator.writeToLog(getAircraftType() + "#" + name + "(" + id + "): " + message);
    }
}