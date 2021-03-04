package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.node.Gateway;
import com.energybox.backendcodingchallenge.node.Sensor;

import java.util.List;

public interface GatewayService {
    Gateway create(Gateway gateway);
    Gateway update(Long gatewayId, Gateway gateway);
    List<Gateway> getAll();
    Gateway get(Long gatewayId);
    Gateway addSensor(Long gatewayId, Sensor sensor);
    Gateway removeSensor(Long gatewayId, Long sensorId);
}