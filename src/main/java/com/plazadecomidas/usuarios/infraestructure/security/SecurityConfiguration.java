package com.plazadecomidas.usuarios.infraestructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desactiva la protección CSRF
                .authorizeHttpRequests(auth -> auth // Nuevo metodo para autorizar solicitudes
                        .anyRequest().permitAll() // Permite todas las solicitudes sin autenticación
                );

        return http.build();
    }
}