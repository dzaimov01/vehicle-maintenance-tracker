package org.vehiclemaintenanceapi.vehiclemaintenanceapi.mapping;

import org.mapstruct.Mapper;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto.MaintenanceLog;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.MaintenanceLogEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceLogMapper {
    MaintenanceLog maintenanceLogToDto(MaintenanceLogEntity maintenanceLogEntity);

    List<MaintenanceLog> maintenanceLogsToDto(Iterable<MaintenanceLogEntity> maintenanceLogEntities);

    List<MaintenanceLogEntity> maintenanceLogsToEntity(List<MaintenanceLog> maintenanceLogs);
}
