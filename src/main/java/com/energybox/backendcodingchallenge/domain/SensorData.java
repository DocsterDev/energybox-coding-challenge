package com.energybox.backendcodingchallenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.Instant;

@Node
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SensorData extends BaseNode {
    private Instant timestamp;
    private Double value;
    @JsonIgnore
    @Relationship(value = "SENSOR_DATA", direction = Relationship.Direction.OUTGOING)
    private Sensor sensor;
}
