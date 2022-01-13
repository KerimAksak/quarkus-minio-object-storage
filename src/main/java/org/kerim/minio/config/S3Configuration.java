package org.kerim.minio.config;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class S3Configuration {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");



    @Bean
    public MinioClient minioClient(S3ConfigProperties properties){
        LOGGER.info("minio url...:"+properties.url());

        return MinioClient.builder()
                .endpoint(properties.url())
                .credentials(properties.accessKey(), properties.secretPass())
                .build();
    }
}
