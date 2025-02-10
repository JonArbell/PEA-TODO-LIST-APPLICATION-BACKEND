package com.myapp.pea.Security.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**") // Api
                .allowedOrigins("http://localhost:4200","https://pea-todo-list-application.netlify.app","http://127.0.0.1:5500") // Allowed
                // Origins
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed Methods
                .allowedHeaders("Content-Type", "Authorization") // Allowed Headers
                .allowCredentials(true)
                .maxAge(3600);

    }

}
