package io.mohajistudio.tangerine.prototype.infra.upload.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;

import io.mohajistudio.tangerine.prototype.domain.post.domain.PlaceBlockImage;
import io.mohajistudio.tangerine.prototype.domain.post.dto.PlaceBlockImageDTO;
import io.mohajistudio.tangerine.prototype.domain.post.repository.PlaceBlockImageRepository;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import io.mohajistudio.tangerine.prototype.infra.upload.config.S3Config;
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


    private final S3Config s3Config;
    private final AmazonS3Client amazonS3Client;
    private final PlaceBlockImageRepository placeBlockImageRepository;

    public PlaceBlockImageDTO uploadImageToS3(MultipartFile multipartFile) throws BusinessException {
        try {
            String originalFileName = multipartFile.getOriginalFilename();
            String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
            String changedName = changedImageName(ext);

            ObjectMetadata metadata = getObjectMetadata(multipartFile);

            PutObjectResult putObjectResult = amazonS3Client.putObject(s3Config.getBucket(), changedName, multipartFile.getInputStream(), metadata);
            String uploadFileUrl = amazonS3Client.getUrl(s3Config.getBucket(), changedName).toString();

            PlaceBlockImageDTO placeBlockImageDTO = getPlaceBlockImageDTO(uploadFileUrl, ext);

            savedPlaceBlockImage(placeBlockImageDTO);

            return placeBlockImageDTO;

        } catch (IOException e) {
            throw new RuntimeException("Error uploading image to S3.", e);
        }
    }

    private void savedPlaceBlockImage(PlaceBlockImageDTO placeBlockImageDTO) {

        PlaceBlockImage placeBlockImage = PlaceBlockImage.builder()
                .imageUrl(placeBlockImageDTO.getImageUrl())
                .imageMimeType(placeBlockImageDTO.getImageMimeType())
                .build();

        PlaceBlockImage savedPlaceBlockImage = placeBlockImageRepository.save(placeBlockImage);
    }

    private static PlaceBlockImageDTO getPlaceBlockImageDTO(String uploadFileUrl, String ext) {
        PlaceBlockImageDTO placeBlockImageDTO = new PlaceBlockImageDTO();
        placeBlockImageDTO.setImageUrl(uploadFileUrl);
        placeBlockImageDTO.setImageMimeType(ext);
        return placeBlockImageDTO;
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