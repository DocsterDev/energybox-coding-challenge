package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.enums.SortDirection;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.node.SensorData;
import com.energybox.backendcodingchallenge.service.SensorService;
import com.energybox.backendcodingchallenge.view.request.SensorDataRequestView;
import com.energybox.backendcodingchallenge.view.request.SensorRequestView;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    /**
     * Create sensor
     *
     * @param sensorRequestView
     * @return Sensor
     */
    @ApiOperation(value = "Create sensor", code = 201, response = Sensor.class)
    @PostMapping
    public ResponseEntity<Sensor> create(@Valid @RequestBody SensorRequestView sensorRequestView) {
        Sensor sensor;
        try {
            sensor = sensorService.create(sensorRequestView);
        } catch (Exception e) {
            log.error("Error creating sensor: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error creating sensor: " + e.getLocalizedMessage(), e);
        }
        return new ResponseEntity<>(sensor, HttpStatus.CREATED);
    }

    /**
     * Update sensor
     *
     * @param sensorId
     * @param sensorRequestView
     * @return Sensor
     */
    @ApiOperation(value = "Update sensor", code = 200, response = Sensor.class)
    @PutMapping("/{sensorId}")
    public ResponseEntity<Sensor> update(
            @PathVariable Long sensorId,
            @Valid @RequestBody SensorRequestView sensorRequestView) {
        Sensor sensor;
        try {
            sensor = sensorService.update(sensorId, sensorRequestView);
        } catch (Exception e) {
            log.error("Error updating sensor: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error updating sensor: " + e.getLocalizedMessage(), e);
        }
        return new ResponseEntity<>(sensor, HttpStatus.OK);
    }

    /**
     * Get all sensors
     *
     * @param type
     * @return List<Sensor>
     */
    @ApiOperation(value = "Get all sensors", code = 200, response = List.class)
    @GetMapping
    public ResponseEntity<List<Sensor>> getAll(@RequestParam(required = false) SensorType type) {
        List<Sensor> sensors;
        try {
            if (ObjectUtils.isEmpty(type)) {
                sensors = sensorService.getAll();
            } else {
                sensors = sensorService.getByType(type);
            }
        } catch (Exception e) {
            log.error("Error fetching sensors: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error fetching sensors: " + e.getLocalizedMessage(), e);
        }
        return new ResponseEntity<>(sensors, HttpStatus.OK);
    }

    /**
     * Get sensor
     *
     * @param sensorId Sensor ID
     * @return Sensor
     */
    @ApiOperation(value = "Get sensor", code = 200, response = Sensor.class)
    @GetMapping("/{sensorId}")
    public ResponseEntity<Sensor> get(@PathVariable Long sensorId) {
        Sensor sensor;
        try {
            sensor = sensorService.get(sensorId);
        } catch (Exception e) {
            log.error("Error fetching sensor: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error fetching sensor: " + e.getLocalizedMessage(), e);
        }
        return new ResponseEntity<>(sensor, HttpStatus.OK);
    }

    /**
     * Delete sensor
     *
     * @param sensorId
     * @return Void
     */
    @ApiOperation(value = "Delete sensor", code = 200)
    @DeleteMapping("/{sensorId}")
    public ResponseEntity<Void> delete(@PathVariable Long sensorId) {
        try {
            sensorService.delete(sensorId);
        } catch (Exception e) {
            log.error("Error deleting sensor: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error deleting sensor: " + e.getLocalizedMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Add sensor type to sensor
     *
     * @param sensorId
     * @param type
     * @return Sensor
     */
    @ApiOperation(value = "Add sensor type to sensor", code = 200, response = Sensor.class)
    @PatchMapping("/{sensorId}/type")
    public ResponseEntity<Sensor> addType(@PathVariable Long sensorId, @RequestBody SensorType type) {
        Sensor sensor;
        try {
            sensor = sensorService.addType(sensorId, type);
        } catch (Exception e) {
            log.error("Error adding sensor type to sensor: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error adding sensor type to sensor: " + e.getLocalizedMessage(), e);
        }
        return new ResponseEntity<>(sensor, HttpStatus.OK);
    }

    /**
     * Removes sensor type from sensor
     *
     * @param sensorId
     * @param type
     * @return Sensor
     */
    @ApiOperation(value = "Removes sensor type from sensor", code = 200, response = Sensor.class)
    @DeleteMapping("/{sensorId}/type")
    public ResponseEntity<Sensor> removeType(@PathVariable Long sensorId, @RequestBody SensorType type) {
        Sensor sensor;
        try {
            sensor = sensorService.removeType(sensorId, type);
        } catch (Exception e) {
            log.error("Error removing sensor type from sensor: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error removing sensor type from sensor: " + e.getLocalizedMessage(), e);
        }
        return new ResponseEntity<>(sensor, HttpStatus.OK);
    }

    /**
     * Add data node to sensor
     *
     * @param sensorId
     * @param sensorDataRequestView
     * @return Sensor
     */
    @ApiOperation(value = "Add data node to sensor", code = 200, response = Sensor.class)
    @PutMapping("/{sensorId}/data")
    public ResponseEntity<Sensor> addData(@PathVariable Long sensorId, @Valid @RequestBody SensorDataRequestView sensorDataRequestView) {
        Sensor sensor;
        try {
            sensor = sensorService.addData(sensorId, sensorDataRequestView);
        } catch (Exception e) {
            log.error("Error adding data node to sensor: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error adding data node to sensor: " + e.getLocalizedMessage(), e);
        }
        return new ResponseEntity<>(sensor, HttpStatus.OK);
    }

    /**
     * Get sensor data from sensor
     *
     * @param sensorId
     * @param direction - Data sort direction, must be either "ASC" or "DESC"
     * @return Sensor
     */
    @ApiOperation(value = "Get sensor data from sensor", code = 200, response = List.class)
    @GetMapping("/{sensorId}/data")
    public ResponseEntity<List<SensorData>> getLatestDataNode(
            @PathVariable Long sensorId,
            @RequestParam(required = false, defaultValue = "DESC") SortDirection direction) {
        List<SensorData> sensorData;
        try {
            sensorData = sensorService.getData(sensorId, direction);
        } catch (Exception e) {
            log.error("Error getting sensor data from sensor: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error getting sensor data from sensor: " + e.getLocalizedMessage(), e);
        }
        return new ResponseEntity<>(sensorData, HttpStatus.OK);
    }
}
