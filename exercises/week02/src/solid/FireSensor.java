package solid;

/**
 * An implementation of sensors for fire
 */
class FireSensor implements Sensor {

    public FireSensor(String location) {

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
