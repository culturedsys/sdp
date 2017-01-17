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

    /* Moved to the BatteryPoweredSensor interface to allow non-battery powered sensors to obey the Liskov
     * substitution principle.
     * @return a number between 0-100 where 0 is empty and 100 is fully charged.
     */
    //int getBatteryPercentage();
}
