package solid;

import java.util.Random;

/**
 * An implementation of sensors for fire
 */
class FireSensor implements BatteryPoweredSensor {
    private final String location;
    private int batteryPercentage;

    /**
     * A random number generator to use to simulate a random chance of fire being detected.
     */
    private final Random random;


    public FireSensor(String location) {
        this.location = location;
        random = new Random();
        batteryPercentage = 100;
    }

    @Override
    public boolean isTriggered() {
        batteryPercentage -= 10;
        return random.nextInt(100) < 5;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getSensorType() {
        return "Fire sensor";
    }

    @Override
    public Type getType() {
        return Type.HAZARD;
    }

    @Override
    public int getBatteryPercentage() {
        return batteryPercentage;
    }
}
