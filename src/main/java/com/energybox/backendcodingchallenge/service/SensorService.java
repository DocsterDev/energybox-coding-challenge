package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.node.SensorData;
import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.view.request.SensorDataRequestView;
import com.energybox.backendcodingchallenge.view.request.SensorRequestView;

public interface SensorService {
    Sensor update(Long gatewayId, Long sensorId, SensorRequestView sensorRequestView);
    Sensor addType(Long gatewayId, Long sensorId, SensorType type);
    Sensor removeType(Long gatewayId, Long sensorId, SensorType type);
    Sensor addData(Long gatewayId, Long sensorId, SensorDataRequestView sensorDataRequestView);
}
