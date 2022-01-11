package org.kerim.minio.service.s3;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.kerim.minio.config.S3ConfigProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
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
