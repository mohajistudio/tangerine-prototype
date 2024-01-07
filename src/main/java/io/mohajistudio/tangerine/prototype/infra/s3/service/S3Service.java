package io.mohajistudio.tangerine.prototype.infra.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    private final AmazonS3Client amazonS3Client;
    private final String bucket;

    private String changedImageName(String ext) {
        String random = UUID.randomUUID().toString();
        return random + ext;
    }

    public void uploadImageToS3(MultipartFile multipartFile) {
        try {


            if (multipartFile == null) {
                throw new IllegalArgumentException("MultipartFile is null");
            }

            log.info("Original File Name: {}", multipartFile.getOriginalFilename());
            log.info("File Size: {} bytes", multipartFile.getSize());
            String originalFileName = multipartFile.getOriginalFilename();
            String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
            String changedName = changedImageName(ext);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());

            PutObjectResult putObjectResult = amazonS3Client.putObject(bucket, changedName, multipartFile.getInputStream(), metadata);
            String uploadFileUrl = amazonS3Client.getUrl(bucket, changedName).toString();

            // builder 통해서 dto에 데이터 넣기

        } catch (IOException e) {
            throw new RuntimeException("Error uploading image to S3.", e);
        }
    }
}