package org.kerim.minio.startup;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.netty.util.internal.ResourcesUtil;
import io.quarkus.runtime.StartupEvent;
import org.kerim.minio.config.S3ConfigProperties;
import org.kerim.minio.service.MinioService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.jboss.logging.Logger;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

        // MinioService.class is used because it cannot read the den.jpeg file under demo.class. (-_-)
        File file = ResourcesUtil.getFile(MinioService.class, "den.jpeg");
        minioService.saveImage(UUID.randomUUID().toString(), new ByteArrayInputStream(Files.readAllBytes(file.toPath())));
    }
}
