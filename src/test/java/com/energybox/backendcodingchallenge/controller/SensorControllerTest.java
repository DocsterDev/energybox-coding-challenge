package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.enums.SensorType;
import com.energybox.backendcodingchallenge.enums.SensorUnit;
import com.energybox.backendcodingchallenge.enums.SortDirection;
import com.energybox.backendcodingchallenge.node.Sensor;
import com.energybox.backendcodingchallenge.node.SensorData;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import com.energybox.backendcodingchallenge.service.SensorService;
import com.energybox.backendcodingchallenge.view.request.SensorDataRequestView;
import com.energybox.backendcodingchallenge.view.request.SensorRequestView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SensorControllerTest {

    @Autowired
    private SensorService sensorService;

    @MockBean
    private SensorRepository sensorRepository;

    @Test
    public void create() {
        String name = "sensor-00";
        Sensor sensor = new Sensor();
        sensor.setName(name);
        when(sensorRepository.save(sensor)).thenReturn(sensor);
        SensorRequestView sensorRequestView = new SensorRequestView();
        sensorRequestView.setName(name);
        Sensor result = sensorService.create(sensorRequestView);
        assertEquals("sensor-00", result.getName());
    }

    @Test
    public void update() {
        Long sensorId = 0L;
        String name = "sensor-00";
        Sensor sensor = new Sensor();
        sensor.setId(sensorId);
        sensor.setName(name);
        when(sensorRepository.findById(sensorId)).thenReturn(java.util.Optional.of(sensor));
        when(sensorRepository.save(sensor)).thenReturn(sensor);
        SensorRequestView sensorRequestView = new SensorRequestView();
        sensorRequestView.setName("sensor-01");
        Sensor result = sensorService.update(sensorId, sensorRequestView);
        assertEquals("sensor-01", result.getName());
    }

    @Test
    public void getAll() {
        when(sensorRepository.findAll()).thenReturn(Stream.of(
                new Sensor("sensor-00", Instant.now(), null, null, null),
                new Sensor("sensor-01", Instant.now(), null, null, null))
                .collect(Collectors.toList()));
        assertEquals(2, sensorService.getAll().size());
    }

    @Test
    public void get() {
        Long sensorId = 0L;
        String name = "sensor-00";
        Sensor sensor = new Sensor();
        sensor.setId(sensorId);
        sensor.setName(name);
        when(sensorRepository.findById(sensorId)).thenReturn(java.util.Optional.of(sensor));
        Sensor result = sensorService.get(sensorId);
        assertEquals(0L, result.getId());
        assertEquals(name, result.getName());
    }

    @Test
    public void addType() {
        Long sensorId = 0L;
        String name = "sensor-00";
        Sensor sensor = new Sensor();
        sensor.setId(sensorId);
        sensor.setName(name);
        when(sensorRepository.findById(sensorId)).thenReturn(java.util.Optional.of(sensor));
        when(sensorRepository.save(sensor)).thenReturn(sensor);
        Sensor result = sensorService.addType(sensorId, SensorType.ELECTRICITY);
        assert(result.getTypes().contains(SensorType.ELECTRICITY));
    }

    @Test
    public void removeType() {
        Long sensorId = 0L;
        String name = "sensor-00";
        Sensor sensor = new Sensor();
        sensor.setId(sensorId);
        sensor.setName(name);
        sensor.setTypes(new HashSet<>());
        sensor.getTypes().add(SensorType.ELECTRICITY);
        when(sensorRepository.findById(sensorId)).thenReturn(java.util.Optional.of(sensor));
        when(sensorRepository.save(sensor)).thenReturn(sensor);
        Sensor result = sensorService.removeType(sensorId, SensorType.ELECTRICITY);
        assert(!result.getTypes().contains(SensorType.ELECTRICITY));
    }

    @Test
    public void addData() {
        Long sensorId = 0L;
        String name = "sensor-00";
        Sensor sensor = new Sensor();
        sensor.setId(sensorId);
        sensor.setName(name);
        sensor.setData(new ArrayList<>());
        when(sensorRepository.findById(sensorId)).thenReturn(java.util.Optional.of(sensor));
        when(sensorRepository.save(sensor)).thenReturn(sensor);
        SensorDataRequestView sensorDataRequestView = new SensorDataRequestView();
        sensorDataRequestView.setUnit(SensorUnit.VOLTS);
        sensorDataRequestView.setValue(2.4);
        Sensor result = sensorService.addData(sensorId, sensorDataRequestView);
        assertEquals(1, result.getData().size());
    }

    @Test
    public void getData() {
        Long sensorId = 0L;
        String name = "sensor-00";
        Sensor sensor = new Sensor();
        sensor.setId(sensorId);
        sensor.setName(name);
        sensor.setData(List.of(
                SensorData.builder().value(2.5).unit(SensorUnit.VOLTS)
                        .timestamp(Instant.now().minus(2, ChronoUnit.MINUTES)).build(),
                SensorData.builder().value(2.2).unit(SensorUnit.VOLTS)
                        .timestamp(Instant.now().minus(1, ChronoUnit.MINUTES)).build(),
                SensorData.builder().value(2.6).unit(SensorUnit.VOLTS)
                        .timestamp(Instant.now().minus(3, ChronoUnit.MINUTES)).build()));
        when(sensorRepository.findById(sensorId)).thenReturn(java.util.Optional.of(sensor));
        List<SensorData> result = sensorService.getData(sensorId, SortDirection.DESC);
        assertEquals(2.2, result.get(0).getValue());
    }
}