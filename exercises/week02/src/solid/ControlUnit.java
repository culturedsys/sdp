package solid;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The main entry point for controlling sensors
 */
public class ControlUnit {
    private final Collection<Sensor> sensors;

    /**
     * Create a control unit monitoring the supplied sensors.
     */
    public ControlUnit(Collection<Sensor> sensors) {
        this.sensors = sensors;
    }

    /**
     * Poll sensors every 5 seconds, and report any that are triggered and any that have low battery (three
     * responsibilities).
     */
    private void pollSensors() {
        while (true) {
            for (Sensor sensor : sensors) {
                if (sensor.isTriggered()) {
                    System.out.println("Alert! " + sensor.getSensorType() + " in " + sensor.getLocation() + " triggered");
                }
                if (sensor.getBatteryPercentage() < 5) {
                    System.out.println("Alert! " + sensor.getSensorType() +
                            " in " + sensor.getLocation() + " battery critical");

                } else if (sensor.getBatteryPercentage() < 15) {
                    System.out.println("Warning: " + sensor.getSensorType() +
                            " in " + sensor.getLocation() + " battery low");
                }
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // If sleep is interrupted, we simply check more frequently, so do nothing here.
            }
        }
    }

    public static void main(String[] args) {
        Collection<Sensor> sensors = Stream.of(
                new FireSensor("Lobby"),
                new SmokeSensor("Stairs"),
                new SmokeSensor("Mezzanine"),
                new FireSensor("Kitchen")
        ).collect(Collectors.toList());

        ControlUnit controlUnit = new ControlUnit(sensors);
        controlUnit.pollSensors();
    }
}
