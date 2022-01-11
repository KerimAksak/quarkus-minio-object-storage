package org.kerim.minio.service.s3;

import java.io.InputStream;
import java.util.List;

public interface FileService {
    void saveFile(String id, InputStream isFile, String contentType);
    void deleteFile(String id);
    byte[] getFile(String id);
    List<?> getAllBuckets();
}
