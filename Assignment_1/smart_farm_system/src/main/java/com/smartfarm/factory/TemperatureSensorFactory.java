package com.smartfarm.factory;

import com.smartfarm.model.Sensor;
import com.smartfarm.model.TemperatureSensor;

/**
 * Concrete Factory: Responsible for creating TemperatureSensor instances.
 */
public class TemperatureSensorFactory implements SensorFactory {
    @Override
    public Sensor createSensor(String sensorId) {
        return new TemperatureSensor(sensorId);
    }
}