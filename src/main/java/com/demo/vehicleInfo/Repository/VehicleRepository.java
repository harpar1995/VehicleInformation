package com.demo.vehicleInfo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.vehicleInfo.dto.Feature;
import com.demo.vehicleInfo.entity.Vehicle;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    List<Vehicle> findAllByModel(String model);

    List<Vehicle> findByVehicleFeatures_FeatureTypeAndVehicleFeatures_Feature(Feature featureType, String feature);

    List<Vehicle> findByVehiclePrice_FinalPriceBetween(Double minValue, Double maxValue);

}