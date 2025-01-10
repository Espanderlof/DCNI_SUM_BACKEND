package com.duoc.alertas_medicas.util;

import org.springframework.stereotype.Component;

@Component
public class VitalSignsValidator {
    // Rangos normales de signos vitales
    private static final double MIN_HEART_RATE = 60;
    private static final double MAX_HEART_RATE = 100;
    private static final double MIN_BLOOD_PRESSURE_SYSTOLIC = 90;
    private static final double MAX_BLOOD_PRESSURE_SYSTOLIC = 140;
    private static final double MIN_BLOOD_PRESSURE_DIASTOLIC = 60;
    private static final double MAX_BLOOD_PRESSURE_DIASTOLIC = 90;
    private static final double MIN_TEMPERATURE = 36.5;
    private static final double MAX_TEMPERATURE = 37.5;
    private static final double MIN_OXYGEN_SATURATION = 95;

    public boolean isHeartRateNormal(double heartRate) {
        return heartRate >= MIN_HEART_RATE && heartRate <= MAX_HEART_RATE;
    }

    public boolean isBloodPressureNormal(double systolic, double diastolic) {
        return systolic >= MIN_BLOOD_PRESSURE_SYSTOLIC && 
               systolic <= MAX_BLOOD_PRESSURE_SYSTOLIC &&
               diastolic >= MIN_BLOOD_PRESSURE_DIASTOLIC && 
               diastolic <= MAX_BLOOD_PRESSURE_DIASTOLIC;
    }

    public boolean isTemperatureNormal(double temperature) {
        return temperature >= MIN_TEMPERATURE && temperature <= MAX_TEMPERATURE;
    }

    public boolean isOxygenSaturationNormal(double saturation) {
        return saturation >= MIN_OXYGEN_SATURATION;
    }

    public String getAlertSeverity(double value, double min, double max) {
        double deviation = Math.max(
            Math.abs(value - min) / min,
            Math.abs(value - max) / max
        );
        
        if (deviation > 0.2) return "HIGH";
        if (deviation > 0.1) return "MEDIUM";
        return "LOW";
    }
}