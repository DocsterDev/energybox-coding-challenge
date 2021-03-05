package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.node.Gateway;
import com.energybox.backendcodingchallenge.service.GatewayService;
import com.energybox.backendcodingchallenge.view.request.GatewayRequestView;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/gateways")
public class GatewayController {

    private final GatewayService gatewayService;

    /**
     * Create gateway
     *
     * @param gatewayRequestView
     * @return Gateway
     */
    @ApiOperation(value = "Create gateway", code = 201, response = Gateway.class)
    @PostMapping
    public ResponseEntity<Gateway> create(@Valid @RequestBody GatewayRequestView gatewayRequestView) {
        Gateway gateway;
        try {
            gateway = gatewayService.create(gatewayRequestView);
        } catch (Exception e) {
            log.error("Error creating gateway: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error creating gateway", e);
        }
        return new ResponseEntity<>(gateway, HttpStatus.CREATED);
    }

    /**
     * Update gateway
     *
     * @param gatewayId
     * @param gatewayRequestView
     * @return Gateway
     */
    @ApiOperation(value = "Update gateway", code = 200, response = Gateway.class)
    @PutMapping("/{gatewayId}")
    public ResponseEntity<Gateway> update(
            @PathVariable Long gatewayId,
            @Valid @RequestBody GatewayRequestView gatewayRequestView) {
        Gateway updated;
        try {
            updated = gatewayService.update(gatewayId, gatewayRequestView);
        } catch (Exception e) {
            log.error("Error updating gateway: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error updating gateway", e);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * Get all gateways
     *
     * @param type
     * @return List<Gateway>
     */
    @ApiOperation(value = "Get all gateways", code = 200, response = List.class)
    @GetMapping
    public ResponseEntity<List<Gateway>> getAllBySensorsType(
            @RequestParam(required = false) SensorType type) {
        List<Gateway> gateways;
        try {
            if (ObjectUtils.isEmpty(type)) {
                gateways = gatewayService.getAll();
            } else {
                gateways = gatewayService.getAllBySensorType(type);
            }
        } catch (Exception e) {
            log.error("Error fetching gateways by sensor type: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error fetching gateways by sensor type", e);
        }
        return new ResponseEntity<>(gateways, HttpStatus.OK);
    }

    /**
     * Get gateway
     *
     * @param gatewayId
     * @return Gateway
     */
    @ApiOperation(value = "Get gateway", code = 200, response = Gateway.class)
    @GetMapping("/{gatewayId}")
    public ResponseEntity<Gateway> get(@PathVariable Long gatewayId) {
        Gateway gateway;
        try {
            gateway = gatewayService.get(gatewayId);
        } catch (Exception e) {
            log.error("Error fetching gateway: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error fetching gateway", e);
        }
        return new ResponseEntity<>(gateway, HttpStatus.OK);
    }

    /**
     * Delete gateway
     *
     * @param gatewayId
     * @return Void
     */
    @ApiOperation(value = "Delete gateway", code = 200)
    @DeleteMapping("/{gatewayId}")
    public ResponseEntity<Void> delete(@PathVariable Long gatewayId) {
        try {
            gatewayService.delete(gatewayId);
        } catch (Exception e) {
            log.error("Error deleting gateway: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error deleting gateway", e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Add sensor to gateway
     *
     * @param gatewayId
     * @param sensorId
     * @return Gateway
     */
    @ApiOperation(value = "Add sensor to gateway", code = 200, response = Gateway.class)
    @PutMapping("/{gatewayId}/sensors/{sensorId}")
    public ResponseEntity<Gateway> addSensor(@PathVariable Long gatewayId, @PathVariable Long sensorId) {
        Gateway gateway;
        try {
            gateway = gatewayService.addSensor(gatewayId, sensorId);
        } catch (Exception e) {
            log.error("Error adding sensor to gateway: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error adding sensor to gateway", e);
        }
        return new ResponseEntity<>(gateway, HttpStatus.OK);
    }

    /**
     * Remove sensor from gateway
     *
     * @param gatewayId
     * @param sensorId
     * @return Gateway
     */
    @ApiOperation(value = "Remove sensor from gateway", code = 200, response = Gateway.class)
    @DeleteMapping("/{gatewayId}/sensors/{sensorId}")
    public ResponseEntity<Gateway> removeSensor(@PathVariable Long gatewayId, @PathVariable Long sensorId) {
        Gateway gateway;
        try {
            gateway = gatewayService.removeSensor(gatewayId, sensorId);
        } catch (Exception e) {
            log.error("Error removing sensor from gateway: {}", e.getLocalizedMessage());
            throw new RuntimeException("Error removing sensor from gateway", e);
        }
        return new ResponseEntity<>(gateway, HttpStatus.OK);
    }
}
