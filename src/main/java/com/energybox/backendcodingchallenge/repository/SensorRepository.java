package com.energybox.backendcodingchallenge.repository;

import com.energybox.backendcodingchallenge.node.Sensor;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SensorRepository extends Neo4jRepository<Sensor, Long> {

}
