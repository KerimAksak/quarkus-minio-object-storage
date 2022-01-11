package org.kerim.minio.service;

import java.io.File;
import java.nio.file.Files;

import javax.enterprise.context.ApplicationScoped;

import io.netty.util.internal.ResourcesUtil;

@ApplicationScoped
public class MinioService {
    public byte[] getImage(String id) throws Exception{
        File file = ResourcesUtil.getFile(MinioService.class, "den.jpeg");
        return Files.readAllBytes(file.toPath());
    }
}
