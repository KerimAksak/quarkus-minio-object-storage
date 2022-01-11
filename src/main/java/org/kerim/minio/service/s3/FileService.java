package org.kerim.minio.service.s3;

import java.io.InputStream;

public interface FileService {
    void saveFile(String id, InputStream isFile, String contentType);
    void deleteFile(String id);
    byte[] getFile(String id);
}
