package com.tanjina.mvc.backend.config; // This tells Java which folder this file is in

// These imports bring in tools we need for Spring Boot and CORS setup
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// This tells Spring Boot: "This is a configuration class"
@Configuration
public class CorsConfig {

    // This method runs when the application starts and sets up CORS rules
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            // This method defines which frontend is allowed to talk to our backend
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all API paths (/** means "all endpoints")
                        .allowedOrigins("http://localhost:5173") // Allow frontend on port 5173 (React)
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // Allow these HTTP methods
                        .allowedHeaders("*"); // Allow any headers sent from frontend
            }
        };
    }
}