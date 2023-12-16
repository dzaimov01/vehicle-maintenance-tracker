package org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Vehicle {

    private UUID id;
    private String brand;
    private String model;
    private String registrationNumber;
    private int year;
}

