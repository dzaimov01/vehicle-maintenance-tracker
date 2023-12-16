package org.vehiclemaintenanceapi.vehiclemaintenanceapi.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.MaintenanceLogEntity;

import java.util.UUID;

@Repository
public interface MaintenanceLogRepository extends CrudRepository<MaintenanceLogEntity, UUID> {
}

