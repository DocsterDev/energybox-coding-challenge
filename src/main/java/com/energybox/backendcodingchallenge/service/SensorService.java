package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.view.request.SensorDataRequestView;
import com.energybox.backendcodingchallenge.view.request.SensorRequestView;

import java.util.List;

public interface SensorService {
    Sensor create(SensorRequestView sensorRequestView);
    Sensor update(Long sensorId, SensorRequestView sensorRequestView);
    List<Sensor> getAll();
    Sensor get(Long sensorId);
    List<Sensor> getByType(SensorType type);
    void delete(Long sensorId);
    Sensor addType(Long sensorId, SensorType type);
    Sensor removeType(Long sensorId, SensorType type);
    Sensor addData(Long sensorId, SensorDataRequestView sensorDataRequestView);
}
