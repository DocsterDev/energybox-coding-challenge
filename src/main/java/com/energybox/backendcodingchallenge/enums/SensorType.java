package com.energybox.backendcodingchallenge.enums;

import lombok.Getter;

@Getter
public enum SensorType {
    HUMIDITY(SensorUnit.PERCENTAGE),
    TEMPERATURE(SensorUnit.CELSIUS),
    ELECTRICITY(SensorUnit.VOLTS),
    ACCELEROMETER(SensorUnit.G);

    public final SensorUnit unit;

    SensorType(SensorUnit unit) {this.unit = unit;};
}
