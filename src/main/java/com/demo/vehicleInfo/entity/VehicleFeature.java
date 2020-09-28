package com.demo.vehicleInfo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.demo.vehicleInfo.dto.Feature;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleFeature {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long featureId;
    private Feature featureType;
    private String feature;
}
