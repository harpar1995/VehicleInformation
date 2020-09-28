package com.demo.vehicleInfo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDetailsDTO {
    private String make;
    private String model;
    private String modelYear;
    private String bodyStyle;
    private String engine;
    private String drivetype;
    private String color;
    @JsonProperty("MPG")
    private String mPG;
    private VehicleFeatureDTO vehicleFeature;
    private List<VehiclePriceDTO> vehiclePrice;
}
