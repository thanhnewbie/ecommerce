package com.example.ecommerce.ecommerce.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("storage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class StorageConfig {
    private String location;
}
