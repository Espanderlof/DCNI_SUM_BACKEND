package com.duoc.alertas_medicas.service;

import com.duoc.alertas_medicas.model.Alert;
import com.duoc.alertas_medicas.repository.AlertRepository;
import com.duoc.alertas_medicas.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertService {
    @Autowired
    private AlertRepository alertRepository;
    
    @Autowired
    private PatientRepository patientRepository;

    public Alert createAlert(Long patientId, String type, String description, String severity) {
        Alert alert = new Alert();
        alert.setPatient(patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado")));
        alert.setAlertType(type);
        alert.setDescription(description);
        alert.setSeverity(severity);
        alert.setTimestamp(LocalDateTime.now());
        alert.setIsActive(true);
        return alertRepository.save(alert);
    }
    
    public List<Alert> getActiveAlerts() {
        return alertRepository.findByIsActiveTrue();
    }
    
    public List<Alert> getPatientAlerts(Long patientId) {
        return alertRepository.findByPatientIdAndIsActiveTrue(patientId);
    }
    
    public Alert resolveAlert(Long alertId) {
        Alert alert = alertRepository.findById(alertId)
            .orElseThrow(() -> new RuntimeException("Alerta no encontrada"));
        alert.setIsActive(false);
        return alertRepository.save(alert);
    }
    
    public void deleteAlert(Long id) {
        alertRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alerta no encontrada"));
        alertRepository.deleteById(id);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
}