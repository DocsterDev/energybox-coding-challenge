package com.energybox.backendcodingchallenge.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

@Data
@NoArgsConstructor
public class BaseNode {
    @Id
    @GeneratedValue
    private Long id;
}
