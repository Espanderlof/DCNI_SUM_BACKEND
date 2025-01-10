package com.duoc.alertas_medicas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ALERTS")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @Column(nullable = false)
    private String alertType;
    
    private String description;
    private String severity;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    private Boolean isActive;
}