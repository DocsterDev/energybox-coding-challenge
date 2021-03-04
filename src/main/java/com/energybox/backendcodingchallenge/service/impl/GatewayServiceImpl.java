package com.energybox.backendcodingchallenge.service.impl;

import com.energybox.backendcodingchallenge.node.Gateway;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.service.GatewayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GatewayServiceImpl implements GatewayService {

    private final GatewayRepository gatewayRepository;

    @Transactional
    public Gateway create(Gateway gateway) {
        return gatewayRepository.save(gateway);
    }

    @Transactional(readOnly = true)
    public Gateway get(Long gatewayId) {
        Optional<Gateway> gateway = gatewayRepository.findById(gatewayId);
        gateway.orElseThrow(() -> new RuntimeException("Gateway with id " + gatewayId + " not found."));
        return gateway.get();
    }

    @Transactional
    public Gateway update(Long gatewayId, Gateway gateway) {
        Gateway gatewayPersistent = get(gatewayId);
        gatewayPersistent.setName(gateway.getName());
        return gatewayRepository.save(gatewayPersistent);
    }

    @Transactional(readOnly = true)
    public List<Gateway> getAll() {
        return gatewayRepository.findAll();
    }

    @Transactional
    public Gateway addSensor(Long gatewayId, Sensor sensor) {
        Gateway gatewayPersistent = get(gatewayId);
        gatewayPersistent.addSensor(sensor);
        return gatewayRepository.save(gatewayPersistent);
    }

    @Transactional
    public Gateway removeSensor(Long gatewayId, Long sensorId) {
        Gateway gatewayPersistent = get(gatewayId);
        gatewayPersistent.removeSensor(sensorId);
        return gatewayRepository.save(gatewayPersistent);
    }
}
