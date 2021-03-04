package com.energybox.backendcodingchallenge.node;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.List;

@Node
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Gateway extends BaseNode {
    private String name;
    private Instant dateCreated;
    @Relationship(value = "CONNECTED_TO", direction = Relationship.Direction.INCOMING)
    private List<Sensor> sensors;

    public void addSensor(Sensor sensor) {
        if (!ObjectUtils.isEmpty(sensor)) {
            sensor.setConnected(true);
            this.sensors.add(sensor);
        }
    }

    public void removeSensor(Long sensorId) {
        // TODO - Set Connected to false - Make sure it does not delete the entity
        for (Sensor sensor: this.sensors) {
            if (sensorId.equals(sensor.getId())) {
                sensor.setConnected(false);
                this.sensors.remove(sensor);
            }
        }
    }
}
