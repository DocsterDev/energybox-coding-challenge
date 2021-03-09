package com.energybox.backendcodingchallenge.node;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Node
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Gateway extends BaseNode {
    private String name;
    private Instant dateCreated;
    @Relationship(value = "CONNECTED_TO", direction = Relationship.Direction.INCOMING)
    private List<Sensor> sensors;

    public void addSensor(Sensor sensor) {
        if (ObjectUtils.isEmpty(this.sensors)) {
            this.sensors = new ArrayList<>();
        }
        if (!ObjectUtils.isEmpty(sensor) && !this.sensors.contains(sensor)) {
            sensor.setGatewayId(this.getId());
            this.sensors.add(sensor);
        }
    }

    public void removeSensor(Sensor sensor) {
       this.sensors.removeIf(sensorObj -> sensor.getId().equals(sensorObj.getId()));
    }
}
