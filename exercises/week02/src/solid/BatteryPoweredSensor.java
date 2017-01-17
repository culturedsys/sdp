package solid;

/**
 * An interface for Sensors that can be queried about their battery status. Introduced so that classes can implement
 * the Sensor interface without having to implement a getBatteryStatus method, which would not make sense for some
 * sensors.
 */
public interface BatteryPoweredSensor extends Sensor {
    /**
     * @return a number between 0-100 where 0 is empty and 100 is fully charged.
     */
    int getBatteryPercentage();
}
