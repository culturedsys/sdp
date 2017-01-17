package solid;

/**
 * A Sensor implemetation that detects smoke.
 */
class SmokeSensor implements Sensor {
    public SmokeSensor(String location) {

    }

    @Override
    public boolean isTriggered() {
        return false;
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public String getSensorType() {
        return null;
    }

    @Override
    public int getBatteryPercentage() {
        return 0;
    }
}
