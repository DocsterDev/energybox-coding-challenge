package com.energybox.backendcodingchallenge.node;

import com.energybox.backendcodingchallenge.enums.SensorUnit;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SensorData extends BaseNode {
    private Instant timestamp;
    private Double value;
    private SensorUnit unit;
}
