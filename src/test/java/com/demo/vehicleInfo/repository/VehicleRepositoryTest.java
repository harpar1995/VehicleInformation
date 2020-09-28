package com.demo.vehicleInfo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.vehicleInfo.Repository.VehicleRepository;
import com.demo.vehicleInfo.dto.Feature;
import com.demo.vehicleInfo.entity.Vehicle;
import com.demo.vehicleInfo.entity.VehicleFeature;
import com.demo.vehicleInfo.entity.VehiclePrice;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleRepositoryTest {
    @Autowired
    VehicleRepository vehicleRepository;

    @Test
    public void findAllByModel_should_return_matching_vehicles_from_db() {
        vehicleRepository.deleteAll();
        Vehicle vehicle = buildVehicleEntity();
        vehicleRepository.save(vehicle);

        List<Vehicle> vehicles = vehicleRepository.findAllByModel("Edge");
        assertEquals(1, vehicles.size());
        assertEquals("Edge", vehicles.get(0).getModel());
    }

    @Test
    public void findAllByVehicleFeatures_should_return_matching_vehicles_from_db(){
        vehicleRepository.deleteAll();
        Vehicle vehicle = buildVehicleEntity();
        vehicleRepository.save(vehicle);
        List<Vehicle> vehicles = vehicleRepository.findByVehicleFeatures_FeatureTypeAndVehicleFeatures_Feature(Feature.EXTERIOR, "exterior1");
        assertEquals(1, vehicles.size());
        assertTrue(vehicles.get(0).getVehicleFeatures().stream().filter(vehicleFeature -> Feature.EXTERIOR.equals(vehicleFeature.getFeatureType())).findFirst().isPresent());
        assertEquals("exterior1", vehicles.get(0).getVehicleFeatures().stream().filter(vehicleFeature -> Feature.EXTERIOR.equals(vehicleFeature.getFeatureType())).findFirst().get().getFeature());
    }

    @Test
    public void findByVehiclePrice_FinalPriceBetween_should_return_vechiles_btw_prices() {
        vehicleRepository.deleteAll();
        Vehicle vehicle=buildVehicleEntity();
        vehicleRepository.save(vehicle);
        List<Vehicle> vehicles=vehicleRepository.findByVehiclePrice_FinalPriceBetween(5000D, 15000D);
        assertEquals(1, vehicles.size());
    }

    public static Vehicle buildVehicleEntity() {
        return Vehicle.builder()
                .vehicleId("vehicleId")
                .model("Edge")
                .make("ford")
                .vehicleFeatures(new HashSet<>(Collections.singletonList(VehicleFeature.builder().feature("exterior1").featureType(Feature.EXTERIOR).build())))
                .vehiclePrice(new HashSet<>(Collections.singletonList(VehiclePrice.builder().finalPrice(10000D)
                        .mSRP(12000D)
                        .savings(2000D).build())))
                .build();
    }

}
