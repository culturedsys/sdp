package solid;
/**
 * An interface for sensors
 */
public interface Sensor {
    /**
     * @return whether the sensor is triggered.
     */
    boolean isTriggered();

    /**
     * @return a description of the location of the sensor.
     */
    String getLocation();

    /**
     * @return a textual description of the sensor type.
     */
    String getSensorType();

    /**
     * @return a number between 0-100 where 0 is empty and 100 is fully charged.
     */
    int getBatteryPercentage();
}
