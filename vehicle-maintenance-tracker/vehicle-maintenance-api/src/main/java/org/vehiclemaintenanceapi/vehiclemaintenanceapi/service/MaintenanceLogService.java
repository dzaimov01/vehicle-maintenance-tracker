package org.vehiclemaintenanceapi.vehiclemaintenanceapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto.MaintenanceLog;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto.Mechanic;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.MaintenanceLogEntity;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.MechanicEntity;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.VehicleEntity;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.mapping.MaintenanceLogMapper;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.repository.MaintenanceLogRepository;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaintenanceLogService {

    private final MaintenanceLogRepository maintenanceLogRepository;
    private final VehicleRepository vehicleRepository;
    private final MaintenanceLogMapper maintenanceLogMapper;

    @Autowired
    public MaintenanceLogService(MaintenanceLogRepository maintenanceLogRepository, VehicleRepository vehicleRepository, MaintenanceLogMapper maintenanceLogMapper) {
        this.maintenanceLogRepository = maintenanceLogRepository;
        this.vehicleRepository = vehicleRepository;
        this.maintenanceLogMapper = maintenanceLogMapper;
    }

    public List<MaintenanceLog> getAllMaintenanceLogs() {
        return maintenanceLogMapper.maintenanceLogsToDto(maintenanceLogRepository.findAll());
    }

    public Optional<MaintenanceLog> getMaintenanceLogById(UUID id) {
        return Optional.of(maintenanceLogMapper.maintenanceLogToDto(maintenanceLogRepository.findById(id).orElse(null)));
    }

    public MaintenanceLog saveMaintenanceLog(MaintenanceLog maintenanceLog) {
        Optional<VehicleEntity> existingVehicle = vehicleRepository.findById(maintenanceLog.getVehicleId());
        MaintenanceLogEntity entity = null;
        if (existingVehicle.isPresent()) {
            entity = MaintenanceLogEntity.builder()
                    .vehicle(existingVehicle.get())
                    .description(maintenanceLog.getDescription()).build();
        }

        return maintenanceLogMapper.maintenanceLogToDto(maintenanceLogRepository.save(entity));
    }

    public MaintenanceLog updateMechanic(UUID id, MaintenanceLog maintenanceLog) {
        Optional<MaintenanceLogEntity> existingOptional = maintenanceLogRepository.findById(id);

        if (existingOptional.isPresent()) {
            MaintenanceLogEntity existingEntity = existingOptional.get();
            System.out.println("UUID: " + maintenanceLog.getVehicleId());
            existingEntity.setVehicle(vehicleRepository.findById(maintenanceLog.getVehicleId()).get());
            existingEntity.setDescription(maintenanceLog.getDescription());

            return maintenanceLogMapper.maintenanceLogToDto(maintenanceLogRepository.save(existingEntity));
        } else {
            throw new IllegalArgumentException("Mechanic with ID " + maintenanceLog.getId() + " not found");
        }
    }

    public void deleteMaintenanceLog(UUID id) {
        maintenanceLogRepository.deleteById(id);
    }
}

