package com.demo.vehicleInfo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.vehicleInfo.Repository.VehicleRepository;
import com.demo.vehicleInfo.dto.Feature;
import com.demo.vehicleInfo.entity.Vehicle;
import com.demo.vehicleInfo.entity.VehicleFeature;
import com.demo.vehicleInfo.entity.VehiclePrice;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerTest {
	
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void submitVehicle_should_save_vehicles_info_and_return_200() throws Exception {

        mockMvc.perform(post("/vehicleInformation/submitVehicle")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                		+ "  \"vehicles\": {\n"
                		+ "    \"vehicle\": [\n"
                		+ "      {\n"
                		+ "        \"vehicleId\": \"101\",\n"
                		+ "        \"vehicleDetails\": {\n"
                		+ "          \"make\": \"Ford\",\n"
                		+ "          \"model\": \"ecoSport\",\n"
                		+ "          \"modelYear\": \"2020\",\n"
                		+ "          \"bodyStyle\": \"4D Sport Utility\",\n"
                		+ "          \"engine\": \"1.0L EcoBoost\",\n"
                		+ "          \"drivetype\": \"FWD\",\n"
                		+ "          \"color\": \"shadow black\",\n"
                		+ "          \"MPG\": \"27\",\n"
                		+ "          \"vehicleFeature\": {\n"
                		+ "            \"Exterior\": [\n"
                		+ "              \"Acoustic-Laminate Windshld\",\n"
                		+ "              \"Active Grille Shutters\",\n"
                		+ "              \"Windshield Wiper De-Icer\",\n"
                		+ "              \"Privacy Glass - Rear Doors\"\n"
                		+ "            ],\n"
                		+ "            \"Interior\": [\n"
                		+ "              \"Illuminated Entry System\",\n"
                		+ "              \"Powerpoints - 12V\",\n"
                		+ "              \"Power Driver Seat - 6-Way\",\n"
                		+ "              \"Unique Clth/Htd Frt Seats\"\n"
                		+ "            ]\n"
                		+ "          },\n"
                		+ "          \"vehiclePrice\": [\n"
                		+ "            {\n"
                		+ "              \"MSRP\": \"$25,000.00\",\n"
                		+ "              \"Savings\": \"$5000\",\n"
                		+ "              \"finalPrice\": \"$20,000.00\"\n"
                		+ "            }\n"
                		+ "          ]\n"
                		+ "        }\n"
                		+ "      },\n"
                		+ "      {\n"
                		+ "        \"vehicleId\": \"102\",\n"
                		+ "        \"vehicleDetails\": {\n"
                		+ "          \"make\": \"Ford\",\n"
                		+ "          \"model\": \"Edge\",\n"
                		+ "          \"modelYear\": \"2019\",\n"
                		+ "          \"bodyStyle\": \"4D Sport Utility\",\n"
                		+ "          \"engine\": \"Twin-Scroll 2.0L EcoBoost\",\n"
                		+ "          \"drivetype\": \"AWD\",\n"
                		+ "          \"color\": \"Agate Black\",\n"
                		+ "          \"MPG\": \"28\",\n"
                		+ "          \"vehicleFeature\": {\n"
                		+ "            \"Exterior\": [\n"
                		+ "              \"Beltline Molding - Black\",\n"
                		+ "              \"Door Handles - Body Color\",\n"
                		+ "              \"Grille - Chrome\",\n"
                		+ "              \"Taillamps-Led\"\n"
                		+ "            ],\n"
                		+ "            \"Interior\": [\n"
                		+ "              \"60/40 Split Fold Rear Seat\",\n"
                		+ "              \"Cruise Control\",\n"
                		+ "              \"Dual Illum Vis Vanity Mirr\",\n"
                		+ "              \"Rotary Gear Shift Dial\"\n"
                		+ "            ]\n"
                		+ "          },\n"
                		+ "          \"vehiclePrice\": [\n"
                		+ "            {\n"
                		+ "              \"MSRP\": \"$30,000.00\",\n"
                		+ "              \"Savings\": \"$2000\",\n"
                		+ "              \"finalPrice\": \"$28,000.00\"\n"
                		+ "            }\n"
                		+ "          ]\n"
                		+ "        }\n"
                		+ "      },\n"
                		+ "      {\n"
                		+ "        \"vehicleId\": \"103\",\n"
                		+ "        \"vehicleDetails\": {\n"
                		+ "          \"make\": \"Ford\",\n"
                		+ "          \"model\": \"F-150\",\n"
                		+ "          \"modelYear\": \"2020\",\n"
                		+ "          \"bodyStyle\": \"4D SuperCrew\",\n"
                		+ "          \"engine\": \"V6 PFDI\",\n"
                		+ "          \"drivetype\": \"4WD\",\n"
                		+ "          \"color\": \"Blue Jeans Metallic\",\n"
                		+ "          \"MPG\": \"23\",\n"
                		+ "          \"vehicleFeature\": {\n"
                		+ "            \"Exterior\": [\n"
                		+ "              \"Headlamps - Autolamp\",\n"
                		+ "              \"Locking Removable Tailgate\",\n"
                		+ "              \"Manual Fold Power Mirrors\",\n"
                		+ "              \"Headlamps - Auto High Beam\"\n"
                		+ "            ],\n"
                		+ "            \"Interior\": [\n"
                		+ "              \"1Touch Up/Down Dr/Pass Win\",\n"
                		+ "              \"60/40 Fold-Up Rear Bench Seat\",\n"
                		+ "              \"Cruise Control\",\n"
                		+ "              \"Illuminated Entry\"\n"
                		+ "            ]\n"
                		+ "          },\n"
                		+ "          \"vehiclePrice\": [\n"
                		+ "            {\n"
                		+ "              \"MSRP\": \"$40,925.00\",\n"
                		+ "              \"Savings\": \"$4678.00\",\n"
                		+ "              \"finalPrice\": \"$36,247.00\"\n"
                		+ "            }\n"
                		+ "          ]\n"
                		+ "        }\n"
                		+ "      },\n"
                		+ "      {\n"
                		+ "        \"vehicleId\": \"104\",\n"
                		+ "        \"vehicleDetails\": {\n"
                		+ "          \"make\": \"Ford\",\n"
                		+ "          \"model\": \"mustang\",\n"
                		+ "          \"modelYear\": \"2017\",\n"
                		+ "          \"bodyStyle\": \"4D Sport Utility\",\n"
                		+ "          \"engine\": \"V8\",\n"
                		+ "          \"drivetype\": \"RWD\",\n"
                		+ "          \"color\": \"Blue Metallic\",\n"
                		+ "          \"MPG\": \"32\",\n"
                		+ "          \"vehicleFeature\": {\n"
                		+ "            \"Exterior\": [\n"
                		+ "              \"Dual Exhaust System\",\n"
                		+ "              \"Easy Fuel Capless Filler\",\n"
                		+ "              \"Headlamps - Autolamp\",\n"
                		+ "              \"Headlamps- Led With Signature Lighting\"\n"
                		+ "            ],\n"
                		+ "            \"Interior\": [\n"
                		+ "              \"Autodim Rearview Mirror\",\n"
                		+ "              \"Center Console W/Armrest\",\n"
                		+ "              \"Floor Mats - Front\",\n"
                		+ "              \"Smart Charging Usb Port(2)\"\n"
                		+ "            ]\n"
                		+ "          },\n"
                		+ "          \"vehiclePrice\": [\n"
                		+ "            {\n"
                		+ "              \"MSRP\": \"$33,645.70\",\n"
                		+ "              \"Savings\": \"$4,988.20\",\n"
                		+ "              \"finalPrice\": \"$28657.50\"\n"
                		+ "            }\n"
                		+ "          ]\n"
                		+ "        }\n"
                		+ "      },\n"
                		+ "      {\n"
                		+ "        \"vehicleId\": \"105\",\n"
                		+ "        \"vehicleDetails\": {\n"
                		+ "          \"make\": \"Ford\",\n"
                		+ "          \"model\": \"Ranger\",\n"
                		+ "          \"modelYear\": \"2016\",\n"
                		+ "          \"bodyStyle\": \"Super Cab\",\n"
                		+ "          \"engine\": \"2.3L EcoBoost\",\n"
                		+ "          \"drivetype\": \"4WD\",\n"
                		+ "          \"color\": \"Oxford White\",\n"
                		+ "          \"MPG\": \"24\",\n"
                		+ "          \"vehicleFeature\": {\n"
                		+ "            \"Exterior\": [\n"
                		+ "              \"Daytime Running Lights\",\n"
                		+ "              \"Easy Fuel Capless Filler\",\n"
                		+ "              \"Fuel Tank - 18.0 Gallon\",\n"
                		+ "              \"Tow Hooks\"\n"
                		+ "            ],\n"
                		+ "            \"Interior\": [\n"
                		+ "              \"110V Outlet\",\n"
                		+ "              \"Dual Sliding Sunvisors\",\n"
                		+ "              \"Dual Zone Auto Climate Ctl\",\n"
                		+ "              \"Overhead Console\"\n"
                		+ "            ]\n"
                		+ "          },\n"
                		+ "          \"vehiclePrice\": [\n"
                		+ "            {\n"
                		+ "              \"MSRP\": \"$35,515.00\",\n"
                		+ "              \"Savings\": \"$5000\",\n"
                		+ "              \"finalPrice\": \"$30,515.00\"\n"
                		+ "            }\n"
                		+ "          ]\n"
                		+ "        }\n"
                		+ "      }\n"
                		+ "    ]\n"
                		+ "  }\n"
                		+ "}}\n")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("101,102,103,104,105 submitted to database successfully"));

        assertTrue(vehicleRepository.findById("101").isPresent());
        assertTrue(vehicleRepository.findById("102").isPresent());
        assertTrue(vehicleRepository.findById("103").isPresent());
        assertTrue(vehicleRepository.findById("104").isPresent());
        assertTrue(vehicleRepository.findById("105").isPresent());

        Vehicle vehicle101 = vehicleRepository.findById("101").get();
        assertEquals(8, vehicle101.getVehicleFeatures().size());
        
        Vehicle vehicle102 = vehicleRepository.findById("102").get();
        assertEquals(8, vehicle102.getVehicleFeatures().size());
        
        Vehicle vehicle103 = vehicleRepository.findById("103").get();
        assertEquals(8, vehicle103.getVehicleFeatures().size());
        
        Vehicle vehicle104 = vehicleRepository.findById("104").get();
        assertEquals(8, vehicle104.getVehicleFeatures().size());
        
        Vehicle vehicle105 = vehicleRepository.findById("105").get();
        assertEquals(8, vehicle105.getVehicleFeatures().size());
    }

    @Test
    public void getVehicles_should_return_vehicles_saved_in_db_as_response_dto() throws Exception {
        vehicleRepository.save(buildVehicleEntity());
        mockMvc.perform(get("/getVehicleInformation")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleId").value("vehicleId"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.model").value("Edge"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehicleFeature.Exterior[0]").value("exterior1"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].finalPrice").value("$10000.0"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].MSRP").value("$12000.0"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].Savings").value("$2000.0"));

    }

    @Test
    public void getVehiclesByModel_should_return_vehicles_had_givenModel_in_db_as_response_dto() throws Exception {
        vehicleRepository.save(buildVehicleEntity());
        mockMvc.perform(get("/getVehiclePrice/10000/15000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleId").value("vehicleId"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.model").value("Edge"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehicleFeature.Exterior[0]").value("exterior1"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].finalPrice").value("$10000.0"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].MSRP").value("$12000.0"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].Savings").value("$2000.0"));
    }


    @Test
    public void getVehiclesByPriceRange_should_return_vehicles_in_btw_price_range_from_db_as_response_dto() throws Exception {
        vehicleRepository.save(buildVehicleEntity());
        mockMvc.perform(get("/getVehiclePrice/10000/15000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleId").value("vehicleId"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.model").value("Edge"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehicleFeature.Exterior[0]").value("exterior1"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].finalPrice").value("$10000.0"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].MSRP").value("$12000.0"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].Savings").value("$2000.0"));

    }

    @Test
    public void getVehiclesByMatchingExteriorInterior_should_return_vehicles_which_contains_given_exterior_interior() throws Exception {
        vehicleRepository.save(buildVehicleEntity());
        mockMvc.perform(get("/getVehicleByFeatures/exterior1/interior1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleId").value("vehicleId"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.model").value("Edge"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehicleFeature.Exterior[0]").value("exterior1"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].finalPrice").value("$10000.0"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].MSRP").value("$12000.0"))
                .andExpect(jsonPath("$.vehicles.vehicle[0].vehicleDetails.vehiclePrice[0].Savings").value("$2000.0"));

    }

    @Test
    public void getVehiclesByMatchingExteriorInterior_should_return_vehicles_which_contains_given_exterior_interior_failure() throws Exception {
        vehicleRepository.save(buildVehicleEntity());
        mockMvc.perform(get("/getVehicleByFeatures/ex/in")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("fail"))
                .andExpect(jsonPath("$.message").value("Error message"));
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

