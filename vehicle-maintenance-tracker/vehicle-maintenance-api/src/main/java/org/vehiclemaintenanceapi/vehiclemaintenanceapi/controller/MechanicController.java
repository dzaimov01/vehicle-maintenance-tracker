package org.vehiclemaintenanceapi.vehiclemaintenanceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto.Mechanic;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.service.MechanicService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/mechanics")
@CrossOrigin(origins = "http://localhost:3000")
public class MechanicController {

    private final MechanicService mechanicService;

    @Autowired
    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @GetMapping
    public List<Mechanic> getAllMechanics() {
        return mechanicService.getAllMechanics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mechanic> getMechanicById(@PathVariable UUID id) {
        return mechanicService.getMechanicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mechanic> saveMechanic(@RequestBody Mechanic mechanicDTO) {
        Mechanic savedMechanic = mechanicService.saveMechanic(mechanicDTO);
        return ResponseEntity.ok(savedMechanic);
    }

    @PutMapping("{id}")
    public ResponseEntity<Mechanic> updateMechanic(@PathVariable UUID id, @RequestBody Mechanic mechanic) {
        Mechanic updatedMechanic = mechanicService.updateMechanic(id, mechanic);
        return ResponseEntity.ok(updatedMechanic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMechanic(@PathVariable UUID id) {
        mechanicService.deleteMechanic(id);
        return ResponseEntity.noContent().build();
    }
}

