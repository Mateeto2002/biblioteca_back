package com.iudigital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig  implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://biblioteca-frontend-4e6x.onrender.com") // orígenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Encabezados permitidos
                .allowCredentials(true); // Permitir credenciales si es necesario

    }




}
