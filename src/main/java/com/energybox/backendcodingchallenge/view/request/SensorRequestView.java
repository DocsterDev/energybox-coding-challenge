package com.energybox.backendcodingchallenge.view.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class SensorRequestView {
    @NotEmpty
    private String name;
}
