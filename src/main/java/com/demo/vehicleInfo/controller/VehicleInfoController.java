package com.demo.vehicleInfo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.vehicleInfo.dto.Feature;
import com.demo.vehicleInfo.dto.ResponseNotMatchedFeaturesDTO;
import com.demo.vehicleInfo.dto.SubmitVehicleRequestDTO;
import com.demo.vehicleInfo.dto.SubmitVehicleResponseDTO;
import com.demo.vehicleInfo.dto.VehiclesResponseDTO;
import com.demo.vehicleInfo.entity.Vehicle;
import com.demo.vehicleInfo.service.VehicleService;

@RestController
public class VehicleInfoController {
    private final VehicleService vehicleService;

    public VehicleInfoController(VehicleService vehicleService) {
        this.vehicleService=vehicleService;
    }

    @PostMapping("/vehicleInformation/submitVehicle")
    public SubmitVehicleResponseDTO submitVehicle(@RequestBody SubmitVehicleRequestDTO submitVehicleRequestDTO) {
        List<Vehicle> vehicleSet=vehicleService.submitVehicle(submitVehicleRequestDTO);


        return SubmitVehicleResponseDTO.builder()
                .message(vehicleSet.stream().map(Vehicle::getVehicleId).collect(Collectors.joining(",")) + " submitted to database successfully")
                .status(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/getVehicleInformation")
    public VehiclesResponseDTO getVehicleInformation() {
        return vehicleService.getVehicleInformation();
    }

    @GetMapping("/getVehicleModelName/{modelName}")
    public VehiclesResponseDTO getVehicleModelName(@PathVariable("modelName") String model) {
        return vehicleService.getVehicleModelName(model);
    }

    @GetMapping("/getVehiclePrice/{From}/{TO}")
    public VehiclesResponseDTO getVehiclePrice(@PathVariable("From") Double fromValue,
    		@PathVariable("TO") Double toValue) {
        return vehicleService.getVehiclePrice(fromValue, toValue);
    }

    @GetMapping("/getVehicleByFeatures/{exterior}/{interior}")
    public Object getVehicleByFeatures(@PathVariable("exterior") String exterior, 
    		@PathVariable("interior") String interior) {
        if (exterior.length() >= 3 && interior.length() >= 3) {
            VehiclesResponseDTO vehicles=new VehiclesResponseDTO();
            vehicles=vehicleService.getVehicleByFeatures(exterior, interior);
            if (vehicles == null) {
            	ResponseNotMatchedFeaturesDTO response=new ResponseNotMatchedFeaturesDTO();
                response.setStatus("fail");
                response.setMessage("Error message");
                return response;
            } else
                return vehicles;
        } else {
        	ResponseNotMatchedFeaturesDTO response=new ResponseNotMatchedFeaturesDTO();
            response.setStatus("fail");
            response.setMessage("Error message");
            return response;
        }

    }

}
