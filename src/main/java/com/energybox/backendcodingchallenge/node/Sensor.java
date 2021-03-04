package com.energybox.backendcodingchallenge.node;

import com.energybox.backendcodingchallenge.enums.SensorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Node
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Sensor extends BaseNode {
    private String name;
    private Instant dateCreated;
    @Relationship(value = "SENSOR_TYPE", direction = Relationship.Direction.INCOMING)
    private Set<SensorType> types;
    @Relationship(value = "SENSOR_DATA", direction = Relationship.Direction.INCOMING)
    private List<SensorData> data;

    public void addSensorType(SensorType type) {
        if (ObjectUtils.isEmpty(this.types)) {
            this.types = new HashSet<>();
        }
        if (type != null) {
            this.types.add(type);
        }
    }

    public void removeSensorType(SensorType type) {
        if (type != null) {
            this.types.remove(type);
        }
    }

    public void addSensorData(SensorData data) {
        if (data != null) {
            this.data.add(data);
        }
    }
}
