package com.energybox.backendcodingchallenge.service.impl;

import com.energybox.backendcodingchallenge.node.Gateway;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.node.SensorData;
import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import com.energybox.backendcodingchallenge.service.GatewayService;
import com.energybox.backendcodingchallenge.service.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final GatewayService gatewayService;
    private final SensorRepository sensorRepository;

    @Transactional
    public Sensor update(Long gatewayId, Long sensorId, Sensor sensor) {
        Sensor sensorPersistent = getSensor(gatewayId, sensorId);
        sensorPersistent.setName(sensor.getName());
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional
    public Sensor addType(Long gatewayId, Long sensorId, SensorType type) {
        Sensor sensorPersistent = getSensor(gatewayId, sensorId);
        sensorPersistent.addSensorType(type);
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional
    public Sensor removeType(Long gatewayId, Long sensorId, SensorType type) {
        Sensor sensorPersistent = getSensor(gatewayId, sensorId);
        sensorPersistent.removeSensorType(type);
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional
    public Sensor addData(Long gatewayId, Long sensorId, SensorData data) {
        Sensor sensorPersistent = getSensor(gatewayId, sensorId);
        data.setTimestamp(Instant.now());
        sensorPersistent.addSensorData(data);
        return sensorRepository.save(sensorPersistent);
    }

    private Sensor getSensor(Long gatewayId, Long sensorId) {
        Gateway gateway = gatewayService.get(gatewayId);
        return gateway.getSensor(sensorId);
    }
}
