package com.energybox.backendcodingchallenge.service.impl;

import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.node.SensorData;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import com.energybox.backendcodingchallenge.service.SensorService;
import com.energybox.backendcodingchallenge.view.request.SensorDataRequestView;
import com.energybox.backendcodingchallenge.view.request.SensorRequestView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    @Transactional(readOnly = true)
    public Sensor get(Long sensorId) {
        Optional<Sensor> sensor = sensorRepository.findById(sensorId);
        sensor.orElseThrow(() -> new RuntimeException("Sensor with id " + sensorId + " not found."));
        return sensor.get();
    }

    @Transactional
    public Sensor create(SensorRequestView sensorRequestView) {
        return sensorRepository.save(Sensor.builder()
                .name(sensorRequestView.getName())
                .dateCreated(Instant.now())
                .build());
    }

    @Transactional
    public Sensor update(Long sensorId, SensorRequestView sensorRequestView) {
        Sensor sensorPersistent = get(sensorId);
        sensorPersistent.setName(sensorRequestView.getName());
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional
    public void delete(Long sensorId) {
        sensorRepository.deleteById(sensorId);
    }

    @Transactional
    public Sensor addType(Long sensorId, SensorType type) {
        Sensor sensorPersistent = get(sensorId);
        sensorPersistent.addSensorType(type);
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional
    public Sensor removeType(Long sensorId, SensorType type) {
        Sensor sensorPersistent = get(sensorId);
        sensorPersistent.removeSensorType(type);
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional
    public Sensor addData(Long sensorId, SensorDataRequestView sensorDataRequestView) {
        Sensor sensorPersistent = get(sensorId);
        sensorPersistent.addSensorData(SensorData.builder()
                .unit(sensorDataRequestView.getUnit())
                .value(sensorDataRequestView.getValue())
                .timestamp(Instant.now())
                .build());
        return sensorRepository.save(sensorPersistent);
    }
}
