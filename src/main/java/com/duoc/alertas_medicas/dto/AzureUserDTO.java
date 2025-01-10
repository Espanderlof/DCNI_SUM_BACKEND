package com.duoc.alertas_medicas.dto;

import lombok.Data;

@Data
public class AzureUserDTO {
    // Campos estándar de Azure AD B2C
    private String id;                    // Id. de objeto del usuario (oid)
    private String nombre;                // Nombre (given_name)
    private String apellidos;             // Apellidos (family_name)
    private String email;                 // Direcciones de correo electrónico (emails)
    private String nombreParaMostrar;     // Nombre para mostrar (name)
    private String accessToken;           // Token de acceso
    
    // Campos personalizados en Azure AD B2C
    private String telefono;              // extension_Telefono
    private String direccion;             // extension_Direccion
    
    // Metadatos del token
    private String tfp;                   // Política B2C usada
    private String version;               // Versión del token
}