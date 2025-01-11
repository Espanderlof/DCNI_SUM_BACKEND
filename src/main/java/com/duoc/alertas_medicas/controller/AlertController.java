package com.duoc.alertas_medicas.controller;

import com.duoc.alertas_medicas.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {
    @Autowired
    private AlertService alertService;
    
    @GetMapping("/active")
    public ResponseEntity<?> getActiveAlerts() {
        return ResponseEntity.ok(alertService.getActiveAlerts());
    }
    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getPatientAlerts(@PathVariable Long patientId) {
        return ResponseEntity.ok(alertService.getPatientAlerts(patientId));
    }
    
    @PutMapping("/{alertId}/resolve")
    public ResponseEntity<?> resolveAlert(@PathVariable Long alertId) {
        return ResponseEntity.ok(alertService.resolveAlert(alertId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }
}