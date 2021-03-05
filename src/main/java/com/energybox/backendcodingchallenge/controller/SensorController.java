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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    /**
     * Create a Sensor
     *
     * @param sensorRequestView
     * @return Sensor
     */
    @ApiOperation(value = "Create a Sensor", code = 201, response = Sensor.class)
    @PostMapping
    public ResponseEntity<Sensor> create(@Valid @RequestBody SensorRequestView sensorRequestView) {
        Sensor sensor = sensorService.create(sensorRequestView);
        return new ResponseEntity<>(sensor, HttpStatus.CREATED);
    }

    /**
     * Updates a sensor's metadata
     *
     * @param sensorId
     * @param sensorRequestView
     * @return Sensor
     */
    @ApiOperation(value = "Update Sensor Metadata", code = 200, response = Sensor.class)
    @PutMapping("/{sensorId}")
    public ResponseEntity<Sensor> update(@PathVariable Long sensorId, @Valid @RequestBody SensorRequestView sensorRequestView) {
        Sensor updated = sensorService.update(sensorId, sensorRequestView);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * Get all Sensors. If Type is present, will search by Sensor type
     *
     * @param type
     * @return List<Sensor>
     */
    @ApiOperation(value = "Get all Sensors. If Type is present, will search by Sensor type", code = 200, response = List.class)
    @GetMapping
    public ResponseEntity<List<Sensor>> getAll(@RequestParam(required = false) SensorType type) {
        List<Sensor> sensors;
        if (ObjectUtils.isEmpty(type)) {
            sensors = sensorService.getAll();
        } else {
            sensors = sensorService.getByType(type);
        }
        return new ResponseEntity<>(sensors, HttpStatus.OK);
    }

    /**
     * Get Sensor by GatewayId and SensorId
     *
     * @param sensorId Sensor ID
     * @return Sensor
     */
    @ApiOperation(value = "Get Sensor", code = 200, response = Sensor.class)
    @GetMapping("/{sensorId}")
    public ResponseEntity<Sensor> get(@PathVariable Long sensorId) {
        Sensor updated = sensorService.get(sensorId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * Delete a Sensor
     *
     * @param sensorId
     * @return Void
     */
    @ApiOperation(value = "Delete a Sensor", code = 200)
    @DeleteMapping("/{sensorId}")
    public ResponseEntity<Void> delete(@PathVariable Long sensorId) {
        sensorService.delete(sensorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Adds a Sensor Type to the SensorType enum list
     *
     * @param sensorId
     * @param type
     * @return Sensor
     */
    @ApiOperation(value = "Add a Sensor type", code = 200, response = Sensor.class)
    @PatchMapping("/{sensorId}/type")
    public ResponseEntity<Sensor> addType(@PathVariable Long sensorId, @RequestBody SensorType type) {
        Sensor sensor = sensorService.addType(sensorId, type);
        return new ResponseEntity<>(sensor, HttpStatus.OK);
    }

    /**
     * Removes a Sensor Type from the enum list
     *
     * @param sensorId
     * @param type
     * @return Sensor
     */
    @ApiOperation(value = "Remove a Sensor type", code = 200, response = Sensor.class)
    @DeleteMapping("/{sensorId}/type")
    public ResponseEntity<Sensor> removeType(@PathVariable Long sensorId, @RequestBody SensorType type) {
        Sensor sensor = sensorService.removeType(sensorId, type);
        return new ResponseEntity<>(sensor, HttpStatus.OK);
    }

    /**
     * Add a Data Node to a Sensor
     *
     * @param sensorId
     * @param sensorDataRequestView
     * @return Sensor
     */
    @ApiOperation(value = "Add a Data Node to a Sensor", code = 200, response = Sensor.class)
    @PutMapping("/{sensorId}/data")
    public ResponseEntity<Sensor> addData(@PathVariable Long sensorId, @Valid @RequestBody SensorDataRequestView sensorDataRequestView) {
        Sensor sensor = sensorService.addData(sensorId, sensorDataRequestView);
        return new ResponseEntity<>(sensor, HttpStatus.OK);
    }

    /**
     * Get Sensor Data from Sensor
     *
     * @param sensorId
     * @param direction - Data sort direction, must be either "ASC" or "DESC"
     * @return Sensor
     */
    @ApiOperation(value = "Get Sensor Data from Sensor", code = 200, response = List.class)
    @GetMapping("/{sensorId}/data")
    public ResponseEntity<List<SensorData>> getLatestDataNode(
            @PathVariable Long sensorId,
            @RequestParam(required = false, defaultValue = "DESC") SortDirection direction) {
        List<SensorData> sensorData = sensorService.getData(sensorId, direction);
        return new ResponseEntity<>(sensorData, HttpStatus.OK);
    }

}
