package com.energybox.backendcodingchallenge.view.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GatewayResponseView {
    private String name;
    private List<SensorResponseView> sensors;
}
