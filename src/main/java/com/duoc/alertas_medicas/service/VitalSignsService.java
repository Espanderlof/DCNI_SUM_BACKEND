package com.duoc.alertas_medicas.service;

import com.duoc.alertas_medicas.model.VitalSigns;
import com.duoc.alertas_medicas.model.Patient;
import com.duoc.alertas_medicas.repository.VitalSignsRepository;
import com.duoc.alertas_medicas.repository.PatientRepository;
import com.duoc.alertas_medicas.dto.VitalSignsDTO;
import com.duoc.alertas_medicas.util.VitalSignsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VitalSignsService {
    @Autowired
    private VitalSignsRepository vitalSignsRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private AlertService alertService;
    
    @Autowired
    private VitalSignsValidator validator;

    public VitalSigns recordVitalSigns(VitalSignsDTO vitalSignsDTO) {
        VitalSigns vitalSigns = new VitalSigns();
        
        // Obtener el paciente
        Patient patient = patientRepository.findById(vitalSignsDTO.getPatientId())
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        
        // Mapeo de DTO a entidad
        vitalSigns.setPatient(patient);
        vitalSigns.setHeartRate(vitalSignsDTO.getHeartRate());
        vitalSigns.setBloodPressureSystolic(vitalSignsDTO.getBloodPressureSystolic());
        vitalSigns.setBloodPressureDiastolic(vitalSignsDTO.getBloodPressureDiastolic());
        vitalSigns.setBodyTemperature(vitalSignsDTO.getBodyTemperature());
        vitalSigns.setOxygenSaturation(vitalSignsDTO.getOxygenSaturation());
        vitalSigns.setTimestamp(LocalDateTime.now());
        
        // Verificar signos vitales y generar alertas si es necesario
        checkVitalSigns(vitalSigns);
        
        return vitalSignsRepository.save(vitalSigns);
    }
    
    public List<VitalSigns> getVitalSignsByPatient(Long patientId) {
        return vitalSignsRepository.findByPatientIdOrderByTimestampDesc(patientId);
    }
    
    private void checkVitalSigns(VitalSigns signs) {
        if (!validator.isHeartRateNormal(signs.getHeartRate())) {
            String severity = validator.getAlertSeverity(signs.getHeartRate(), 60, 100);
            alertService.createAlert(
                signs.getPatient().getId(),
                "ABNORMAL_HEART_RATE",
                "Frecuencia cardíaca fuera de rango normal: " + signs.getHeartRate(),
                severity
            );
        }

        if (!validator.isBloodPressureNormal(
                signs.getBloodPressureSystolic(),
                signs.getBloodPressureDiastolic())) {
            alertService.createAlert(
                signs.getPatient().getId(),
                "ABNORMAL_BLOOD_PRESSURE",
                String.format("Presión arterial anormal: %f/%f",
                    signs.getBloodPressureSystolic(),
                    signs.getBloodPressureDiastolic()),
                "HIGH"
            );
        }

        if (!validator.isTemperatureNormal(signs.getBodyTemperature())) {
            String severity = validator.getAlertSeverity(signs.getBodyTemperature(), 36.5, 37.5);
            alertService.createAlert(
                signs.getPatient().getId(),
                "ABNORMAL_TEMPERATURE",
                "Temperatura corporal fuera de rango: " + signs.getBodyTemperature(),
                severity
            );
        }

        if (!validator.isOxygenSaturationNormal(signs.getOxygenSaturation())) {
            alertService.createAlert(
                signs.getPatient().getId(),
                "LOW_OXYGEN_SATURATION",
                "Saturación de oxígeno baja: " + signs.getOxygenSaturation(),
                "HIGH"
            );
        }
    }
    
    public void deleteVitalSigns(Long id) {
        vitalSignsRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Registro de signos vitales no encontrado"));
        vitalSignsRepository.deleteById(id);
    }
}