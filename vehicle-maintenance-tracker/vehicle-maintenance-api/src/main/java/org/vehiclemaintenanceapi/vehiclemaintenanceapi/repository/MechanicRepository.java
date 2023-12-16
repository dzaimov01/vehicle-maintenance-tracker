package org.vehiclemaintenanceapi.vehiclemaintenanceapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.MechanicEntity;

import java.util.UUID;

@Repository
public interface MechanicRepository extends CrudRepository<MechanicEntity, UUID> {
}

