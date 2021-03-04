package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.view.request.SensorDataRequestView;
import com.energybox.backendcodingchallenge.view.request.SensorRequestView;

public interface SensorService {
    Sensor get(Long sensorId);
    Sensor create(SensorRequestView sensorRequestView);
    Sensor update(Long sensorId, SensorRequestView sensorRequestView);
    void delete(Long sensorId);
    Sensor addType(Long sensorId, SensorType type);
    Sensor removeType(Long sensorId, SensorType type);
    Sensor addData(Long sensorId, SensorDataRequestView sensorDataRequestView);
}
