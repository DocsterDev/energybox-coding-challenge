package com.energybox.backendcodingchallenge.node;

import com.energybox.backendcodingchallenge.enums.SensorUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.Instant;

@Node
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SensorData extends BaseNode {
    private Instant timestamp;
    private Double value;
    private SensorUnit unit;
}
