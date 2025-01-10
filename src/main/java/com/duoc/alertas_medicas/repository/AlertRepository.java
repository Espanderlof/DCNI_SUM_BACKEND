package com.duoc.alertas_medicas.repository;

import com.duoc.alertas_medicas.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByPatientIdAndIsActiveTrue(Long patientId);
    List<Alert> findByIsActiveTrue();
}