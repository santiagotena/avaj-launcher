
package tower;

import java.util.ArrayList;
import java.util.List;
import aircraft.Flyable;
import simulation.Simulator;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        if (!observers.contains(p_flyable)) {
            observers.add(p_flyable);
            Simulator.writeToLog("Tower: " + p_flyable.getAircraftType() + "#" + p_flyable.getName() + "(" + p_flyable.getId() + ") registered to weather tower.");
        }
    }

    public void unregister(Flyable p_flyable) {
        if (observers.contains(p_flyable)) {
            observers.remove(p_flyable);
            Simulator.writeToLog("Tower: " + p_flyable.getAircraftType() + "#" + p_flyable.getName() + "(" + p_flyable.getId() + ") unregistered from weather tower.");
        }
    }

    protected void conditionChanged() {
        List<Flyable> observersCopy = new ArrayList<>(observers);
        for (Flyable flyable : observersCopy) {
            if (flyable.getCoordinates().getHeight() > 0) {
                flyable.updateConditions();
            } else {
                unregister(flyable);
            }
        }
    }

    public List<Flyable> getObservers() {
        return observers;
    }
}