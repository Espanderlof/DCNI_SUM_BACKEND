package com.duoc.alertas_medicas.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PatientDTO {
    private Long id;
    private String name;
    private String rut;
    private Date dateOfBirth;
    private String medicalHistory;
    private String roomNumber;
}