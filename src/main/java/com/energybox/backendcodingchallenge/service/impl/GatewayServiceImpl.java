package com.energybox.backendcodingchallenge.service.impl;

import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.node.Gateway;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import com.energybox.backendcodingchallenge.service.GatewayService;
import com.energybox.backendcodingchallenge.service.SensorService;
import com.energybox.backendcodingchallenge.view.request.GatewayRequestView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class GatewayServiceImpl implements GatewayService {

    private final GatewayRepository gatewayRepository;
    private final SensorRepository sensorRepository;
    private final SensorService sensorService;

    @Transactional
    public Gateway create(GatewayRequestView gatewayRequestView) {
        log.info("Creating a new Gateway wth name: {}", gatewayRequestView.getName());
        return gatewayRepository.save(Gateway.builder()
                .name(gatewayRequestView.getName())
                .dateCreated(gatewayRequestView.getDateCreated())
                .build());
    }

    @Transactional
    public Gateway update(Long gatewayId, GatewayRequestView gatewayRequestView) {
        log.info("Updating Gateway with id: {}", gatewayId);
        Gateway gatewayPersistent = get(gatewayId);
        gatewayPersistent.setName(gatewayRequestView.getName());
        return gatewayRepository.save(gatewayPersistent);
    }

    @Transactional(readOnly = true)
    public List<Gateway> getAll() {
        log.info("Fetching all Gateways");
        return gatewayRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Gateway get(Long gatewayId) {
        log.info("Fetching Gateway with id: {}", gatewayId);
        Optional<Gateway> gateway = gatewayRepository.findById(gatewayId);
        gateway.orElseThrow(() -> new RuntimeException("Gateway with id " + gatewayId + " not found."));
        return gateway.get();
    }

    @Transactional(readOnly = true)
    public List<Gateway> getAllBySensorType(SensorType type) {
        log.info("Fetching all Gateways By Sensor Type");
        return gatewayRepository.findAll()
                .stream()
                .filter(gateway -> gateway
                        .getSensors()
                        .stream()
                        .anyMatch(sensor -> !ObjectUtils.isEmpty(sensor.getTypes())
                                && sensor.getTypes().contains(type)))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long gatewayId) {
        log.info("Deleting Gateway with id {}", gatewayId);
        gatewayRepository.deleteById(gatewayId);
    }

    @Transactional
    public Gateway addSensor(Long gatewayId, Long sensorId) {
        log.info("Adding Sensor id: {} to Gateway id: {}", sensorId, gatewayId);
        Gateway gatewayPersistent = get(gatewayId);
        Sensor sensor = sensorService.get(sensorId);
        if (!ObjectUtils.isEmpty(sensor.getGatewayId())) {
            log.error("Sensor with id: {} is already connected to another Gateway", sensorId);
            throw new RuntimeException("Sensor with id: " + sensorId + " is already connected to another Gateway");
        }
        gatewayPersistent.addSensor(sensor);
        return gatewayRepository.save(gatewayPersistent);
    }

    @Transactional
    public Gateway removeSensor(Long gatewayId, Long sensorId) {
        log.info("Removing Sensor id: {} from Gateway id: {}", sensorId, gatewayId);
        Gateway gatewayPersistent = get(gatewayId);
        Sensor sensor = sensorService.get(sensorId);
        sensor.setGatewayId(null);
        gatewayPersistent.removeSensor(sensor);
        sensorRepository.save(sensor);
        return gatewayRepository.save(gatewayPersistent);
    }
}
