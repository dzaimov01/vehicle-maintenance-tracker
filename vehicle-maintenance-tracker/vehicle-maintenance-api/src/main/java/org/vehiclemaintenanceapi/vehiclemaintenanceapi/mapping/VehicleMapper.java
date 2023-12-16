package org.vehiclemaintenanceapi.vehiclemaintenanceapi.mapping;

import org.mapstruct.Mapper;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto.Vehicle;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.VehicleEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    Vehicle vehicleToDto(VehicleEntity vehicleEntity);

    List<Vehicle> vehiclesToDto(Iterable<VehicleEntity> vehiclesEntities);
}
