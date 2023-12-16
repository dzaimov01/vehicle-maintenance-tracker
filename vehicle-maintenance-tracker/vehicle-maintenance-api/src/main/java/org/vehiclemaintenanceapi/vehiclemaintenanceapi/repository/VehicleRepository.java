package org.vehiclemaintenanceapi.vehiclemaintenanceapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.VehicleEntity;

import java.util.UUID;

@Repository
public interface VehicleRepository extends CrudRepository<VehicleEntity, UUID> {
}

