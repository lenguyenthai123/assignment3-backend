package com.assignment.backend_assignment3.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EnvConfig {

    @PostConstruct
    public void loadEnv() {
        // Đọc file `.env`
        Dotenv dotenv = Dotenv.load();

        // Đặt các biến môi trường vào System properties
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}