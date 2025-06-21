
package factory;

import aircraft.Aircraft;
import aircraft.Balloon;
import aircraft.Helicopter;
import aircraft.JetPlane;
import aircraft.Flyable;
import coordinates.Coordinates;

public class AircraftFactory {
    private static AircraftFactory aircraftFactory;
    private static long previousId = 1;

    private AircraftFactory() {
    }

    public static AircraftFactory getFactory() {
        if (aircraftFactory == null) {
            aircraftFactory = new AircraftFactory();
        }
        return aircraftFactory;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        long currentId = previousId++;
        switch (p_type.toLowerCase()) {
            case "balloon":
                return new Balloon(currentId, p_name, p_coordinates);
            case "helicopter":
                return new Helicopter(currentId, p_name, p_coordinates);
            case "jetplane":
                return new JetPlane(currentId, p_name, p_coordinates);
            default:
                System.err.println("Error: Unknown aircraft type '" + p_type + "'");
                return null;
        }
    }
}
