package com.example.bookstore.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow credentials, meaning sessions and basic auth are allowed
        config.setAllowCredentials(true);

        // Allow requests only from localhost:3000
        config.addAllowedOrigin("http://localhost:3000");

        // Allow headers for POST requests
        config.addAllowedHeader("*");

        // Specify the request methods allowed
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
