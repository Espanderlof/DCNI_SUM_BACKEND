package com.duoc.alertas_medicas.controller;

import com.duoc.alertas_medicas.dto.AzureUserDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/test_auth")
    public String test() {
        return "El endpoint de autenticación está funcionando correctamente";
    }

    @GetMapping("/test_public")
    @RequestMapping(path = "/public/test", method = RequestMethod.GET)
    public String testPublic() {
        return "Este es un endpoint público que no requiere autenticación";
    }

    @GetMapping("/perfil")
    public AzureUserDTO obtenerPerfil(@AuthenticationPrincipal Jwt jwt) {
        logger.info("Token recibido y validado correctamente");
        
        // Log de todos los claims disponibles para debugging
        logger.debug("Claims disponibles en el token:");
        jwt.getClaims().forEach((key, value) -> 
            logger.debug("{}: {}", key, value));
        
        AzureUserDTO usuario = new AzureUserDTO();
        
        // Campos básicos del usuario
        usuario.setId(jwt.getClaimAsString("oid")); // ID del objeto en Azure AD
        usuario.setNombre(jwt.getClaimAsString("given_name"));
        usuario.setApellidos(jwt.getClaimAsString("family_name"));
        usuario.setNombreParaMostrar(jwt.getClaimAsString("name"));
        
        // Manejo del email (puede venir en diferentes formatos)
        Object emailsClaim = jwt.getClaim("emails");
        if (emailsClaim instanceof String[] strings) {
            usuario.setEmail(strings[0]);
        } else if (emailsClaim instanceof java.util.List) {
            @SuppressWarnings("unchecked")
            java.util.List<String> emails = (java.util.List<String>) emailsClaim;
            if (!emails.isEmpty()) {
                usuario.setEmail(emails.get(0));
            }
        } else if (emailsClaim instanceof String string) {
            usuario.setEmail(string);
        }
        
        // Campos personalizados de Azure AD B2C
        usuario.setTelefono(jwt.getClaimAsString("extension_Telefono"));
        usuario.setDireccion(jwt.getClaimAsString("extension_Direccion"));
        
        // Metadatos del token
        usuario.setAccessToken(jwt.getTokenValue());
        usuario.setTfp(jwt.getClaimAsString("tfp"));
        usuario.setVersion(jwt.getClaimAsString("ver"));
        
        logger.info("Datos del usuario extraídos del token: {}", usuario);
        return usuario;
    }

    @GetMapping("/claims")
    public Object verClaims(@AuthenticationPrincipal Jwt jwt) {
        // Endpoint para debugging que muestra todos los claims disponibles
        return jwt.getClaims();
    }
}