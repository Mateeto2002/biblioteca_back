package com.iudigital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors() // Habilitar CORS
                .and()
                .csrf().disable() // Deshabilitar CSRF para pruebas (no recomendado en producción)
                .authorizeRequests()
                .anyRequest().permitAll(); // Permitir todas las solicitudes temporalmente
        return http.build();
    }




}
