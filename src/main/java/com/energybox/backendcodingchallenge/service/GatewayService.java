package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.node.Gateway;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.view.request.GatewayRequestView;
import com.energybox.backendcodingchallenge.view.request.SensorRequestView;

import java.util.List;

public interface GatewayService {
    Gateway create(GatewayRequestView gatewayRequestView);
    Gateway update(Long gatewayId, GatewayRequestView gatewayRequestView);
    List<Gateway> getAll();
    Gateway get(Long gatewayId);
    Gateway addSensor(Long gatewayId, SensorRequestView sensorRequestView);
    Gateway removeSensor(Long gatewayId, Long sensorId);
}
