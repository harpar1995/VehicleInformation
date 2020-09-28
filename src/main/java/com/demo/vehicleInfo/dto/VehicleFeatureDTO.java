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
public class VehicleFeatureDTO {
	@JsonProperty("Exterior")
	private List<String> exterior;
	@JsonProperty("Interior")
	private List<String> interior;
}
