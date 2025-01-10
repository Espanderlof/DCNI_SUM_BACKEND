package com.duoc.alertas_medicas.controller;

import com.duoc.alertas_medicas.dto.VitalSignsDTO;
import com.duoc.alertas_medicas.service.VitalSignsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vital-signs")
@CrossOrigin(origins = "*")
public class VitalSignsController {
    @Autowired
    private VitalSignsService vitalSignsService;
    
    @PostMapping
    public ResponseEntity<?> recordVitalSigns(@RequestBody VitalSignsDTO vitalSignsDTO) {
        return ResponseEntity.ok(vitalSignsService.recordVitalSigns(vitalSignsDTO));
    }
    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getPatientVitalSigns(@PathVariable Long patientId) {
        return ResponseEntity.ok(vitalSignsService.getVitalSignsByPatient(patientId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVitalSigns(@PathVariable Long id) {
        vitalSignsService.deleteVitalSigns(id);
        return ResponseEntity.ok().build();
    }
}