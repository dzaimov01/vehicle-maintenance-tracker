package org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Mechanic {

    private UUID id;
    private String name;
    private String specialization;
    private List<MaintenanceLog> maintenanceLogList;
}

