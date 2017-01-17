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
     * Poll sensors every 5 seconds, and report any that are triggered (two responsibilities).
     *
     * I've removed the battery status check, because not all sensors have batteries, and so I've moved
     * getBatteryPercentage() to a separate interface; as such, I can't access it on the Sensor objects passed in
     * here (I think I could use a downcast and handle any exceptions, or add an isBatteryPowered method to the
     * Sensor interface and only downcast if that method returns true, but both options seem inelegant. Anyway,
     * polling and checking battery status are separate responsibilites, to perhaps pollSensors() shouldn't be
     * handling the latter anyway).
     */
    private void pollSensors() {
        while (true) {
            for (Sensor sensor : sensors) {
                if (sensor.isTriggered()) {
                    System.out.println("Alert! " + sensor.getSensorType() + " in " + sensor.getLocation() + " triggered");
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
