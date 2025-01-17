package com.constuction.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "location_details")
public class LocationDetails extends BaseEntity{
    private Long id;
    private String State;
    private String city;
    private String pincode;
    private String address;
}
