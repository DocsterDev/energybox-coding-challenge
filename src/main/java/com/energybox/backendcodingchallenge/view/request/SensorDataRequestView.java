package com.energybox.backendcodingchallenge.view.request;

import com.energybox.backendcodingchallenge.enums.SensorUnit;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class SensorDataRequestView {
    private Double value;
    private SensorUnit unit;
}
