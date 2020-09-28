package com.demo.vehicleInfo.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.demo.vehicleInfo.Repository.VehicleRepository;
import com.demo.vehicleInfo.dto.Feature;
import com.demo.vehicleInfo.dto.SubmitVehicleRequestDTO;
import com.demo.vehicleInfo.dto.VehicleDTO;
import com.demo.vehicleInfo.dto.VehicleDetailsDTO;
import com.demo.vehicleInfo.dto.VehicleFeatureDTO;
import com.demo.vehicleInfo.dto.VehiclePriceDTO;
import com.demo.vehicleInfo.dto.VehiclesDTO;
import com.demo.vehicleInfo.dto.VehiclesResponseDTO;
import com.demo.vehicleInfo.entity.Vehicle;
import com.demo.vehicleInfo.entity.VehicleFeature;
import com.demo.vehicleInfo.entity.VehiclePrice;


@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    public VehicleService(VehicleRepository vehicleRepository, ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Vehicle> submitVehicle(SubmitVehicleRequestDTO submitVehicleRequestDTO) {
        List<Vehicle> vehicles = buildVehicles(submitVehicleRequestDTO.getVehicles());
        return vehicleRepository.saveAll(vehicles);
    }

    public VehiclesResponseDTO getVehicleInformation() {
        return VehiclesResponseDTO.builder()
                .vehicles(buildVehiclesDTO(vehicleRepository.findAll()))
                .build();
    }

    public VehiclesResponseDTO getVehicleModelName(String model) {
        return VehiclesResponseDTO.builder()
                .vehicles(buildVehiclesDTO(vehicleRepository.findAllByModel(model)))
                .build();
    }

    public VehiclesResponseDTO getVehiclePrice(Double fromValue, Double toValue) {
        return VehiclesResponseDTO.builder()
                .vehicles(buildVehiclesDTO(vehicleRepository.findByVehiclePrice_FinalPriceBetween(fromValue, toValue)))
                .build();
    }

    public VehiclesResponseDTO getVehicleByFeatures(String exterior, String interior) {
        List<Vehicle> exteriorMatchingVehicles = vehicleRepository.findByVehicleFeatures_FeatureTypeAndVehicleFeatures_Feature(Feature.EXTERIOR, exterior);
        List<Vehicle> interiorMatchingVehicles = vehicleRepository.findByVehicleFeatures_FeatureTypeAndVehicleFeatures_Feature(Feature.INTERIOR, interior);
        List<Vehicle> matchingVehicles = Stream.of(exteriorMatchingVehicles, interiorMatchingVehicles)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        Set<Vehicle> uniqueResults = new HashSet<>(matchingVehicles);

        return VehiclesResponseDTO.builder()
                .vehicles(buildVehiclesDTO(new ArrayList<>(uniqueResults)))
                .build();
    }

    private VehiclesDTO buildVehiclesDTO(List<Vehicle> vehicles) {
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        if (vehicles != null && vehicles.size() > 0) {
            vehicles.forEach(vehicle -> {
                VehicleDetailsDTO vehicleDetailsDTO = modelMapper.map(vehicle, VehicleDetailsDTO.class);
                vehicleDetailsDTO.setVehicleFeature(buildVehicleFeatureDTO(vehicle.getVehicleFeatures()));
                vehicleDetailsDTO.setVehiclePrice(buildVehiclePriceDTO(vehicle.getVehiclePrice()));
                vehicleDTOS.add(VehicleDTO.builder()
                        .vehicleId(vehicle.getVehicleId())
                        .vehicleDetails(vehicleDetailsDTO)
                        .build());
            });
        }
        return VehiclesDTO.builder().vehicle(vehicleDTOS).build();
    }

    private List<Vehicle> buildVehicles(VehiclesDTO vehiclesDTO) {
        List<Vehicle> vehicles = new ArrayList<>();
        if (vehiclesDTO != null && vehiclesDTO.getVehicle() != null && vehiclesDTO.getVehicle().size() > 0) {
            vehiclesDTO.getVehicle().forEach(vehicleDTO -> {
                if (vehicleDTO.getVehicleDetails() != null) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVehicleId(vehicleDTO.getVehicleId());
                    vehicle.setMake(vehicleDTO.getVehicleDetails().getMake());
                    vehicle.setModel(vehicleDTO.getVehicleDetails().getModel());
                    vehicle.setModelYear(vehicleDTO.getVehicleDetails().getModelYear());
                    vehicle.setBodyStyle(vehicleDTO.getVehicleDetails().getBodyStyle());
                    vehicle.setEngine(vehicleDTO.getVehicleDetails().getEngine());
                    vehicle.setDrivetype(vehicleDTO.getVehicleDetails().getDrivetype());
                    vehicle.setColor(vehicleDTO.getVehicleDetails().getColor());
                    vehicle.setMPG(vehicleDTO.getVehicleDetails().getMPG());
                    vehicle.setVehicleFeatures(buildVehicleFeatures(vehicleDTO.getVehicleDetails().getVehicleFeature()));
                    vehicle.setVehiclePrice(buildVehiclePrice(vehicleDTO.getVehicleDetails().getVehiclePrice()));
                    vehicles.add(vehicle);
                }
            });
        }
        return vehicles;
    }

    private Set<VehiclePrice> buildVehiclePrice(List<VehiclePriceDTO> vehiclePriceDTOS) {
        Set<VehiclePrice> vehiclePrices = new HashSet<>();
        if (vehiclePriceDTOS != null && vehiclePriceDTOS.size() > 0) {
            vehiclePriceDTOS.forEach(vehiclePriceDTO -> vehiclePrices.add(
                    VehiclePrice.builder()
                            .mSRP(convertVehiclePriceStringToDouble(vehiclePriceDTO.getMSRP()))
                            .finalPrice(convertVehiclePriceStringToDouble(vehiclePriceDTO.getFinalPrice()))
                            .savings(convertVehiclePriceStringToDouble(vehiclePriceDTO.getSavings()))
                            .build()
            ));
        }
        return vehiclePrices;
    }

    private Double convertVehiclePriceStringToDouble(String value) {
        Double finalValue;
        value = value.replace("$", "");
        value = value.replace(",", "");
        finalValue = Double.valueOf(value);
        return finalValue;
    }

    private List<VehiclePriceDTO> buildVehiclePriceDTO(Set<VehiclePrice> vehiclePriceS) {
        List<VehiclePriceDTO> vehiclePriceDTOS = new ArrayList<>();
        if (vehiclePriceS != null && vehiclePriceS.size() > 0) {
            vehiclePriceS.forEach(vehiclePrice -> vehiclePriceDTOS.add(
                    VehiclePriceDTO.builder()
                            .mSRP(vehiclePrice.getMSRP())
                            .finalPrice(vehiclePrice.getFinalPrice())
                            .savings(vehiclePrice.getSavings())
                            .build()
            ));
        }
        return vehiclePriceDTOS;
    }

    private Set<VehicleFeature> buildVehicleFeatures(VehicleFeatureDTO vehicleFeature) {
    	Set<VehicleFeature> vehicleFeatures = new HashSet<>();
        if (vehicleFeature != null && vehicleFeature.getExterior() != null && vehicleFeature.getExterior().size() > 0) {
            vehicleFeature.getExterior().forEach(feature -> vehicleFeatures.add(VehicleFeature.builder().featureType(Feature.EXTERIOR).feature(feature).build()));
        }
        if (vehicleFeature != null && vehicleFeature.getInterior() != null && vehicleFeature.getInterior().size() > 0) {
            vehicleFeature.getInterior().forEach(feature -> vehicleFeatures.add(VehicleFeature.builder().featureType(Feature.INTERIOR).feature(feature).build()));
        }
        return vehicleFeatures;
    }

    private VehicleFeatureDTO buildVehicleFeatureDTO(Set<VehicleFeature> vehicleFeatures) {
        VehicleFeatureDTO vehicleFeatureDTO = new VehicleFeatureDTO();
        List<String> exteriorArray = new ArrayList<>();
        List<String> interiorArray = new ArrayList<>();
        if (vehicleFeatures != null && vehicleFeatures.size() > 0) {
            for (VehicleFeature vehicleFeature : vehicleFeatures) {
                if (vehicleFeature.getFeatureType().equals(Feature.EXTERIOR)) {
                    exteriorArray.add(vehicleFeature.getFeature());
                }
            }
            vehicleFeatureDTO.setExterior(exteriorArray);
            for (VehicleFeature vehicleFeature : vehicleFeatures) {
                if (vehicleFeature.getFeatureType().equals(Feature.INTERIOR)) {
                    interiorArray.add(vehicleFeature.getFeature());
                }
            }
            vehicleFeatureDTO.setInterior(interiorArray);
        }
        return vehicleFeatureDTO;
    }


}

