package com.energybox.backendcodingchallenge.node;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Node
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Gateway extends BaseNode {
    private String name;
    @Relationship(value = "CONNECTED_TO", direction = Relationship.Direction.INCOMING)
    private List<Sensor> sensors;

    public Sensor getSensor(Long sensorId) {
       return this.sensors
               .stream()
               .filter(sensor -> sensor.getId().equals(sensorId))
               .findFirst()
               .orElseThrow(() -> new RuntimeException("Sensor with id " + sensorId + " not found."));
    }

    public void addSensor(Sensor sensor) {
        if (!ObjectUtils.isEmpty(sensor)) {
            this.sensors.add(sensor);
        }
    }

    public void removeSensor(Long sensorId) {
        this.sensors
                .removeIf(sensor -> sensorId.equals(sensor.getId()));
    }
}
