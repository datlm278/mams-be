package com.example.mamsbe.services.minio;

import com.example.mamsbe.dto.FileDTO;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Objects;

@Slf4j
@Service
public class MinIOService {
    @Value("${minio.bucket.name}")
    private String bucketName;

    @Value("${minio.url}")
    private String url;

    private final MinioClient minioClient;

    @Autowired
    public MinIOService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public InputStream getObject(String filename) {
        InputStream stream;
        try {
            stream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filename)
                    .build());
        } catch (Exception e) {
            log.error("Happened error when get list objects from minio: ", e);
            return null;
        }
        return stream;
    }

    public FileDTO uploadFile(FileDTO request) {
        String fileName = System.currentTimeMillis() + Objects
                .requireNonNull(request.getFile().getOriginalFilename()).replace(" ", "_");
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(request.getFile().getInputStream(), request.getFile().getSize(), -1)
                    .contentType(request.getFile().getContentType())
                    .build());
        } catch (Exception e) {
            log.error("error when upload file: ", e);
        }

        return FileDTO.builder()
                .url(getResignedObjectUrl(fileName))
                .build();
    }

    private String getResignedObjectUrl(String fileName) {
        return url.concat(bucketName + "/").concat(fileName);
    }

}
