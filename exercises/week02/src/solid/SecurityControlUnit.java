package solid;

import java.time.LocalTime;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A ControlUnit sub-class that only polls at specific times.
 */
public class SecurityControlUnit extends ControlUnit {
    /**
     * Create a security control unit monitoring the supplied sensors.
     *
     * @param sensors
     */
    public SecurityControlUnit(Collection<Sensor> sensors) {
        super(sensors);
    }

    /**
     * Modify the behaviour of pollSensors so that it only runs at certain times. This obeys the open-closed
     * principle, in that it extends the behaviour of the ControlUnit class without altering its implementation.
     */
    @Override
    public void pollSensors() {
        LocalTime now = LocalTime.now();
        if (now.getHour() > 22 || now.getHour() < 6) {
            super.pollSensors();
        }
    }

    public static void main(String[] args) {
        Collection<Sensor> sensors = Stream.of(
                new FireSensor("Lobby"),
                new SmokeSensor("Stairs"),
                new SmokeSensor("Mezzanine"),
                new FireSensor("Kitchen"),
                new MotionSensor("Door")
        ).collect(Collectors.toList());

        ControlUnit controlUnit = new SecurityControlUnit(sensors);
        controlUnit.pollSensors();
    }

}
