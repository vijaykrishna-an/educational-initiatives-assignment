package com.smartfarm.factory;

import com.smartfarm.model.Sensor;
import com.smartfarm.model.PestDetectionSensor;

/**
 * Concrete Factory: Responsible for creating PestDetectionSensor instances.
 */
public class PestDetectionSensorFactory implements SensorFactory {
    @Override
    public Sensor createSensor(String sensorId) {
        return new PestDetectionSensor(sensorId);
    }
}