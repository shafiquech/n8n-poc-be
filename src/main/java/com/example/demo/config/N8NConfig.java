package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("n8n")
@Data
public class N8NConfig {
    private String personInfoUrl;
    private String sendMessageUrl;
}

