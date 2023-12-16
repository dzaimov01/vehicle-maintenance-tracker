package org.vehiclemaintenanceapi.vehiclemaintenanceapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto.Mechanic;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity.MechanicEntity;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.mapping.MaintenanceLogMapper;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.mapping.MechanicMapper;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.repository.MechanicRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MechanicService {

    private final MechanicRepository mechanicRepository;
    private final MechanicMapper mechanicMapper;
    private final MaintenanceLogMapper maintenanceLogMapper;

    @Autowired
    public MechanicService(MechanicRepository mechanicRepository, MechanicMapper mechanicMapper, MaintenanceLogMapper maintenanceLogMapper) {
        this.mechanicRepository = mechanicRepository;
        this.mechanicMapper = mechanicMapper;
        this.maintenanceLogMapper = maintenanceLogMapper;
    }

    public List<Mechanic> getAllMechanics() {
        return mechanicMapper.mechanicsToDto(mechanicRepository.findAll());
    }

    public Optional<Mechanic> getMechanicById(UUID id) {
        return Optional.of(mechanicMapper.mechanicToDto(mechanicRepository.findById(id).orElse(null)));
    }

    public Mechanic saveMechanic(Mechanic mechanic) {
        MechanicEntity mechanicEntity = MechanicEntity.builder()
                .id(mechanic.getId())
                .name(mechanic.getName())
                .specialization(mechanic.getSpecialization()).build();
        return mechanicMapper.mechanicToDto(mechanicRepository.save(mechanicEntity));
    }

    public Mechanic updateMechanic(UUID id, Mechanic mechanic) {
        Optional<MechanicEntity> existingMechanicOptional = mechanicRepository.findById(id);

        if (existingMechanicOptional.isPresent()) {
            MechanicEntity existingMechanicEntity = existingMechanicOptional.get();
            existingMechanicEntity.setName(mechanic.getName());
            existingMechanicEntity.setSpecialization(mechanic.getSpecialization());
            existingMechanicEntity.setMaintenanceLogs(maintenanceLogMapper.maintenanceLogsToEntity(mechanic.getMaintenanceLogList()));

            return mechanicMapper.mechanicToDto(mechanicRepository.save(existingMechanicEntity));
        } else {
            throw new IllegalArgumentException("Mechanic with ID " + mechanic.getId() + " not found");
        }
    }

    public void deleteMechanic(UUID id) {
        mechanicRepository.deleteById(id);
    }

}

