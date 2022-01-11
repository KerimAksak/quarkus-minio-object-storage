package org.kerim.minio.service.s3;

import io.minio.*;
import io.minio.messages.Item;
import org.jboss.logging.Logger;
import org.kerim.minio.config.S3ConfigProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MinioFileService implements FileService{

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

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
                    // Upload unknown sized input stream.
                    .stream(isFile, -1, 10485760)
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

    @Override
    public List<?> getAllBuckets(){
        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(s3ConfigProperties.bucket()).build());
        List<String> resultToList = new ArrayList<>();
        results.forEach(value -> {
            try {
                resultToList.add(value.get().objectName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return resultToList;
    }

}
