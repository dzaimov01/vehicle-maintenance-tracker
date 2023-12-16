package org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
public class MaintenanceLog {

    private UUID id;
    private UUID vehicleId;
    private String description;
}

