package com.energybox.backendcodingchallenge.service.impl;

import com.energybox.backendcodingchallenge.node.Gateway;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.service.GatewayService;
import com.energybox.backendcodingchallenge.view.request.GatewayRequestView;
import com.energybox.backendcodingchallenge.view.request.SensorRequestView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GatewayServiceImpl implements GatewayService {

    private final GatewayRepository gatewayRepository;

    @Transactional
    public Gateway create(GatewayRequestView gatewayRequestView) {
        return gatewayRepository.save(Gateway.builder()
                .name(gatewayRequestView.getName())
                .dateCreated(Instant.now())
                .build());
    }

    @Transactional(readOnly = true)
    public Gateway get(Long gatewayId) {
        Optional<Gateway> gateway = gatewayRepository.findById(gatewayId);
        gateway.orElseThrow(() -> new RuntimeException("Gateway with id " + gatewayId + " not found."));
        return gateway.get();
    }

    @Transactional
    public Gateway update(Long gatewayId, GatewayRequestView gatewayRequestView) {
        Gateway gatewayPersistent = get(gatewayId);
        gatewayPersistent.setName(gatewayRequestView.getName());
        return gatewayRepository.save(gatewayPersistent);
    }

    @Transactional(readOnly = true)
    public List<Gateway> getAll() {
        return gatewayRepository.findAll();
    }

    @Transactional
    public Gateway addSensor(Long gatewayId, SensorRequestView sensorRequestView) {
        Gateway gatewayPersistent = get(gatewayId);
        gatewayPersistent.addSensor(Sensor.builder()
                .name(sensorRequestView.getName())
                .dateCreated(Instant.now())
                .build());
        return gatewayRepository.save(gatewayPersistent);
    }

    @Transactional
    public Gateway removeSensor(Long gatewayId, Long sensorId) {
        Gateway gatewayPersistent = get(gatewayId);
        gatewayPersistent.removeSensor(sensorId);
        return gatewayRepository.save(gatewayPersistent);
    }
}
