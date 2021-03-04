package com.energybox.backendcodingchallenge.service.impl;

import com.energybox.backendcodingchallenge.node.Gateway;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.node.SensorData;
import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import com.energybox.backendcodingchallenge.service.GatewayService;
import com.energybox.backendcodingchallenge.service.SensorService;
import com.energybox.backendcodingchallenge.view.request.SensorDataRequestView;
import com.energybox.backendcodingchallenge.view.request.SensorRequestView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final GatewayService gatewayService;
    private final SensorRepository sensorRepository;

    @Transactional(readOnly = true)
    public Sensor get(Long gatewayId, Long sensorId) {
        Gateway gateway = gatewayService.get(gatewayId);
        return gateway.getSensor(sensorId);
    }

    @Transactional
    public Sensor update(Long gatewayId, Long sensorId, SensorRequestView sensorRequestView) {
        Sensor sensorPersistent = get(gatewayId, sensorId);
        sensorPersistent.setName(sensorRequestView.getName());
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional
    public Sensor addType(Long gatewayId, Long sensorId, SensorType type) {
        Sensor sensorPersistent = get(gatewayId, sensorId);
        sensorPersistent.addSensorType(type);
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional
    public Sensor removeType(Long gatewayId, Long sensorId, SensorType type) {
        Sensor sensorPersistent = get(gatewayId, sensorId);
        sensorPersistent.removeSensorType(type);
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional
    public Sensor addData(Long gatewayId, Long sensorId, SensorDataRequestView sensorDataRequestView) {
        Sensor sensorPersistent = get(gatewayId, sensorId);
        sensorPersistent.addSensorData(SensorData.builder()
                .unit(sensorDataRequestView.getUnit())
                .value(sensorDataRequestView.getValue())
                .timestamp(Instant.now())
                .build());
        return sensorRepository.save(sensorPersistent);
    }
}
