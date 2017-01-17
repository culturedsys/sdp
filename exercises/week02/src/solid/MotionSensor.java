package solid;

import java.util.Random;

/**
 * A security sensor for detecting motion.
 */
public class MotionSensor implements Sensor {

    private String location;
    private Random random;

    public MotionSensor(String location) {
        this.location = location;
        random = new Random();
    }

    @Override
    public boolean isTriggered() {
        return random.nextInt(100) < 30;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getSensorType() {
        return "Motion sensor";
    }

    @Override
    public Type getType() {
        return Type.SECURITY;
    }
}
