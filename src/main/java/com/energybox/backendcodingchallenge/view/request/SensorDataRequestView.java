package com.energybox.backendcodingchallenge.view.request;

import com.energybox.backendcodingchallenge.enums.SensorUnit;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SensorDataRequestView {
    private Double value;
    private SensorUnit unit;
}
