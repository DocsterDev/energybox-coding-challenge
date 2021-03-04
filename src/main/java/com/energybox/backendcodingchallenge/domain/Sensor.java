package com.energybox.backendcodingchallenge.domain;

import com.energybox.backendcodingchallenge.enums.SensorType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Sensor extends BaseNode {
    private String name;
    @Relationship(value = "SENSOR_TYPE", direction = Relationship.Direction.INCOMING)
    private List<SensorType> types;
    @Relationship(value = "SENSOR_DATA", direction = Relationship.Direction.INCOMING)
    private List<SensorData> data;

    public void addSensorType(SensorType type) {
        if (type != null) {
            this.types.add(type);
        }
    }

    public void addSensorData(SensorData data) {
        if (data != null) {
            this.data.add(data);
        }
    }
}
