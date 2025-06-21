package aircraft;

import coordinates.Coordinates;
import simulation.Simulator;

public class Balloon extends Aircraft {
    public Balloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public String getAircraftType() {
        return "Balloon";
    }

    @Override
    public void updateConditions() {
        String currentWeather = weatherTower.getWeather(coordinates);
        String message = "";

        switch (currentWeather) {
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 2);
                coordinates.setHeight(coordinates.getHeight() + 4);
                message = "Let's go up.";
                break;
            case "RAIN":
                coordinates.setHeight(coordinates.getHeight() - 5);
                message = "Cover the flame!";
                break;
            case "FOG":
                coordinates.setHeight(coordinates.getHeight() - 3);
                message = "If we hit something, we hit something.";
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 15);
                message = "We're going down.";
                break;
        }

        if (coordinates.getHeight() <= 0) {
            message = "Landing.";
            weatherTower.unregister(this);
        }

        Simulator.writeToLog(getAircraftType() + "#" + name + "(" + id + "): " + message);
    }
}