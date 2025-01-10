package com.duoc.alertas_medicas.repository;

import com.duoc.alertas_medicas.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByRut(String rut);
}