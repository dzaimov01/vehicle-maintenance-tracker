package org.vehiclemaintenanceapi.vehiclemaintenanceapi.mapping;

import org.mapstruct.Mapper;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto.Mechanic;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.MechanicEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MechanicMapper {
    Mechanic mechanicToDto(MechanicEntity mechanicEntity);

    List<Mechanic> mechanicsToDto(Iterable<MechanicEntity> maintenanceLogEntities);
}
