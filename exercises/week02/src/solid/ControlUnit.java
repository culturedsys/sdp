package solid;

/**
 * The main entry point for controlling sensors
 */
public class ControlUnit {
    /**
     * Create some sensors, poll them every 5 seconds, and report any that are triggered (three responsibilities).
     */
    private void pollSensors() {
        Sensor sensors[] = {
                new FireSensor("Lobby"),
                new SmokeSensor("Stairs"),
                new SmokeSensor("Mezzanine"),
                new FireSensor("Kitchen")
        };

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
        ControlUnit controlUnit = new ControlUnit();
        controlUnit.pollSensors();
    }
}
