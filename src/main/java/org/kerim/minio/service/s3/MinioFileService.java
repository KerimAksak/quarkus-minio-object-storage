package org.kerim.minio.service.s3;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.kerim.minio.config.S3ConfigProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.InputStream;

@ApplicationScoped
public class MinioFileService implements FileService{

    @Inject
    MinioClient minioClient;

    @Inject
    S3ConfigProperties s3ConfigProperties;

    @Override
    public void saveFile(String id, InputStream isFile, String contentType) {
        try {
            var object = PutObjectArgs.builder()
                    .object(id)
                    .contentType(contentType)
                    .stream(isFile, isFile.available(), -1)
                    .bucket(s3ConfigProperties.bucket())
                    .build();
            minioClient.putObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFile(String id) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(s3ConfigProperties.bucket())
                    .object(id)
                    .build());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getFile(String id) {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(s3ConfigProperties.bucket())
                    .object(id)
                    .build()).readAllBytes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new byte[0];
    }
}
