package com.energybox.backendcodingchallenge.view.response;

import com.energybox.backendcodingchallenge.enums.SensorUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorDataResponseView {
    private Double value;
    private SensorUnit unit;
}
