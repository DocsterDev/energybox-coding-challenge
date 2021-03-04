package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.node.Gateway;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.service.GatewayService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/gateways")
public class GatewayController {

    private final GatewayService gatewayService;

    /**
     * Create a new Gateway
     *
     * @param gateway
     * @return Gateway
     */
    @ApiOperation(value = "Create a Gateway", code = 201, response = Gateway.class)
    @PostMapping
    public ResponseEntity<Gateway> create(@RequestBody Gateway gateway) {
        // TODO - Validate name is present (make a view object)
        Gateway created = gatewayService.create(gateway);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Update existing Gateway
     *
     * @param gatewayId
     * @param gateway
     * @return Gateway
     */
    @ApiOperation(value = "Update Gateway Metadata", code = 200, response = Gateway.class)
    @PutMapping("/{gatewayId}")
    public ResponseEntity<Gateway> update(@PathVariable Long gatewayId, @RequestBody Gateway gateway) {
        Gateway updated = gatewayService.update(gatewayId, gateway);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * Fetch Gateway by Id
     *
     * @param gatewayId
     * @return Gateway
     */
    @ApiOperation(value = "Fetch Gateway by Id", code = 200, response = Gateway.class)
    @GetMapping("/{gatewayId}")
    public ResponseEntity<Gateway> get(@PathVariable Long gatewayId) {
        Gateway gateway = gatewayService.get(gatewayId);
        return new ResponseEntity<>(gateway, HttpStatus.OK);
    }

    /**
     * Fetch all existing Gateways
     *
     * @return List<Gateway>
     */
    @ApiOperation(value = "Fetch all Gateways", code = 200, response = List.class)
    @GetMapping
    public ResponseEntity<List<Gateway>> getAll() {
        List<Gateway> gateways = gatewayService.getAll();
        return new ResponseEntity<>(gateways, HttpStatus.OK);
    }

    /**
     * Add a Sensor to an existing Gateway
     *
     * @param gatewayId
     * @param sensor
     * @return Gateway
     */
    @ApiOperation(value = "Add a Sensor to a Gateway", code = 201, response = Gateway.class)
    @PutMapping("/{gatewayId}/sensors")
    public ResponseEntity<Gateway> addSensor(@PathVariable Long gatewayId, @RequestBody Sensor sensor) {
        Gateway gateway = gatewayService.addSensor(gatewayId, sensor);
        return new ResponseEntity<>(gateway, HttpStatus.CREATED);
    }

    /**
     * Removes a Sensor from an existing Gateway
     *
     * @param gatewayId
     * @param sensorId
     * @return Void
     */
    @ApiOperation(value = "Remove a Sensor from a Gateway", code = 200)
    @DeleteMapping("/{gatewayId}/sensors/{sensorId}")
    public ResponseEntity<Void> removeSensor(@PathVariable Long gatewayId, @PathVariable Long sensorId) {
        gatewayService.removeSensor(gatewayId, sensorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
