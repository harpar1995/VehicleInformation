package com.demo.vehicleInfo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehiclePrice {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long priceId;
    private Double mSRP;
    private Double savings;
    private Double finalPrice;

    public String getMSRP() {
        return "$" + mSRP;
    }

    public String getFinalPrice() {
        return "$" + finalPrice;
    }

    public String getSavings() {
        return "$" + savings;
    }
}
