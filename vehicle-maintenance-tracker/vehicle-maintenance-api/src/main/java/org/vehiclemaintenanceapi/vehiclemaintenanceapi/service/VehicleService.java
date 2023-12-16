package org.vehiclemaintenanceapi.vehiclemaintenanceapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto.Mechanic;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto.Vehicle;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.MechanicEntity;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.VehicleEntity;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.mapping.VehicleMapper;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleMapper.vehiclesToDto(vehicleRepository.findAll());
    }

    public Optional<Vehicle> getVehicleById(UUID id) {
        return Optional.of(vehicleMapper.vehicleToDto(vehicleRepository.findById(id).orElse(null)));
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleMapper.vehicleToDto(vehicleRepository.save(
                VehicleEntity.builder()
                        .brand(vehicle.getBrand())
                        .model(vehicle.getModel())
                        .registrationNumber(vehicle.getRegistrationNumber())
                        .year(vehicle.getYear()).build()
        ));
    }

    public Vehicle updateVehicle(UUID id, Vehicle vehicle) {
        Optional<VehicleEntity> existingOptional = vehicleRepository.findById(id);

        if (existingOptional.isPresent()) {
            VehicleEntity existingEntity = existingOptional.get();
            existingEntity.setBrand(vehicle.getBrand());
            existingEntity.setModel(vehicle.getModel());
            existingEntity.setRegistrationNumber(vehicle.getRegistrationNumber());
            existingEntity.setYear(vehicle.getYear());

            return vehicleMapper.vehicleToDto(vehicleRepository.save(existingEntity));
        } else {
            throw new IllegalArgumentException("Mechanic with ID " + vehicle.getId() + " not found");
        }
    }

    public void deleteVehicle(UUID id) {
        vehicleRepository.deleteById(id);
    }
}
