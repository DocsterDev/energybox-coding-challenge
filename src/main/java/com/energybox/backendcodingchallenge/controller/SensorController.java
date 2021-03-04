package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.service.SensorService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/gateways")
public class SensorController {

    private final SensorService sensorService;

    /**
     * Updates a sensor's metadata
     *
     * @param gatewayId
     * @param sensorId
     * @param sensor
     * @return Sensor
     */
    @ApiOperation(value = "Update Sensor Metadata", code = 200, response = Sensor.class)
    @PutMapping("/{gatewayId}/sensors/{sensorId}")
    public ResponseEntity<Sensor> update(@PathVariable Long gatewayId, @PathVariable Long sensorId, @RequestBody Sensor sensor) {
        Sensor updated = sensorService.update(gatewayId, sensorId, sensor);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * Adds a Sensor Type to the SensorType enum list
     *
     * @param gatewayId
     * @param sensorId
     * @param type
     * @return Sensor
     */
    @ApiOperation(value = "Add a Sensor type", code = 200, response = Sensor.class)
    @PatchMapping("/{gatewayId}/sensors/{sensorId}/type")
    public ResponseEntity<Sensor> addType(@PathVariable Long gatewayId, @PathVariable Long sensorId, @RequestBody SensorType type) {
        Sensor sensor = sensorService.addType(gatewayId, sensorId, type);
        return new ResponseEntity<>(sensor, HttpStatus.OK);
    }

    /**
     * Removes a Sensor Type from the enum list
     *
     * @param gatewayId
     * @param sensorId
     * @param type
     * @return Sensor
     */
    @ApiOperation(value = "Remove a Sensor type", code = 200, response = Sensor.class)
    @DeleteMapping("/{gatewayId}/sensors/{sensorId}/type")
    public ResponseEntity<Sensor> removeType(@PathVariable Long gatewayId, @PathVariable Long sensorId, @RequestBody SensorType type) {
        Sensor sensor = sensorService.removeType(gatewayId, sensorId, type);
        return new ResponseEntity<>(sensor, HttpStatus.OK);
    }
}
