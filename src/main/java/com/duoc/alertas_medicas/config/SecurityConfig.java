package com.duoc.alertas_medicas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())

            //SIN SEGURIDAD
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permitir todas las solicitudes temporalmente
            );
            
            //SEGURIDAD
            // .authorizeHttpRequests(auth -> auth
            //     // Endpoints de autenticación y Azure
            //     .requestMatchers("/api/auth/**").authenticated()
                
            //     // Endpoints de pacientes
            //     .requestMatchers(HttpMethod.GET, "/api/patients/**").authenticated()
            //     .requestMatchers(HttpMethod.POST, "/api/patients/**").authenticated()
            //     .requestMatchers(HttpMethod.PUT, "/api/patients/**").authenticated()
            //     .requestMatchers(HttpMethod.DELETE, "/api/patients/**").authenticated()
                
            //     // Endpoints de signos vitales
            //     .requestMatchers(HttpMethod.GET, "/api/vital-signs/**").authenticated()
            //     .requestMatchers(HttpMethod.POST, "/api/vital-signs/**").authenticated()
                
            //     // Endpoints de alertas
            //     .requestMatchers(HttpMethod.GET, "/api/alerts/**").authenticated()
            //     .requestMatchers(HttpMethod.PUT, "/api/alerts/**").authenticated()
                
            //     // Endpoints públicos
            //     .requestMatchers("/api/public/**").permitAll()
            //     .requestMatchers("/actuator/health").permitAll()
                
            //     // Por defecto, requerir autenticación
            //     .anyRequest().authenticated()
            // )
            // .oauth2ResourceServer(oauth2 -> oauth2
            //     .jwt(jwt -> jwt.decoder(jwtDecoder()))
            // );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowCredentials(false);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}