package com.duoc.alertas_medicas.repository;

import com.duoc.alertas_medicas.model.VitalSigns;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VitalSignsRepository extends JpaRepository<VitalSigns, Long> {
    List<VitalSigns> findByPatientIdOrderByTimestampDesc(Long patientId);
}