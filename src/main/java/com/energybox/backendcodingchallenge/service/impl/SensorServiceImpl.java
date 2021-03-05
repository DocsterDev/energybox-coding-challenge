package com.energybox.backendcodingchallenge.service.impl;

import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.enums.SortDirection;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.node.SensorData;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import com.energybox.backendcodingchallenge.service.SensorService;
import com.energybox.backendcodingchallenge.view.request.SensorDataRequestView;
import com.energybox.backendcodingchallenge.view.request.SensorRequestView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    @Transactional
    public Sensor create(SensorRequestView sensorRequestView) {
        log.info("Creating a new Sensor with name: {}", sensorRequestView.getName());
        return sensorRepository.save(Sensor.builder()
                .name(sensorRequestView.getName())
                .dateCreated(Instant.now())
                .build());
    }

    @Transactional
    public Sensor update(Long sensorId, SensorRequestView sensorRequestView) {
        log.info("Updating Sensor with id: {}", sensorId);
        Sensor sensorPersistent = get(sensorId);
        sensorPersistent.setName(sensorRequestView.getName());
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional(readOnly = true)
    public List<Sensor> getAll() {
        log.info("Fetching all Sensors");
        return sensorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Sensor get(Long sensorId) {
        log.info("Fetching Sensor with id: {}", sensorId);
        Optional<Sensor> sensor = sensorRepository.findById(sensorId);
        sensor.orElseThrow(() -> new RuntimeException("Sensor with id " + sensorId + " not found."));
        return sensor.get();
    }

    public List<Sensor> getByType(SensorType type) {
        return sensorRepository.findAll().stream()
                .filter(sensor -> !ObjectUtils.isEmpty(sensor.getTypes())
                        && sensor.getTypes().contains(type))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long sensorId) {
        log.info("Deleting Sensor with id: {}", sensorId);
        sensorRepository.deleteById(sensorId);
    }

    @Transactional
    public Sensor addType(Long sensorId, SensorType type) {
        log.info("Adding type {} to Sensor with id: {}", type, sensorId);
        Sensor sensorPersistent = get(sensorId);
        sensorPersistent.addSensorType(type);
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional
    public Sensor removeType(Long sensorId, SensorType type) {
        log.info("Removing type {} from Sensor with id: {}", type, sensorId);
        Sensor sensorPersistent = get(sensorId);
        sensorPersistent.removeSensorType(type);
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional
    public Sensor addData(Long sensorId, SensorDataRequestView sensorDataRequestView) {
        log.info("Adding data object: {} to Sensor with id: {}", sensorDataRequestView.toString(), sensorId);
        Sensor sensorPersistent = get(sensorId);
        sensorPersistent.addSensorData(SensorData.builder()
                .unit(sensorDataRequestView.getUnit())
                .value(sensorDataRequestView.getValue())
                .timestamp(Instant.now())
                .build());
        return sensorRepository.save(sensorPersistent);
    }

    @Transactional(readOnly = true)
    public List<SensorData> getData(Long sensorId, SortDirection direction) {
        log.info("Fetching Sensor Data from Sensor with id: {} with sort direction: {}", sensorId, direction);
        Sensor sensorPersistent = get(sensorId);
        return sensorPersistent.getData()
                .stream()
                .sorted(SortDirection.ACS.equals(direction) ? Comparator.comparing(SensorData::getTimestamp) :
                        Comparator.comparing(SensorData::getTimestamp).reversed())
                .collect(Collectors.toList());
    }
}
