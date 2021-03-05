package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.node.Gateway;
import com.energybox.backendcodingchallenge.view.request.GatewayRequestView;

import java.util.List;

public interface GatewayService {
    Gateway create(GatewayRequestView gatewayRequestView);
    Gateway update(Long gatewayId, GatewayRequestView gatewayRequestView);
    List<Gateway> getAll();
    List<Gateway> getAllBySensorType(SensorType type);
    Gateway get(Long gatewayId);
    void delete(Long gatewayId);
    Gateway addSensor(Long gatewayId, Long sensorId);
    Gateway removeSensor(Long gatewayId, Long sensorId);
}
