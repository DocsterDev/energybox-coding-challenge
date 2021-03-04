package com.energybox.backendcodingchallenge.repository;

import com.energybox.backendcodingchallenge.node.Gateway;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GatewayRepository extends Neo4jRepository<Gateway, Long> {

}
