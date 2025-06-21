package aircraft;

import coordinates.Coordinates;
import simulation.Simulator;

public class JetPlane extends Aircraft {
    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public String getAircraftType() {
        return "JetPlane";
    }

    @Override
    public void updateConditions() {
        String currentWeather = weatherTower.getWeather(coordinates);
        String message = "";

        switch (currentWeather) {
            case "SUN":
                coordinates.setLatitude(coordinates.getLatitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                message = "I am speed!";
                break;
            case "RAIN":
                coordinates.setLatitude(coordinates.getLatitude() + 5);
                message = "Time to get wet.";
                break;
            case "FOG":
                coordinates.setLatitude(coordinates.getLatitude() + 1);
                message = "Use your instincts.";
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 7);
                message = "Frosty.";
                break;
        }

        if (coordinates.getHeight() <= 0) {
            message = "Landing.";
            weatherTower.unregister(this);
        }

        Simulator.writeToLog(getAircraftType() + "#" + name + "(" + id + "): " + message);
    }
}