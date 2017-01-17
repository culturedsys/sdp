package solid;

import java.util.Random;

/**
 * A Sensor implementation that detects smoke.
 */
class SmokeSensor implements Sensor {
    private final String location;

    /**
     * A random number generator to simulate a change of detecting smoke.
     */
    private final Random random;
    private int batteryPercentage;

    public SmokeSensor(String location) {
        this.location = location;
        batteryPercentage = 100;
        random = new Random();
    }

    @Override
    public boolean isTriggered() {
        batteryPercentage -= 20;
        return random.nextInt(100) < 10;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getSensorType() {
        return "Smoke sensor";
    }

    @Override
    public int getBatteryPercentage() {
        return batteryPercentage;
    }
}
