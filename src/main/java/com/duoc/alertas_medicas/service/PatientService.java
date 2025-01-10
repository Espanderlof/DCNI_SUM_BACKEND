package com.duoc.alertas_medicas.service;

import com.duoc.alertas_medicas.model.Patient;
import com.duoc.alertas_medicas.repository.PatientRepository;
import com.duoc.alertas_medicas.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }
    
    public Patient createPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setRut(patientDTO.getRut());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setMedicalHistory(patientDTO.getMedicalHistory());
        patient.setRoomNumber(patientDTO.getRoomNumber());
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        
        // Actualizar solo los campos que no sean null en el DTO
        if (patientDTO.getName() != null) {
            patient.setName(patientDTO.getName());
        }
        if (patientDTO.getRut() != null) {
            patient.setRut(patientDTO.getRut());
        }
        if (patientDTO.getDateOfBirth() != null) {
            patient.setDateOfBirth(patientDTO.getDateOfBirth());
        }
        if (patientDTO.getMedicalHistory() != null) {
            patient.setMedicalHistory(patientDTO.getMedicalHistory());
        }
        if (patientDTO.getRoomNumber() != null) {
            patient.setRoomNumber(patientDTO.getRoomNumber());
        }
        
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        patientRepository.deleteById(id);
    }
}