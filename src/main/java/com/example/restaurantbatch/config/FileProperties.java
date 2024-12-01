package com.example.restaurantbatch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public record FileProperties (
        String path,
        String encoding
) {
}
