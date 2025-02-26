package com.myapp.pea.Security.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/authenticated/**") // Api
                .allowedOrigins("http://localhost:4200","https://pea-todo-list-application.netlify.app") // Allowed Origins
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed Methods
                .allowedHeaders("Authorization") // Allowed Headers
                .maxAge(3600);

        registry.addMapping("/api/authentication/**") // Api
                .allowedOrigins("http://localhost:4200","https://pea-todo-list-application.netlify.app") // Allowed Origins
                .allowedMethods("POST","GET") // Allowed Methods
                .maxAge(3600);
    }

}
