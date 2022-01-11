package org.kerim.minio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class S3Configuration {
    @Bean
    public MinioClient minioClient(S3ConfigProperties properties){
        return MinioClient.builder()
            .credentials(properties.accessKey(), properties.secretPass())
            .endpoint(properties.url())
            .build();
    }
}
