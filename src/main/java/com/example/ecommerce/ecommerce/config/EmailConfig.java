package com.example.ecommerce.ecommerce.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties("mail")
@Data
@Component
public class EmailConfig {
    String email;
    String password;
}
