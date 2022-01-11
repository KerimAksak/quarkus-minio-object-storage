package org.kerim.minio.startup;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.quarkus.runtime.StartupEvent;
import org.kerim.minio.config.S3ConfigProperties;
import org.kerim.minio.service.MinioService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.jboss.logging.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@ApplicationScoped
public class DemoData {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Inject
    MinioService minioService;

    @Inject
    MinioClient minioClient;

    @Inject
    S3ConfigProperties s3ConfigProperties;

    void onStart(@Observes StartupEvent ev) throws IOException {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(s3ConfigProperties.bucket()).build());
            if (!found) {
                // Make a new bucket called 'test'.
                LOGGER.info("Bucket 'test' is not exists.");
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(s3ConfigProperties.bucket()).build());
            } else {
                LOGGER.info("Bucket 'test' already exists.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream("img/babyYoda.jpeg");

        minioService.saveImage(UUID.randomUUID().toString(), new ByteArrayInputStream(is.readAllBytes()));
    }
}
