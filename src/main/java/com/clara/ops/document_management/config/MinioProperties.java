package com.clara.ops.document_management.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "config.bucket.minio")
public class MinioProperties {
    private String endpoint;
    private String user;
    private String pass;
}
