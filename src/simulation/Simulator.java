package simulation;

import aircraft.Flyable;
import factory.AircraftFactory;
import coordinates.Coordinates;
import tower.WeatherTower;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private static PrintWriter simulationLogWriter;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java simulation.Simulator <scenario_file>");
            System.exit(1);
        }

        String scenarioFile = args[0];
        List<Flyable> aircrafts = new ArrayList<>();
        WeatherTower weatherTower = new WeatherTower();
        int simulationRuns = 0;

        try {
            simulationLogWriter = new PrintWriter("simulation.txt");

            BufferedReader reader = new BufferedReader(new FileReader(scenarioFile));
            String line = reader.readLine();

            if (line != null) {
                try {
                    simulationRuns = Integer.parseInt(line.trim());
                    if (simulationRuns < 1) {
                        throw new IllegalArgumentException("Simulation runs must be a positive number.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: First line (simulation runs) is not a valid integer.");
                    System.exit(1);
                } catch (IllegalArgumentException e) {
                    System.err.println("Error: " + e.getMessage());
                    System.exit(1);
                }
            } else {
                System.err.println("Error: Scenario file is empty.");
                System.exit(1);
            }

            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                if (parts.length == 5) {
                    try {
                        String type = parts[0];
                        String name = parts[1];
                        int longitude = Integer.parseInt(parts[2]);
                        int latitude = Integer.parseInt(parts[3]);
                        int height = Integer.parseInt(parts[4]);

                        if (longitude < 1 || latitude < 1 || height < 1) {
                            throw new IllegalArgumentException("Coordinates (longitude, latitude, height) must be positive numbers.");
                        }

                        if (height > 100) { height = 100; }

                        Coordinates coordinates = new Coordinates(longitude, latitude, height);

                        Flyable aircraft = AircraftFactory.getFactory().newAircraft(type, name, coordinates);
                        if (aircraft != null) {
                            aircrafts.add(aircraft);
                        } else {
                            System.err.println("Error: Could not create aircraft of type '" + type + "'. Aborting.");
                            System.exit(1);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Invalid number format in scenario file line: " + line);
                        System.exit(1);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error: Invalid input data in scenario file line: " + line + " - " + e.getMessage());
                        System.exit(1);
                    }
                } else {
                    System.err.println("Error: Invalid line format in scenario file: " + line);
                    System.exit(1);
                }
            }
            reader.close();

            for (Flyable aircraft : aircrafts) {
                aircraft.registerTower(weatherTower);
            }

            for (int i = 0; i < simulationRuns; i++) {
                if (weatherTower.getObservers().isEmpty()) {
                    break;
                }
                weatherTower.changeWeather();
            }

        } catch (IOException e) {
            System.err.println("Error reading scenario file: " + e.getMessage());
            System.exit(1);
        } finally {
            if (simulationLogWriter != null) {
                simulationLogWriter.close();
            }
        }
    }

    public static void writeToLog(String message) {
        if (simulationLogWriter != null) {
            simulationLogWriter.println(message);
        }
    }
}