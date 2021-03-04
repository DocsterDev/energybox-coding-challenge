package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.node.SensorData;
import com.energybox.backendcodingchallenge.enums.SensorType;

public interface SensorService {
    Sensor update(Long gatewayId, Long sensorId, Sensor sensor);
    Sensor addType(Long gatewayId, Long sensorId, SensorType type);
    Sensor removeType(Long gatewayId, Long sensorId, SensorType type);
    Sensor addData(Long gatewayId, Long sensorId, SensorData data);
}
