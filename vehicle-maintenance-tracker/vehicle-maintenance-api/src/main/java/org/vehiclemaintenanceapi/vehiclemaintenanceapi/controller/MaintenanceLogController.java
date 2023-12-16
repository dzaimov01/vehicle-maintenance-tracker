package org.vehiclemaintenanceapi.vehiclemaintenanceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.dto.MaintenanceLog;
import org.vehiclemaintenanceapi.vehiclemaintenanceapi.service.MaintenanceLogService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/maintenance-logs")
@CrossOrigin(origins = "http://localhost:3000")
public class MaintenanceLogController {

    private final MaintenanceLogService maintenanceLogService;

    @Autowired
    public MaintenanceLogController(MaintenanceLogService maintenanceLogService) {
        this.maintenanceLogService = maintenanceLogService;
    }

    @GetMapping
    public List<MaintenanceLog> getAllMaintenanceLogs() {
        return maintenanceLogService.getAllMaintenanceLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceLog> getMaintenanceLogById(@PathVariable UUID id) {
        return maintenanceLogService.getMaintenanceLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<MaintenanceLog> updateMaintenanceLog(@PathVariable UUID id, MaintenanceLog maintenanceLog){
        System.out.println("Controller: " + maintenanceLog.toString());
        return ResponseEntity.ok(maintenanceLogService.updateMechanic(id, maintenanceLog));
    }

    @PostMapping
    public ResponseEntity<MaintenanceLog> saveMaintenanceLog(@RequestBody MaintenanceLog maintenanceLog) {
        MaintenanceLog savedMaintenanceLog = maintenanceLogService.saveMaintenanceLog(maintenanceLog);
        return ResponseEntity.ok(savedMaintenanceLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenanceLog(@PathVariable UUID id) {
        maintenanceLogService.deleteMaintenanceLog(id);
        return ResponseEntity.noContent().build();
    }
}

