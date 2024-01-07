package io.mohajistudio.tangerine.prototype.infra.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import io.mohajistudio.tangerine.prototype.infra.s3.dto.S3UploadRequestDTO;
import io.mohajistudio.tangerine.prototype.infra.s3.dto.S3UploadResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3UploadService {

    private final AmazonS3Client amazonS3Client;
    private final String bucket;

    public S3UploadResponseDTO uploadImageToS3(MultipartFile multipartFile) {
//        public S3UploadResponseDTO uploadImageToS3
//    }(MultipartFile multipartFile, S3UploadRequestDTO s3UploadRequestDTO) {
        try {
            if (multipartFile == null) {
                throw new IllegalArgumentException("MultipartFile is null");
            }
            String originalFileName = multipartFile.getOriginalFilename();
            String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
            String changedName = changedImageName(ext);

//            Long placeBlockId = s3UploadRequestDTO.getId();

            ObjectMetadata metadata = getObjectMetadata(multipartFile);

            PutObjectResult putObjectResult = amazonS3Client.putObject(bucket, changedName, multipartFile.getInputStream(), metadata);
            String uploadFileUrl = amazonS3Client.getUrl(bucket, changedName).toString();

            S3UploadResponseDTO s3UploadResponseDTO = S3UploadResponseDTO.builder()
                    .S3UploadImageUrl(uploadFileUrl)
                    .S3UploadImageName(originalFileName)
                    .build();

            return s3UploadResponseDTO;

        } catch (IOException e) {
            throw new RuntimeException("Error uploading image to S3.", e);
        }
    }

    private static ObjectMetadata getObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());
        return metadata;
    }

    private String changedImageName(String ext) {
        String random = UUID.randomUUID().toString();
        return random + ext;
    }
}