package org.kerim.minio.service;

import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.kerim.minio.service.s3.MinioFileService;

@ApplicationScoped
public class MinioService {

    @Inject
    MinioFileService fileService;

    public byte[] getImage(String id){
        return fileService.getFile(id);
    }

    public void saveImage(String id, InputStream isFile){
        fileService.saveFile(id, isFile,"image/jpeg");
    }

    public void deleteImage(String id){
        fileService.deleteFile(id);
    }
}
