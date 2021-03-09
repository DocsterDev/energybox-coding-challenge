package com.energybox.backendcodingchallenge.view.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@NoArgsConstructor
public class GatewayRequestView {
    @NotEmpty
    private String name;
    private Instant dateCreated;
}
