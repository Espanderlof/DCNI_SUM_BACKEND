package com.duoc.alertas_medicas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "VITAL_SIGNS")
public class VitalSigns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    private Double heartRate;
    private Double bloodPressureSystolic;
    private Double bloodPressureDiastolic;
    private Double bodyTemperature;
    private Double oxygenSaturation;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
}