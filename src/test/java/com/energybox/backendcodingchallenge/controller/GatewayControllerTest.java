package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.node.Gateway;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.service.GatewayService;
import com.energybox.backendcodingchallenge.view.request.GatewayRequestView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GatewayControllerTest {

    @Autowired
    private GatewayService gatewayService;

    @MockBean
    private GatewayRepository gatewayRepository;

    @Test
    public void create() {
        String name = "gateway-test";
        Gateway gateway = new Gateway();
        gateway.setName(name);
        when(gatewayRepository.save(gateway)).thenReturn(gateway);
        GatewayRequestView gatewayRequestView = new GatewayRequestView();
        gatewayRequestView.setName(name);
        Gateway result = gatewayService.create(gatewayRequestView);
        assertEquals("gateway-test", result.getName());
    }

    @Test
    public void update() {
        String updateName = "gateway-update";
        Gateway gateway = new Gateway();
        gateway.setId(0L);
        gateway.setName(updateName);
        when(gatewayRepository.findById(0L)).thenReturn(java.util.Optional.of(gateway));
        when(gatewayRepository.save(gateway)).thenReturn(gateway);
        GatewayRequestView gatewayRequestView = new GatewayRequestView();
        gatewayRequestView.setName(updateName);
        Gateway result = gatewayService.update(0L, gatewayRequestView);
        assertEquals(0L, result.getId());
        assertEquals("gateway-update", result.getName());
    }

    @Test
    public void getAll() {
        when(gatewayRepository.findAll()).thenReturn(Stream.of(
                new Gateway("gateway-00", Instant.now(), null),
                new Gateway("gateway-01", Instant.now(), null))
                .collect(Collectors.toList()));
        assertEquals(2, gatewayService.getAll().size());
    }

    @Test
    public void get() {
        String name = "gateway-00";
        Gateway gateway = new Gateway();
        gateway.setId(0L);
        gateway.setName(name);
        when(gatewayRepository.findById(0L)).thenReturn(java.util.Optional.of(gateway));
        Gateway result = gatewayService.get(0L);
        assertEquals(0L, result.getId());
        assertEquals(name, result.getName());
    }

    @Test
    public void getAllBySensorsType() {
        when(gatewayRepository.findAll()).thenReturn(Stream.of(
                Gateway.builder().name("gateway-00")
                        .sensors(Stream.of(
                                Sensor.builder().name("sensor-00")
                                        .types(Set.of(SensorType.ELECTRICITY)).build(),
                                Sensor.builder().name("sensor-01")
                                        .types(Set.of(SensorType.HUMIDITY)).build())
                                .collect(Collectors.toList())).build(),
                Gateway.builder().name("gateway-01")
                        .sensors(Stream.of(
                                Sensor.builder().name("sensor-02")
                                        .types(Set.of(SensorType.TEMPERATURE)).build(),
                                Sensor.builder().name("sensor-03")
                                        .types(Set.of(SensorType.ACCELEROMETER)).build())
                                .collect(Collectors.toList())).build(),
                Gateway.builder().name("gateway-02")
                        .sensors(Stream.of(
                                Sensor.builder().name("sensor-04")
                                        .types(Set.of(SensorType.ELECTRICITY)).build(),
                                Sensor.builder().name("sensor-05")
                                        .types(Set.of(SensorType.ACCELEROMETER)).build())
                                .collect(Collectors.toList())).build())
                .collect(Collectors.toList()));
        assertEquals(2, gatewayService.getAllBySensorType(SensorType.ACCELEROMETER).size());
        assertEquals(2, gatewayService.getAllBySensorType(SensorType.ELECTRICITY).size());
    }


//
//    @Test
//    void addSensor() {
//    }
//
//    @Test
//    void removeSensor() {
//    }
    // Add get sensors only from gateway
    // Add check for gateway and sensors named the same
}